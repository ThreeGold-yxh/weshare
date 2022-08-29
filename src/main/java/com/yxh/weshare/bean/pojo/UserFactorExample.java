package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserFactorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserFactorExample() {
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

        public Criteria andFactor1IsNull() {
            addCriterion("factor1 is null");
            return (Criteria) this;
        }

        public Criteria andFactor1IsNotNull() {
            addCriterion("factor1 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor1EqualTo(Double value) {
            addCriterion("factor1 =", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1NotEqualTo(Double value) {
            addCriterion("factor1 <>", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1GreaterThan(Double value) {
            addCriterion("factor1 >", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1GreaterThanOrEqualTo(Double value) {
            addCriterion("factor1 >=", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1LessThan(Double value) {
            addCriterion("factor1 <", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1LessThanOrEqualTo(Double value) {
            addCriterion("factor1 <=", value, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1In(List<Double> values) {
            addCriterion("factor1 in", values, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1NotIn(List<Double> values) {
            addCriterion("factor1 not in", values, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1Between(Double value1, Double value2) {
            addCriterion("factor1 between", value1, value2, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor1NotBetween(Double value1, Double value2) {
            addCriterion("factor1 not between", value1, value2, "factor1");
            return (Criteria) this;
        }

        public Criteria andFactor2IsNull() {
            addCriterion("factor2 is null");
            return (Criteria) this;
        }

        public Criteria andFactor2IsNotNull() {
            addCriterion("factor2 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor2EqualTo(Double value) {
            addCriterion("factor2 =", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2NotEqualTo(Double value) {
            addCriterion("factor2 <>", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2GreaterThan(Double value) {
            addCriterion("factor2 >", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2GreaterThanOrEqualTo(Double value) {
            addCriterion("factor2 >=", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2LessThan(Double value) {
            addCriterion("factor2 <", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2LessThanOrEqualTo(Double value) {
            addCriterion("factor2 <=", value, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2In(List<Double> values) {
            addCriterion("factor2 in", values, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2NotIn(List<Double> values) {
            addCriterion("factor2 not in", values, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2Between(Double value1, Double value2) {
            addCriterion("factor2 between", value1, value2, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor2NotBetween(Double value1, Double value2) {
            addCriterion("factor2 not between", value1, value2, "factor2");
            return (Criteria) this;
        }

        public Criteria andFactor3IsNull() {
            addCriterion("factor3 is null");
            return (Criteria) this;
        }

        public Criteria andFactor3IsNotNull() {
            addCriterion("factor3 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor3EqualTo(Double value) {
            addCriterion("factor3 =", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3NotEqualTo(Double value) {
            addCriterion("factor3 <>", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3GreaterThan(Double value) {
            addCriterion("factor3 >", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3GreaterThanOrEqualTo(Double value) {
            addCriterion("factor3 >=", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3LessThan(Double value) {
            addCriterion("factor3 <", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3LessThanOrEqualTo(Double value) {
            addCriterion("factor3 <=", value, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3In(List<Double> values) {
            addCriterion("factor3 in", values, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3NotIn(List<Double> values) {
            addCriterion("factor3 not in", values, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3Between(Double value1, Double value2) {
            addCriterion("factor3 between", value1, value2, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor3NotBetween(Double value1, Double value2) {
            addCriterion("factor3 not between", value1, value2, "factor3");
            return (Criteria) this;
        }

        public Criteria andFactor4IsNull() {
            addCriterion("factor4 is null");
            return (Criteria) this;
        }

        public Criteria andFactor4IsNotNull() {
            addCriterion("factor4 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor4EqualTo(Double value) {
            addCriterion("factor4 =", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4NotEqualTo(Double value) {
            addCriterion("factor4 <>", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4GreaterThan(Double value) {
            addCriterion("factor4 >", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4GreaterThanOrEqualTo(Double value) {
            addCriterion("factor4 >=", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4LessThan(Double value) {
            addCriterion("factor4 <", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4LessThanOrEqualTo(Double value) {
            addCriterion("factor4 <=", value, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4In(List<Double> values) {
            addCriterion("factor4 in", values, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4NotIn(List<Double> values) {
            addCriterion("factor4 not in", values, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4Between(Double value1, Double value2) {
            addCriterion("factor4 between", value1, value2, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor4NotBetween(Double value1, Double value2) {
            addCriterion("factor4 not between", value1, value2, "factor4");
            return (Criteria) this;
        }

        public Criteria andFactor5IsNull() {
            addCriterion("factor5 is null");
            return (Criteria) this;
        }

        public Criteria andFactor5IsNotNull() {
            addCriterion("factor5 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor5EqualTo(Double value) {
            addCriterion("factor5 =", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5NotEqualTo(Double value) {
            addCriterion("factor5 <>", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5GreaterThan(Double value) {
            addCriterion("factor5 >", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5GreaterThanOrEqualTo(Double value) {
            addCriterion("factor5 >=", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5LessThan(Double value) {
            addCriterion("factor5 <", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5LessThanOrEqualTo(Double value) {
            addCriterion("factor5 <=", value, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5In(List<Double> values) {
            addCriterion("factor5 in", values, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5NotIn(List<Double> values) {
            addCriterion("factor5 not in", values, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5Between(Double value1, Double value2) {
            addCriterion("factor5 between", value1, value2, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor5NotBetween(Double value1, Double value2) {
            addCriterion("factor5 not between", value1, value2, "factor5");
            return (Criteria) this;
        }

        public Criteria andFactor6IsNull() {
            addCriterion("factor6 is null");
            return (Criteria) this;
        }

        public Criteria andFactor6IsNotNull() {
            addCriterion("factor6 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor6EqualTo(Double value) {
            addCriterion("factor6 =", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6NotEqualTo(Double value) {
            addCriterion("factor6 <>", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6GreaterThan(Double value) {
            addCriterion("factor6 >", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6GreaterThanOrEqualTo(Double value) {
            addCriterion("factor6 >=", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6LessThan(Double value) {
            addCriterion("factor6 <", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6LessThanOrEqualTo(Double value) {
            addCriterion("factor6 <=", value, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6In(List<Double> values) {
            addCriterion("factor6 in", values, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6NotIn(List<Double> values) {
            addCriterion("factor6 not in", values, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6Between(Double value1, Double value2) {
            addCriterion("factor6 between", value1, value2, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor6NotBetween(Double value1, Double value2) {
            addCriterion("factor6 not between", value1, value2, "factor6");
            return (Criteria) this;
        }

        public Criteria andFactor7IsNull() {
            addCriterion("factor7 is null");
            return (Criteria) this;
        }

        public Criteria andFactor7IsNotNull() {
            addCriterion("factor7 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor7EqualTo(Double value) {
            addCriterion("factor7 =", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7NotEqualTo(Double value) {
            addCriterion("factor7 <>", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7GreaterThan(Double value) {
            addCriterion("factor7 >", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7GreaterThanOrEqualTo(Double value) {
            addCriterion("factor7 >=", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7LessThan(Double value) {
            addCriterion("factor7 <", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7LessThanOrEqualTo(Double value) {
            addCriterion("factor7 <=", value, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7In(List<Double> values) {
            addCriterion("factor7 in", values, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7NotIn(List<Double> values) {
            addCriterion("factor7 not in", values, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7Between(Double value1, Double value2) {
            addCriterion("factor7 between", value1, value2, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor7NotBetween(Double value1, Double value2) {
            addCriterion("factor7 not between", value1, value2, "factor7");
            return (Criteria) this;
        }

        public Criteria andFactor8IsNull() {
            addCriterion("factor8 is null");
            return (Criteria) this;
        }

        public Criteria andFactor8IsNotNull() {
            addCriterion("factor8 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor8EqualTo(Double value) {
            addCriterion("factor8 =", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8NotEqualTo(Double value) {
            addCriterion("factor8 <>", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8GreaterThan(Double value) {
            addCriterion("factor8 >", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8GreaterThanOrEqualTo(Double value) {
            addCriterion("factor8 >=", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8LessThan(Double value) {
            addCriterion("factor8 <", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8LessThanOrEqualTo(Double value) {
            addCriterion("factor8 <=", value, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8In(List<Double> values) {
            addCriterion("factor8 in", values, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8NotIn(List<Double> values) {
            addCriterion("factor8 not in", values, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8Between(Double value1, Double value2) {
            addCriterion("factor8 between", value1, value2, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor8NotBetween(Double value1, Double value2) {
            addCriterion("factor8 not between", value1, value2, "factor8");
            return (Criteria) this;
        }

        public Criteria andFactor9IsNull() {
            addCriterion("factor9 is null");
            return (Criteria) this;
        }

        public Criteria andFactor9IsNotNull() {
            addCriterion("factor9 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor9EqualTo(Double value) {
            addCriterion("factor9 =", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9NotEqualTo(Double value) {
            addCriterion("factor9 <>", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9GreaterThan(Double value) {
            addCriterion("factor9 >", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9GreaterThanOrEqualTo(Double value) {
            addCriterion("factor9 >=", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9LessThan(Double value) {
            addCriterion("factor9 <", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9LessThanOrEqualTo(Double value) {
            addCriterion("factor9 <=", value, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9In(List<Double> values) {
            addCriterion("factor9 in", values, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9NotIn(List<Double> values) {
            addCriterion("factor9 not in", values, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9Between(Double value1, Double value2) {
            addCriterion("factor9 between", value1, value2, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor9NotBetween(Double value1, Double value2) {
            addCriterion("factor9 not between", value1, value2, "factor9");
            return (Criteria) this;
        }

        public Criteria andFactor10IsNull() {
            addCriterion("factor10 is null");
            return (Criteria) this;
        }

        public Criteria andFactor10IsNotNull() {
            addCriterion("factor10 is not null");
            return (Criteria) this;
        }

        public Criteria andFactor10EqualTo(Double value) {
            addCriterion("factor10 =", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10NotEqualTo(Double value) {
            addCriterion("factor10 <>", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10GreaterThan(Double value) {
            addCriterion("factor10 >", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10GreaterThanOrEqualTo(Double value) {
            addCriterion("factor10 >=", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10LessThan(Double value) {
            addCriterion("factor10 <", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10LessThanOrEqualTo(Double value) {
            addCriterion("factor10 <=", value, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10In(List<Double> values) {
            addCriterion("factor10 in", values, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10NotIn(List<Double> values) {
            addCriterion("factor10 not in", values, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10Between(Double value1, Double value2) {
            addCriterion("factor10 between", value1, value2, "factor10");
            return (Criteria) this;
        }

        public Criteria andFactor10NotBetween(Double value1, Double value2) {
            addCriterion("factor10 not between", value1, value2, "factor10");
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