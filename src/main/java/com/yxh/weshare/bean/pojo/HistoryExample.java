package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HistoryExample() {
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

        public Criteria andWsHistoryIdIsNull() {
            addCriterion("ws_history_id is null");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdIsNotNull() {
            addCriterion("ws_history_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdEqualTo(Integer value) {
            addCriterion("ws_history_id =", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdNotEqualTo(Integer value) {
            addCriterion("ws_history_id <>", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdGreaterThan(Integer value) {
            addCriterion("ws_history_id >", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_history_id >=", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdLessThan(Integer value) {
            addCriterion("ws_history_id <", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_history_id <=", value, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdIn(List<Integer> values) {
            addCriterion("ws_history_id in", values, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdNotIn(List<Integer> values) {
            addCriterion("ws_history_id not in", values, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_history_id between", value1, value2, "wsHistoryId");
            return (Criteria) this;
        }

        public Criteria andWsHistoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_history_id not between", value1, value2, "wsHistoryId");
            return (Criteria) this;
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

        public Criteria andWsHistoryCreateDateIsNull() {
            addCriterion("ws_history_create_date is null");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateIsNotNull() {
            addCriterion("ws_history_create_date is not null");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateEqualTo(Date value) {
            addCriterion("ws_history_create_date =", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateNotEqualTo(Date value) {
            addCriterion("ws_history_create_date <>", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateGreaterThan(Date value) {
            addCriterion("ws_history_create_date >", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ws_history_create_date >=", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateLessThan(Date value) {
            addCriterion("ws_history_create_date <", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("ws_history_create_date <=", value, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateIn(List<Date> values) {
            addCriterion("ws_history_create_date in", values, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateNotIn(List<Date> values) {
            addCriterion("ws_history_create_date not in", values, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateBetween(Date value1, Date value2) {
            addCriterion("ws_history_create_date between", value1, value2, "wsHistoryCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsHistoryCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("ws_history_create_date not between", value1, value2, "wsHistoryCreateDate");
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