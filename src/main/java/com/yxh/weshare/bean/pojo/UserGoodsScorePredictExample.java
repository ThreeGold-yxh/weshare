package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserGoodsScorePredictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserGoodsScorePredictExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andWsUserIdIsNull() {
            addCriterion("ws_user_id is null");
            return (Criteria) this;
        }

        public Criteria andWsUserIdIsNotNull() {
            addCriterion("ws_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserIdEqualTo(Integer value) {
            addCriterion("ws_user_id =", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdNotEqualTo(Integer value) {
            addCriterion("ws_user_id <>", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdGreaterThan(Integer value) {
            addCriterion("ws_user_id >", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_user_id >=", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdLessThan(Integer value) {
            addCriterion("ws_user_id <", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_user_id <=", value, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdIn(List<Integer> values) {
            addCriterion("ws_user_id in", values, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdNotIn(List<Integer> values) {
            addCriterion("ws_user_id not in", values, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_id between", value1, value2, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_id not between", value1, value2, "wsUserId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdIsNull() {
            addCriterion("ws_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdIsNotNull() {
            addCriterion("ws_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdEqualTo(Integer value) {
            addCriterion("ws_goods_id =", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdNotEqualTo(Integer value) {
            addCriterion("ws_goods_id <>", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdGreaterThan(Integer value) {
            addCriterion("ws_goods_id >", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_id >=", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdLessThan(Integer value) {
            addCriterion("ws_goods_id <", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_id <=", value, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdIn(List<Integer> values) {
            addCriterion("ws_goods_id in", values, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdNotIn(List<Integer> values) {
            addCriterion("ws_goods_id not in", values, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_id between", value1, value2, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_id not between", value1, value2, "wsGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictIsNull() {
            addCriterion("ws_score_predict is null");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictIsNotNull() {
            addCriterion("ws_score_predict is not null");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictEqualTo(Double value) {
            addCriterion("ws_score_predict =", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictNotEqualTo(Double value) {
            addCriterion("ws_score_predict <>", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictGreaterThan(Double value) {
            addCriterion("ws_score_predict >", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictGreaterThanOrEqualTo(Double value) {
            addCriterion("ws_score_predict >=", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictLessThan(Double value) {
            addCriterion("ws_score_predict <", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictLessThanOrEqualTo(Double value) {
            addCriterion("ws_score_predict <=", value, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictIn(List<Double> values) {
            addCriterion("ws_score_predict in", values, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictNotIn(List<Double> values) {
            addCriterion("ws_score_predict not in", values, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictBetween(Double value1, Double value2) {
            addCriterion("ws_score_predict between", value1, value2, "wsScorePredict");
            return (Criteria) this;
        }

        public Criteria andWsScorePredictNotBetween(Double value1, Double value2) {
            addCriterion("ws_score_predict not between", value1, value2, "wsScorePredict");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}