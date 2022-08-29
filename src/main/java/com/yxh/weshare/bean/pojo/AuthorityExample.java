package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.List;

public class AuthorityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuthorityExample() {
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

        public Criteria andWsUserAuthorityIsNull() {
            addCriterion("ws_user_authority is null");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityIsNotNull() {
            addCriterion("ws_user_authority is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityEqualTo(Integer value) {
            addCriterion("ws_user_authority =", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityNotEqualTo(Integer value) {
            addCriterion("ws_user_authority <>", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityGreaterThan(Integer value) {
            addCriterion("ws_user_authority >", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_user_authority >=", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityLessThan(Integer value) {
            addCriterion("ws_user_authority <", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityLessThanOrEqualTo(Integer value) {
            addCriterion("ws_user_authority <=", value, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityIn(List<Integer> values) {
            addCriterion("ws_user_authority in", values, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityNotIn(List<Integer> values) {
            addCriterion("ws_user_authority not in", values, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_authority between", value1, value2, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsUserAuthorityNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_authority not between", value1, value2, "wsUserAuthority");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionIsNull() {
            addCriterion("ws_authority_description is null");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionIsNotNull() {
            addCriterion("ws_authority_description is not null");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionEqualTo(String value) {
            addCriterion("ws_authority_description =", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionNotEqualTo(String value) {
            addCriterion("ws_authority_description <>", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionGreaterThan(String value) {
            addCriterion("ws_authority_description >", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("ws_authority_description >=", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionLessThan(String value) {
            addCriterion("ws_authority_description <", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionLessThanOrEqualTo(String value) {
            addCriterion("ws_authority_description <=", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionLike(String value) {
            addCriterion("ws_authority_description like", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionNotLike(String value) {
            addCriterion("ws_authority_description not like", value, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionIn(List<String> values) {
            addCriterion("ws_authority_description in", values, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionNotIn(List<String> values) {
            addCriterion("ws_authority_description not in", values, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionBetween(String value1, String value2) {
            addCriterion("ws_authority_description between", value1, value2, "wsAuthorityDescription");
            return (Criteria) this;
        }

        public Criteria andWsAuthorityDescriptionNotBetween(String value1, String value2) {
            addCriterion("ws_authority_description not between", value1, value2, "wsAuthorityDescription");
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