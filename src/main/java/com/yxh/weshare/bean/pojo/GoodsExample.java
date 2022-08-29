package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
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

        public Criteria andWsGoodsOwnerIdIsNull() {
            addCriterion("ws_goods_owner_id is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdIsNotNull() {
            addCriterion("ws_goods_owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdEqualTo(Integer value) {
            addCriterion("ws_goods_owner_id =", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdNotEqualTo(Integer value) {
            addCriterion("ws_goods_owner_id <>", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdGreaterThan(Integer value) {
            addCriterion("ws_goods_owner_id >", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_owner_id >=", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdLessThan(Integer value) {
            addCriterion("ws_goods_owner_id <", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_owner_id <=", value, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdIn(List<Integer> values) {
            addCriterion("ws_goods_owner_id in", values, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdNotIn(List<Integer> values) {
            addCriterion("ws_goods_owner_id not in", values, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_owner_id between", value1, value2, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsOwnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_owner_id not between", value1, value2, "wsGoodsOwnerId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameIsNull() {
            addCriterion("ws_goods_name is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameIsNotNull() {
            addCriterion("ws_goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameEqualTo(String value) {
            addCriterion("ws_goods_name =", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameNotEqualTo(String value) {
            addCriterion("ws_goods_name <>", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameGreaterThan(String value) {
            addCriterion("ws_goods_name >", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ws_goods_name >=", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameLessThan(String value) {
            addCriterion("ws_goods_name <", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("ws_goods_name <=", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameLike(String value) {
            addCriterion("ws_goods_name like", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameNotLike(String value) {
            addCriterion("ws_goods_name not like", value, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameIn(List<String> values) {
            addCriterion("ws_goods_name in", values, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameNotIn(List<String> values) {
            addCriterion("ws_goods_name not in", values, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameBetween(String value1, String value2) {
            addCriterion("ws_goods_name between", value1, value2, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsNameNotBetween(String value1, String value2) {
            addCriterion("ws_goods_name not between", value1, value2, "wsGoodsName");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageIsNull() {
            addCriterion("ws_goods_image is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageIsNotNull() {
            addCriterion("ws_goods_image is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageEqualTo(String value) {
            addCriterion("ws_goods_image =", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageNotEqualTo(String value) {
            addCriterion("ws_goods_image <>", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageGreaterThan(String value) {
            addCriterion("ws_goods_image >", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageGreaterThanOrEqualTo(String value) {
            addCriterion("ws_goods_image >=", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageLessThan(String value) {
            addCriterion("ws_goods_image <", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageLessThanOrEqualTo(String value) {
            addCriterion("ws_goods_image <=", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageLike(String value) {
            addCriterion("ws_goods_image like", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageNotLike(String value) {
            addCriterion("ws_goods_image not like", value, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageIn(List<String> values) {
            addCriterion("ws_goods_image in", values, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageNotIn(List<String> values) {
            addCriterion("ws_goods_image not in", values, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageBetween(String value1, String value2) {
            addCriterion("ws_goods_image between", value1, value2, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsImageNotBetween(String value1, String value2) {
            addCriterion("ws_goods_image not between", value1, value2, "wsGoodsImage");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionIsNull() {
            addCriterion("ws_goods_description is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionIsNotNull() {
            addCriterion("ws_goods_description is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionEqualTo(String value) {
            addCriterion("ws_goods_description =", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionNotEqualTo(String value) {
            addCriterion("ws_goods_description <>", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionGreaterThan(String value) {
            addCriterion("ws_goods_description >", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("ws_goods_description >=", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionLessThan(String value) {
            addCriterion("ws_goods_description <", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionLessThanOrEqualTo(String value) {
            addCriterion("ws_goods_description <=", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionLike(String value) {
            addCriterion("ws_goods_description like", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionNotLike(String value) {
            addCriterion("ws_goods_description not like", value, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionIn(List<String> values) {
            addCriterion("ws_goods_description in", values, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionNotIn(List<String> values) {
            addCriterion("ws_goods_description not in", values, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionBetween(String value1, String value2) {
            addCriterion("ws_goods_description between", value1, value2, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsDescriptionNotBetween(String value1, String value2) {
            addCriterion("ws_goods_description not between", value1, value2, "wsGoodsDescription");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceIsNull() {
            addCriterion("ws_goods_price is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceIsNotNull() {
            addCriterion("ws_goods_price is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceEqualTo(Double value) {
            addCriterion("ws_goods_price =", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceNotEqualTo(Double value) {
            addCriterion("ws_goods_price <>", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceGreaterThan(Double value) {
            addCriterion("ws_goods_price >", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("ws_goods_price >=", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceLessThan(Double value) {
            addCriterion("ws_goods_price <", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceLessThanOrEqualTo(Double value) {
            addCriterion("ws_goods_price <=", value, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceIn(List<Double> values) {
            addCriterion("ws_goods_price in", values, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceNotIn(List<Double> values) {
            addCriterion("ws_goods_price not in", values, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceBetween(Double value1, Double value2) {
            addCriterion("ws_goods_price between", value1, value2, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPriceNotBetween(Double value1, Double value2) {
            addCriterion("ws_goods_price not between", value1, value2, "wsGoodsPrice");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountIsNull() {
            addCriterion("ws_goods_amount is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountIsNotNull() {
            addCriterion("ws_goods_amount is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountEqualTo(Integer value) {
            addCriterion("ws_goods_amount =", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountNotEqualTo(Integer value) {
            addCriterion("ws_goods_amount <>", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountGreaterThan(Integer value) {
            addCriterion("ws_goods_amount >", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_amount >=", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountLessThan(Integer value) {
            addCriterion("ws_goods_amount <", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountLessThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_amount <=", value, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountIn(List<Integer> values) {
            addCriterion("ws_goods_amount in", values, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountNotIn(List<Integer> values) {
            addCriterion("ws_goods_amount not in", values, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_amount between", value1, value2, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_amount not between", value1, value2, "wsGoodsAmount");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateIsNull() {
            addCriterion("ws_goods_publish_date is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateIsNotNull() {
            addCriterion("ws_goods_publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateEqualTo(Date value) {
            addCriterion("ws_goods_publish_date =", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateNotEqualTo(Date value) {
            addCriterion("ws_goods_publish_date <>", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateGreaterThan(Date value) {
            addCriterion("ws_goods_publish_date >", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ws_goods_publish_date >=", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateLessThan(Date value) {
            addCriterion("ws_goods_publish_date <", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateLessThanOrEqualTo(Date value) {
            addCriterion("ws_goods_publish_date <=", value, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateIn(List<Date> values) {
            addCriterion("ws_goods_publish_date in", values, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateNotIn(List<Date> values) {
            addCriterion("ws_goods_publish_date not in", values, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateBetween(Date value1, Date value2) {
            addCriterion("ws_goods_publish_date between", value1, value2, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsPublishDateNotBetween(Date value1, Date value2) {
            addCriterion("ws_goods_publish_date not between", value1, value2, "wsGoodsPublishDate");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusIsNull() {
            addCriterion("ws_goods_status is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusIsNotNull() {
            addCriterion("ws_goods_status is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusEqualTo(Integer value) {
            addCriterion("ws_goods_status =", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusNotEqualTo(Integer value) {
            addCriterion("ws_goods_status <>", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusGreaterThan(Integer value) {
            addCriterion("ws_goods_status >", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_status >=", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusLessThan(Integer value) {
            addCriterion("ws_goods_status <", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_status <=", value, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusIn(List<Integer> values) {
            addCriterion("ws_goods_status in", values, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusNotIn(List<Integer> values) {
            addCriterion("ws_goods_status not in", values, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_status between", value1, value2, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_status not between", value1, value2, "wsGoodsStatus");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdIsNull() {
            addCriterion("ws_goods_category_id is null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdIsNotNull() {
            addCriterion("ws_goods_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdEqualTo(Integer value) {
            addCriterion("ws_goods_category_id =", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdNotEqualTo(Integer value) {
            addCriterion("ws_goods_category_id <>", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdGreaterThan(Integer value) {
            addCriterion("ws_goods_category_id >", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_category_id >=", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdLessThan(Integer value) {
            addCriterion("ws_goods_category_id <", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_goods_category_id <=", value, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdIn(List<Integer> values) {
            addCriterion("ws_goods_category_id in", values, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdNotIn(List<Integer> values) {
            addCriterion("ws_goods_category_id not in", values, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_category_id between", value1, value2, "wsGoodsCategoryId");
            return (Criteria) this;
        }

        public Criteria andWsGoodsCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_goods_category_id not between", value1, value2, "wsGoodsCategoryId");
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