package com.yxh.weshare.utils.recommend;

import com.yxh.weshare.bean.pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Xinhao Yi
 * @date 2022/7/13 12:07
 * @description: Recommend utils class
 */
public class RecommendCalculateUtil {

    private static Integer numOfFactors;

    // learning rate
    private static Double alpha;

    // regularity factor
    private static Double beta;

    //max iteration times
    private static Integer maxIterations;

    private static InnerMatrix userMatrix;
    private static InnerMatrix goodsMatrix;

    private static List<UserFactor> userFactorList;

    private static List<GoodsFactor> goodsFactorList;

    private static List<UserGoodsScorePredict> userGoodsScorePredictList;

    private static List<Double> lossList;

    private static boolean excuteFlag = false;

    static {
        try {
            //get num of impact factors from "com/yxh/weshare/config/recommend.config". we set it to 10 now
            InputStream resourceAsStream = RecommendCalculateUtil.class.getClassLoader().getResourceAsStream("com/yxh/weshare/config/recommend.properties");
            Properties prop = new Properties();
            prop.load(resourceAsStream);
            numOfFactors = Integer.parseInt(prop.getProperty("numOfFactors"));
            alpha = Double.parseDouble(prop.getProperty("alpha"));
            beta = Double.parseDouble(prop.getProperty("beta"));
            maxIterations = Integer.parseInt(prop.getProperty("maxIterations"));

            userFactorList = new ArrayList<>();
            goodsFactorList = new ArrayList<>();

            userGoodsScorePredictList = new ArrayList<>();

            lossList = new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void refresh(){
        RecommendCalculateUtil.userFactorList = new ArrayList<>();
        RecommendCalculateUtil.goodsFactorList = new ArrayList<>();

        RecommendCalculateUtil.userGoodsScorePredictList = new ArrayList<>();
        RecommendCalculateUtil.lossList = new ArrayList<>();
    }

    /************************* Build Model *******************************/

    /**
     * the row element
     */
    private static class MatrixRowElement {
        //userId or goodsId
        private Integer id;
        private double[] factors;

        /**
         * constructor
         **/
        public MatrixRowElement() {
        }

        public MatrixRowElement(Integer id, Integer numOfFactors) {
            this.id = id;
            this.factors = new double[numOfFactors];
            for (int i = 0; i < numOfFactors; i++) {
                // 初始值设置为0-0.25的随机数
                // Random number with initial value set to 0-1
                this.factors[i] = Math.random() * 0.25;
            }
        }

        //BigDecimal

        public MatrixRowElement(Integer distinctID, UserFactor userFactor, Integer numOfFactors) {
            this(distinctID, numOfFactors);
            if (null != userFactor) {
                Class<? extends UserFactor> aClass = userFactor.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                // numOfFactors = 10
                numOfFactors = declaredFields.length - 1;
                // possible to refresh the inner arr
                if (numOfFactors != this.factors.length) {
                    this.factors = new double[numOfFactors];
                }

                for (int i = 0; i < numOfFactors; i++) {
                    try {
                        declaredFields[i + 1].setAccessible(true);
                        double value = (double) declaredFields[i + 1].get(userFactor);
                        this.factors[i] = value;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

//                    //还可以调用get方法实现
//                    String fieldName = declaredFields[i + 1].getName();
//                    // 拼接get方法,如getId
//                    String getMethod = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1,fieldName.length());
//                    try {
//                        Double value = (Double) aClass.getMethod(getMethod).invoke(userFactor);
//                        this.factors[i] = value;
//                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                        e.printStackTrace();
//                    }
                }

            }


        }

        public MatrixRowElement(Integer distinctID, GoodsFactor goodsFactor, Integer numOfFactors) {
            this(distinctID, numOfFactors);
            if (null != goodsFactor) {
                Class<? extends GoodsFactor> aClass = goodsFactor.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                // numOfFactors = 10
                numOfFactors = declaredFields.length - 1;
                // possible to refresh the inner arr
                if (numOfFactors != this.factors.length) {
                    this.factors = new double[numOfFactors];
                }

                for (int i = 0; i < numOfFactors; i++) {
                    try {
                        declaredFields[i + 1].setAccessible(true);
                        double value = (double) declaredFields[i + 1].get(goodsFactor);
                        this.factors[i] = value;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        public Double multiplicate(MatrixRowElement rowElement) {
            double res = 0.0;
            for (int i = 0; i < this.factors.length; i++) {
                res += this.factors[i] * rowElement.factors[i];
            }
            return res;
        }

    }

    /**
     * Inner matrix
     */
    private static class InnerMatrix {
        private Map<Integer, MatrixRowElement> mapOfRowElements;

        /**
         * constructor
         **/
        public InnerMatrix(Set<Integer> distinctIDs) {
            mapOfRowElements = new HashMap<>();
            for (Integer distinctID : distinctIDs) {
                mapOfRowElements.put(distinctID, new MatrixRowElement(distinctID, RecommendCalculateUtil.numOfFactors));
            }
        }

        public InnerMatrix(Set<Integer> distinctUserIDs, Map<Integer, UserFactor> userFactorMap) {
            mapOfRowElements = new HashMap<>();
            for (Integer distinctID : distinctUserIDs) {
                if (userFactorMap.containsKey(distinctID)) {
                    UserFactor userFactor = userFactorMap.get(distinctID);
                    mapOfRowElements.put(distinctID, new MatrixRowElement(distinctID, userFactor, RecommendCalculateUtil.numOfFactors));
                } else {
                    mapOfRowElements.put(distinctID, new MatrixRowElement(distinctID, RecommendCalculateUtil.numOfFactors));
                }
            }
        }

        // distinctFlag is useless
        public InnerMatrix(Set<Integer> distinctGoodsIDs, Map<Integer, GoodsFactor> goodsFactorMap, Integer distinctFlag) {
            mapOfRowElements = new HashMap<>();
            for (Integer distinctID : distinctGoodsIDs) {
                if (goodsFactorMap.containsKey(distinctID)) {
                    GoodsFactor goodsFactor = goodsFactorMap.get(distinctID);
                    mapOfRowElements.put(distinctID, new MatrixRowElement(distinctID, goodsFactor, RecommendCalculateUtil.numOfFactors));
                } else {
                    mapOfRowElements.put(distinctID, new MatrixRowElement(distinctID, RecommendCalculateUtil.numOfFactors));
                }
            }
        }

        public MatrixRowElement getRowElements(Integer id) {
            return mapOfRowElements.get(id);
        }

    }


    /**
     * traversal the list from user_goods_score table and get two set collection contains the distinct userID and goodsID
     * we will use these two sets to build the matrix
     *
     * @param userGoodsScoreList get the list from data base
     */
    private static void buildMatrix(List<UserGoodsScore> userGoodsScoreList, Map<Integer, UserFactor> userFactorMap, Map<Integer, GoodsFactor> goodsFactorMap) {
        Set<Integer> distinctUserIDs = new HashSet<>();
        Set<Integer> distinctGoodsIDs = new HashSet<>();
        for (UserGoodsScore userGoodsScore : userGoodsScoreList) {
            distinctUserIDs.add(userGoodsScore.getWsUserId());
            distinctGoodsIDs.add(userGoodsScore.getWsGoodsId());
        }
        // create two initial matrix based on the two set collection
        if (null == userFactorMap || 0 == userFactorMap.size()) {
            userMatrix = new InnerMatrix(distinctUserIDs);
        } else {
            userMatrix = new InnerMatrix(distinctUserIDs, userFactorMap);
        }

        if (null == goodsFactorMap || 0 == goodsFactorMap.size()) {
            goodsMatrix = new InnerMatrix(distinctGoodsIDs);
        } else {
            goodsMatrix = new InnerMatrix(distinctGoodsIDs, goodsFactorMap, 1);
        }
    }

    /**
     * implement gradient descent to build the two smaller matrix.
     *
     * @param userGoodsScoreList get the list from data base
     */
    private static void latentFactorModelGradientDescent(List<UserGoodsScore> userGoodsScoreList) {
        //error - the difference between the prediction and the true value
        double error = 0.0;

        for (int iterationTimes = 0; iterationTimes < maxIterations; iterationTimes++) {
            for (UserGoodsScore userGoodsScore : userGoodsScoreList) {
                MatrixRowElement userRow = userMatrix.getRowElements(userGoodsScore.getWsUserId());
                MatrixRowElement goodsRow = goodsMatrix.getRowElements(userGoodsScore.getWsGoodsId());
                //calculate the the difference between the true value and the prediction
                error = userGoodsScore.getWsScore() - userRow.multiplicate(goodsRow);

                //Then update the factors in userMatrix and goodsMatrix
                for (int i = 0; i < numOfFactors; i++) {
                    userRow.factors[i] = userRow.factors[i] + alpha * (2 * error * goodsRow.factors[i] - beta * userRow.factors[i]);
                    goodsRow.factors[i] = goodsRow.factors[i] + alpha * (2 * error * userRow.factors[i] - beta * goodsRow.factors[i]);
                }
            }

            // the final loss of our model
            double loss = 0.0;

            //Then we will calculate the loss
            for (UserGoodsScore userGoodsScore : userGoodsScoreList) {
                MatrixRowElement userRow = userMatrix.getRowElements(userGoodsScore.getWsUserId());
                MatrixRowElement goodsRow = goodsMatrix.getRowElements(userGoodsScore.getWsGoodsId());
                //calculate the the left part of the loss function
                loss += Math.pow(userGoodsScore.getWsScore() - userRow.multiplicate(goodsRow), 2);
                for (int i = 0; i < numOfFactors; i++) {
                    loss += (beta / 2) * (userRow.factors[i] * userRow.factors[i] + goodsRow.factors[i] * goodsRow.factors[i]);
                }
            }

            //update the loss list
            RecommendCalculateUtil.lossList.add(loss);

            //check if loss < 0.001. end
            if (loss < 0.001) {
                System.out.println(loss);
                break;
            }
        }
    }

    // 这个方法感觉没什么必要
    private static void simplebuildMatrixForSingelUser(Integer userId, List<UserGoodsScore> userGoodsScoreList, Map<Integer, UserFactor> userFactorMap, Map<Integer, GoodsFactor> goodsFactorMap) {

        Set<Integer> distinctUserIDs = new HashSet<>();
        distinctUserIDs.add(userId);

        Set<Integer> distinctGoodsIDs = new HashSet<>();
        for (UserGoodsScore userGoodsScore : userGoodsScoreList) {
            distinctGoodsIDs.add(userGoodsScore.getWsGoodsId());
        }

        // create two initial matrix based on the two set collection
        if (null == userFactorMap) {
            userMatrix = new InnerMatrix(distinctUserIDs);
        } else {
            userMatrix = new InnerMatrix(distinctUserIDs, userFactorMap);
        }

        if (null == goodsFactorMap) {
            goodsMatrix = new InnerMatrix(distinctGoodsIDs);
        } else {
            goodsMatrix = new InnerMatrix(distinctGoodsIDs, goodsFactorMap, 1);
        }
    }


    private static GoodsFactor buildGoodsFactorBeanFromRowElement(MatrixRowElement rowElement) {
        GoodsFactor res = new GoodsFactor();
        res.setWsGoodsId(rowElement.id);
        res.setFactor1(rowElement.factors[0]);
        res.setFactor2(rowElement.factors[1]);
        res.setFactor3(rowElement.factors[2]);
        res.setFactor4(rowElement.factors[3]);
        res.setFactor5(rowElement.factors[4]);
        res.setFactor6(rowElement.factors[5]);
        res.setFactor7(rowElement.factors[6]);
        res.setFactor8(rowElement.factors[7]);
        res.setFactor9(rowElement.factors[8]);
        res.setFactor10(rowElement.factors[9]);
        return res;
    }

    private static UserFactor buildUserFactorBeanFromRowElement(MatrixRowElement rowElement) {
        UserFactor res = new UserFactor();
        res.setWsUserId(rowElement.id);
        res.setFactor1(rowElement.factors[0]);
        res.setFactor2(rowElement.factors[1]);
        res.setFactor3(rowElement.factors[2]);
        res.setFactor4(rowElement.factors[3]);
        res.setFactor5(rowElement.factors[4]);
        res.setFactor6(rowElement.factors[5]);
        res.setFactor7(rowElement.factors[6]);
        res.setFactor8(rowElement.factors[7]);
        res.setFactor9(rowElement.factors[8]);
        res.setFactor10(rowElement.factors[9]);
        return res;
    }


    /**
     * 根据List<UserFactor> userFactorList, List<GoodsFactor> goodsFactorList生成大矩阵
     * Generate large matrix data based on userFactorList, goodsFactorList
     *
     * @param userFactorList
     * @param goodsFactorList
     * @return
     */
    private static List<UserGoodsScorePredict> generateUserGoodsScorePredictList(List<UserFactor> userFactorList, List<GoodsFactor> goodsFactorList) {
        List<UserGoodsScorePredict> res = new ArrayList<>();
        for (UserFactor userFactor : userFactorList) {
            for (GoodsFactor goodsFactor : goodsFactorList) {
                double predictScore = predict(userFactor, goodsFactor);
                // todo check
                predictScore = new BigDecimal(predictScore).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                UserGoodsScorePredict userGoodsScorePredict = new UserGoodsScorePredict();
                userGoodsScorePredict.setWsUserId(userFactor.getWsUserId());
                userGoodsScorePredict.setWsGoodsId(goodsFactor.getWsGoodsId());
                userGoodsScorePredict.setWsScorePredict(predictScore);
                res.add(userGoodsScorePredict);
            }
        }
        return res;
    }


    /**
     * combine the former function and fulfil the two result list - userFactorList, goodsFactorList
     *
     * @param userGoodsScoreList
     */
    public static void modelExecute(List<UserGoodsScore> userGoodsScoreList, List<UserFactor> originUserFactorList, List<GoodsFactor> originGoodsFactorList) {

        refresh();
        Map<Integer, UserFactor> userFactorMap = generateUserFactorMap(originUserFactorList);
        Map<Integer, GoodsFactor> goodsFactorMap = generateGoodsFactorMap(originGoodsFactorList);

        buildMatrix(userGoodsScoreList, userFactorMap, goodsFactorMap);
        latentFactorModelGradientDescent(userGoodsScoreList);

        //store the InnerMatrix into data base - userMatrix, goodsMatrix
        for (Map.Entry<Integer, MatrixRowElement> entry : userMatrix.mapOfRowElements.entrySet()) {
            RecommendCalculateUtil.userFactorList.add(buildUserFactorBeanFromRowElement(entry.getValue()));
        }

        for (Map.Entry<Integer, MatrixRowElement> entry : goodsMatrix.mapOfRowElements.entrySet()) {
            RecommendCalculateUtil.goodsFactorList.add(buildGoodsFactorBeanFromRowElement(entry.getValue()));
        }

        //generate the userGoodsScorePredictList
        //对单用户的简单预测情况，这一条也是要插入的
        // For the simple prediction case of a single user, this entry is also to be inserted
        RecommendCalculateUtil.userGoodsScorePredictList = generateUserGoodsScorePredictList(RecommendCalculateUtil.userFactorList, RecommendCalculateUtil.goodsFactorList);

        //we have already execute the function
        excuteFlag = true;
    }


    public static void modelExecuteForSingleUser(List<UserGoodsScore> userGoodsScoreList, List<UserFactor> originUserFactorList, List<GoodsFactor> originGoodsFactorList, List<GoodsFactor> allOriginGoodsFactorList) {

        Map<Integer, UserFactor> userFactorMap = generateUserFactorMap(originUserFactorList);
        Map<Integer, GoodsFactor> goodsFactorMap = generateGoodsFactorMap(originGoodsFactorList);

        buildMatrix(userGoodsScoreList, userFactorMap, goodsFactorMap);
        latentFactorModelGradientDescent(userGoodsScoreList);

        //store the InnerMatrix into data base - userMatrix, goodsMatrix
        for (Map.Entry<Integer, MatrixRowElement> entry : userMatrix.mapOfRowElements.entrySet()) {
            RecommendCalculateUtil.userFactorList.add(buildUserFactorBeanFromRowElement(entry.getValue()));
        }

//        for (Map.Entry<Integer, MatrixRowElement> entry : goodsMatrix.mapOfRowElements.entrySet()) {
//            RecommendCalculateUtil.goodsFactorList.add(buildGoodsFactorBeanFromRowElement(entry.getValue()));
//        }

        //generate the userGoodsScorePredictList
        //对单用户的简单预测情况，这一条也是要插入的
        // For the simple prediction case of a single user, this entry is also to be inserted
        userGoodsScorePredictList = generateUserGoodsScorePredictList(RecommendCalculateUtil.userFactorList, allOriginGoodsFactorList);

        //we have already execute the function
        excuteFlag = true;
    }


    private static Map<Integer, UserFactor> generateUserFactorMap(List<UserFactor> userFactorList) {
        Map<Integer, UserFactor> res = new HashMap<>();
        for (UserFactor userFactor : userFactorList) {
            res.put(userFactor.getWsUserId(), userFactor);
        }
        return res;
    }

    private static Map<Integer, GoodsFactor> generateGoodsFactorMap(List<GoodsFactor> goodsFactorList) {
        Map<Integer, GoodsFactor> res = new HashMap<>();
        for (GoodsFactor goodsFactor : goodsFactorList) {
            res.put(goodsFactor.getWsGoodsId(), goodsFactor);
        }
        return res;
    }


    /**
     * GET result of user-factor list
     *
     * @return
     */
    public static List<UserFactor> getUserFactorList() {
        return excuteFlag ? userFactorList : new ArrayList<>();
    }

    /**
     * GET result of goods-factor list
     *
     * @return
     */
    public static List<GoodsFactor> getGoodsFactorList() {
        return excuteFlag ? goodsFactorList : new ArrayList<>();
    }

    public static List<UserGoodsScorePredict> getUserGoodsScorePredictList() {
        return excuteFlag ? userGoodsScorePredictList : new ArrayList<>();
    }


    public static List<Double> getLossList(){
        return excuteFlag ? RecommendCalculateUtil.lossList : new ArrayList<>();
    }


    /************************* Get To Recommend *******************************/

    /**
     * get the prediction score
     *
     * @param userFactor
     * @param goodsFactor
     * @return
     */
    private static Double predict(UserFactor userFactor, GoodsFactor goodsFactor) {
        return userFactor.getFactor1() * goodsFactor.getFactor1() + userFactor.getFactor2() * goodsFactor.getFactor2()
                + userFactor.getFactor3() * goodsFactor.getFactor3() + userFactor.getFactor4() * goodsFactor.getFactor4()
                + userFactor.getFactor5() * goodsFactor.getFactor5() + userFactor.getFactor6() * goodsFactor.getFactor6()
                + userFactor.getFactor7() * goodsFactor.getFactor7() + userFactor.getFactor8() * goodsFactor.getFactor8()
                + userFactor.getFactor9() * goodsFactor.getFactor9() + userFactor.getFactor10() * goodsFactor.getFactor10();
    }


    /**
     * get the topN list of goods for a single user
     *
     * @param userFactor      a user-factor object
     * @param goodsFactorList a list of all the goods-factor objects
     * @return List<Integer> res
     */
    public static List<Integer> getUserRecommendList(UserFactor userFactor, List<GoodsFactor> goodsFactorList, Integer numOfElements) {

        if (numOfElements <= 0) {
            return new ArrayList<>();
        }

        Map<Integer, Double> innerMap = new HashMap<>();

        List<Integer> res = new ArrayList<>();

        for (GoodsFactor goodsFactor : goodsFactorList) {
            Double predictScore = predict(userFactor, goodsFactor);
            innerMap.put(goodsFactor.getWsGoodsId(), predictScore);
            res.add(goodsFactor.getWsGoodsId());
        }

        //Sort from largest to smallest
        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return innerMap.get(o1).compareTo(innerMap.get(o2));
                return new BigDecimal(innerMap.get(o2)).compareTo(new BigDecimal(innerMap.get(o1)));
            }
        });

        if (res.size() > numOfElements) {
            return res.subList(0, numOfElements);
        } else {
            return res;
        }
    }

    public static List<Integer> getUserRecommendList(List<UserGoodsScorePredict> userGoodsScorePredictList, Integer numOfElements) {
        if (numOfElements <= 0) {
            return new ArrayList<>();
        }

        Map<Integer, Double> innerMap = new HashMap<>();

        List<Integer> res = new ArrayList<>();

        for (UserGoodsScorePredict userGoodsScorePredict : userGoodsScorePredictList) {
            innerMap.put(userGoodsScorePredict.getWsGoodsId(), userGoodsScorePredict.getWsScorePredict());
            res.add(userGoodsScorePredict.getWsGoodsId());
        }

        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return new BigDecimal(innerMap.get(o2)).compareTo(new BigDecimal(innerMap.get(o1)));
            }
        });

        if (res.size() > numOfElements) {
            return res.subList(0, numOfElements);
        } else {
            return res;
        }

    }

}

