package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andWsCommentIdIsNull() {
            addCriterion("ws_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdIsNotNull() {
            addCriterion("ws_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdEqualTo(Integer value) {
            addCriterion("ws_comment_id =", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdNotEqualTo(Integer value) {
            addCriterion("ws_comment_id <>", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdGreaterThan(Integer value) {
            addCriterion("ws_comment_id >", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_id >=", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdLessThan(Integer value) {
            addCriterion("ws_comment_id <", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_id <=", value, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdIn(List<Integer> values) {
            addCriterion("ws_comment_id in", values, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdNotIn(List<Integer> values) {
            addCriterion("ws_comment_id not in", values, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_id between", value1, value2, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_id not between", value1, value2, "wsCommentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameIsNull() {
            addCriterion("ws_comment_user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameIsNotNull() {
            addCriterion("ws_comment_user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameEqualTo(String value) {
            addCriterion("ws_comment_user_nickname =", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameNotEqualTo(String value) {
            addCriterion("ws_comment_user_nickname <>", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameGreaterThan(String value) {
            addCriterion("ws_comment_user_nickname >", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("ws_comment_user_nickname >=", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameLessThan(String value) {
            addCriterion("ws_comment_user_nickname <", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("ws_comment_user_nickname <=", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameLike(String value) {
            addCriterion("ws_comment_user_nickname like", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameNotLike(String value) {
            addCriterion("ws_comment_user_nickname not like", value, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameIn(List<String> values) {
            addCriterion("ws_comment_user_nickname in", values, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameNotIn(List<String> values) {
            addCriterion("ws_comment_user_nickname not in", values, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameBetween(String value1, String value2) {
            addCriterion("ws_comment_user_nickname between", value1, value2, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentUserNicknameNotBetween(String value1, String value2) {
            addCriterion("ws_comment_user_nickname not between", value1, value2, "wsCommentUserNickname");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentIsNull() {
            addCriterion("ws_comment_content is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentIsNotNull() {
            addCriterion("ws_comment_content is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentEqualTo(String value) {
            addCriterion("ws_comment_content =", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentNotEqualTo(String value) {
            addCriterion("ws_comment_content <>", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentGreaterThan(String value) {
            addCriterion("ws_comment_content >", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentGreaterThanOrEqualTo(String value) {
            addCriterion("ws_comment_content >=", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentLessThan(String value) {
            addCriterion("ws_comment_content <", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentLessThanOrEqualTo(String value) {
            addCriterion("ws_comment_content <=", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentLike(String value) {
            addCriterion("ws_comment_content like", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentNotLike(String value) {
            addCriterion("ws_comment_content not like", value, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentIn(List<String> values) {
            addCriterion("ws_comment_content in", values, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentNotIn(List<String> values) {
            addCriterion("ws_comment_content not in", values, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentBetween(String value1, String value2) {
            addCriterion("ws_comment_content between", value1, value2, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentContentNotBetween(String value1, String value2) {
            addCriterion("ws_comment_content not between", value1, value2, "wsCommentContent");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateIsNull() {
            addCriterion("ws_comment_publish_date is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateIsNotNull() {
            addCriterion("ws_comment_publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateEqualTo(Date value) {
            addCriterion("ws_comment_publish_date =", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateNotEqualTo(Date value) {
            addCriterion("ws_comment_publish_date <>", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateGreaterThan(Date value) {
            addCriterion("ws_comment_publish_date >", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ws_comment_publish_date >=", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateLessThan(Date value) {
            addCriterion("ws_comment_publish_date <", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateLessThanOrEqualTo(Date value) {
            addCriterion("ws_comment_publish_date <=", value, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateIn(List<Date> values) {
            addCriterion("ws_comment_publish_date in", values, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateNotIn(List<Date> values) {
            addCriterion("ws_comment_publish_date not in", values, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateBetween(Date value1, Date value2) {
            addCriterion("ws_comment_publish_date between", value1, value2, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentPublishDateNotBetween(Date value1, Date value2) {
            addCriterion("ws_comment_publish_date not between", value1, value2, "wsCommentPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdIsNull() {
            addCriterion("ws_comment_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdIsNotNull() {
            addCriterion("ws_comment_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdEqualTo(Integer value) {
            addCriterion("ws_comment_parent_id =", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdNotEqualTo(Integer value) {
            addCriterion("ws_comment_parent_id <>", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdGreaterThan(Integer value) {
            addCriterion("ws_comment_parent_id >", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_parent_id >=", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdLessThan(Integer value) {
            addCriterion("ws_comment_parent_id <", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_parent_id <=", value, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdIn(List<Integer> values) {
            addCriterion("ws_comment_parent_id in", values, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdNotIn(List<Integer> values) {
            addCriterion("ws_comment_parent_id not in", values, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_parent_id between", value1, value2, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_parent_id not between", value1, value2, "wsCommentParentId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdIsNull() {
            addCriterion("ws_comment_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdIsNotNull() {
            addCriterion("ws_comment_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdEqualTo(Integer value) {
            addCriterion("ws_comment_goods_id =", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdNotEqualTo(Integer value) {
            addCriterion("ws_comment_goods_id <>", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdGreaterThan(Integer value) {
            addCriterion("ws_comment_goods_id >", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_goods_id >=", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdLessThan(Integer value) {
            addCriterion("ws_comment_goods_id <", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_comment_goods_id <=", value, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdIn(List<Integer> values) {
            addCriterion("ws_comment_goods_id in", values, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdNotIn(List<Integer> values) {
            addCriterion("ws_comment_goods_id not in", values, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_goods_id between", value1, value2, "wsCommentGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsCommentGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_comment_goods_id not between", value1, value2, "wsCommentGoodsId");
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