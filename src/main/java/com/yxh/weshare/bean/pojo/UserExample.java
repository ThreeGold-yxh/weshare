package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andWsUserNicknameIsNull() {
            addCriterion("ws_user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameIsNotNull() {
            addCriterion("ws_user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameEqualTo(String value) {
            addCriterion("ws_user_nickname =", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameNotEqualTo(String value) {
            addCriterion("ws_user_nickname <>", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameGreaterThan(String value) {
            addCriterion("ws_user_nickname >", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("ws_user_nickname >=", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameLessThan(String value) {
            addCriterion("ws_user_nickname <", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("ws_user_nickname <=", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameLike(String value) {
            addCriterion("ws_user_nickname like", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameNotLike(String value) {
            addCriterion("ws_user_nickname not like", value, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameIn(List<String> values) {
            addCriterion("ws_user_nickname in", values, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameNotIn(List<String> values) {
            addCriterion("ws_user_nickname not in", values, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameBetween(String value1, String value2) {
            addCriterion("ws_user_nickname between", value1, value2, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserNicknameNotBetween(String value1, String value2) {
            addCriterion("ws_user_nickname not between", value1, value2, "wsUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountIsNull() {
            addCriterion("ws_user_account is null");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountIsNotNull() {
            addCriterion("ws_user_account is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountEqualTo(String value) {
            addCriterion("ws_user_account =", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountNotEqualTo(String value) {
            addCriterion("ws_user_account <>", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountGreaterThan(String value) {
            addCriterion("ws_user_account >", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ws_user_account >=", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountLessThan(String value) {
            addCriterion("ws_user_account <", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountLessThanOrEqualTo(String value) {
            addCriterion("ws_user_account <=", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountLike(String value) {
            addCriterion("ws_user_account like", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountNotLike(String value) {
            addCriterion("ws_user_account not like", value, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountIn(List<String> values) {
            addCriterion("ws_user_account in", values, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountNotIn(List<String> values) {
            addCriterion("ws_user_account not in", values, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountBetween(String value1, String value2) {
            addCriterion("ws_user_account between", value1, value2, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserAccountNotBetween(String value1, String value2) {
            addCriterion("ws_user_account not between", value1, value2, "wsUserAccount");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordIsNull() {
            addCriterion("ws_user_password is null");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordIsNotNull() {
            addCriterion("ws_user_password is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordEqualTo(String value) {
            addCriterion("ws_user_password =", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordNotEqualTo(String value) {
            addCriterion("ws_user_password <>", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordGreaterThan(String value) {
            addCriterion("ws_user_password >", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("ws_user_password >=", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordLessThan(String value) {
            addCriterion("ws_user_password <", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("ws_user_password <=", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordLike(String value) {
            addCriterion("ws_user_password like", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordNotLike(String value) {
            addCriterion("ws_user_password not like", value, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordIn(List<String> values) {
            addCriterion("ws_user_password in", values, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordNotIn(List<String> values) {
            addCriterion("ws_user_password not in", values, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordBetween(String value1, String value2) {
            addCriterion("ws_user_password between", value1, value2, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserPasswordNotBetween(String value1, String value2) {
            addCriterion("ws_user_password not between", value1, value2, "wsUserPassword");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailIsNull() {
            addCriterion("ws_user_email is null");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailIsNotNull() {
            addCriterion("ws_user_email is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailEqualTo(String value) {
            addCriterion("ws_user_email =", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailNotEqualTo(String value) {
            addCriterion("ws_user_email <>", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailGreaterThan(String value) {
            addCriterion("ws_user_email >", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("ws_user_email >=", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailLessThan(String value) {
            addCriterion("ws_user_email <", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailLessThanOrEqualTo(String value) {
            addCriterion("ws_user_email <=", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailLike(String value) {
            addCriterion("ws_user_email like", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailNotLike(String value) {
            addCriterion("ws_user_email not like", value, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailIn(List<String> values) {
            addCriterion("ws_user_email in", values, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailNotIn(List<String> values) {
            addCriterion("ws_user_email not in", values, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailBetween(String value1, String value2) {
            addCriterion("ws_user_email between", value1, value2, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserEmailNotBetween(String value1, String value2) {
            addCriterion("ws_user_email not between", value1, value2, "wsUserEmail");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressIsNull() {
            addCriterion("ws_user_address is null");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressIsNotNull() {
            addCriterion("ws_user_address is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressEqualTo(String value) {
            addCriterion("ws_user_address =", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressNotEqualTo(String value) {
            addCriterion("ws_user_address <>", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressGreaterThan(String value) {
            addCriterion("ws_user_address >", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ws_user_address >=", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressLessThan(String value) {
            addCriterion("ws_user_address <", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressLessThanOrEqualTo(String value) {
            addCriterion("ws_user_address <=", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressLike(String value) {
            addCriterion("ws_user_address like", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressNotLike(String value) {
            addCriterion("ws_user_address not like", value, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressIn(List<String> values) {
            addCriterion("ws_user_address in", values, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressNotIn(List<String> values) {
            addCriterion("ws_user_address not in", values, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressBetween(String value1, String value2) {
            addCriterion("ws_user_address between", value1, value2, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserAddressNotBetween(String value1, String value2) {
            addCriterion("ws_user_address not between", value1, value2, "wsUserAddress");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditIsNull() {
            addCriterion("ws_user_credit is null");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditIsNotNull() {
            addCriterion("ws_user_credit is not null");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditEqualTo(Integer value) {
            addCriterion("ws_user_credit =", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditNotEqualTo(Integer value) {
            addCriterion("ws_user_credit <>", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditGreaterThan(Integer value) {
            addCriterion("ws_user_credit >", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_user_credit >=", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditLessThan(Integer value) {
            addCriterion("ws_user_credit <", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditLessThanOrEqualTo(Integer value) {
            addCriterion("ws_user_credit <=", value, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditIn(List<Integer> values) {
            addCriterion("ws_user_credit in", values, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditNotIn(List<Integer> values) {
            addCriterion("ws_user_credit not in", values, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_credit between", value1, value2, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andWsUserCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_user_credit not between", value1, value2, "wsUserCredit");
            return (Criteria) this;
        }

        public Criteria andAuthorityIsNull() {
            addCriterion("authority is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIsNotNull() {
            addCriterion("authority is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityEqualTo(Integer value) {
            addCriterion("authority =", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotEqualTo(Integer value) {
            addCriterion("authority <>", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityGreaterThan(Integer value) {
            addCriterion("authority >", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("authority >=", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityLessThan(Integer value) {
            addCriterion("authority <", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityLessThanOrEqualTo(Integer value) {
            addCriterion("authority <=", value, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityIn(List<Integer> values) {
            addCriterion("authority in", values, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotIn(List<Integer> values) {
            addCriterion("authority not in", values, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityBetween(Integer value1, Integer value2) {
            addCriterion("authority between", value1, value2, "authority");
            return (Criteria) this;
        }

        public Criteria andAuthorityNotBetween(Integer value1, Integer value2) {
            addCriterion("authority not between", value1, value2, "authority");
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