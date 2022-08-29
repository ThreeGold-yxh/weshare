package com.yxh.weshare.bean.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andWsOrderIdIsNull() {
            addCriterion("ws_order_id is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdIsNotNull() {
            addCriterion("ws_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdEqualTo(Integer value) {
            addCriterion("ws_order_id =", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdNotEqualTo(Integer value) {
            addCriterion("ws_order_id <>", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdGreaterThan(Integer value) {
            addCriterion("ws_order_id >", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_order_id >=", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdLessThan(Integer value) {
            addCriterion("ws_order_id <", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_order_id <=", value, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdIn(List<Integer> values) {
            addCriterion("ws_order_id in", values, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdNotIn(List<Integer> values) {
            addCriterion("ws_order_id not in", values, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_id between", value1, value2, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_id not between", value1, value2, "wsOrderId");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusIsNull() {
            addCriterion("ws_order_status is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusIsNotNull() {
            addCriterion("ws_order_status is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusEqualTo(Integer value) {
            addCriterion("ws_order_status =", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusNotEqualTo(Integer value) {
            addCriterion("ws_order_status <>", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusGreaterThan(Integer value) {
            addCriterion("ws_order_status >", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_order_status >=", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusLessThan(Integer value) {
            addCriterion("ws_order_status <", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ws_order_status <=", value, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusIn(List<Integer> values) {
            addCriterion("ws_order_status in", values, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusNotIn(List<Integer> values) {
            addCriterion("ws_order_status not in", values, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_status between", value1, value2, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_status not between", value1, value2, "wsOrderStatus");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdIsNull() {
            addCriterion("ws_order_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdIsNotNull() {
            addCriterion("ws_order_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdEqualTo(Integer value) {
            addCriterion("ws_order_goods_id =", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdNotEqualTo(Integer value) {
            addCriterion("ws_order_goods_id <>", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdGreaterThan(Integer value) {
            addCriterion("ws_order_goods_id >", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_order_goods_id >=", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdLessThan(Integer value) {
            addCriterion("ws_order_goods_id <", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_order_goods_id <=", value, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdIn(List<Integer> values) {
            addCriterion("ws_order_goods_id in", values, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdNotIn(List<Integer> values) {
            addCriterion("ws_order_goods_id not in", values, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_goods_id between", value1, value2, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_goods_id not between", value1, value2, "wsOrderGoodsId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdIsNull() {
            addCriterion("ws_order_buyer_id is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdIsNotNull() {
            addCriterion("ws_order_buyer_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdEqualTo(Integer value) {
            addCriterion("ws_order_buyer_id =", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdNotEqualTo(Integer value) {
            addCriterion("ws_order_buyer_id <>", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdGreaterThan(Integer value) {
            addCriterion("ws_order_buyer_id >", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_order_buyer_id >=", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdLessThan(Integer value) {
            addCriterion("ws_order_buyer_id <", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_order_buyer_id <=", value, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdIn(List<Integer> values) {
            addCriterion("ws_order_buyer_id in", values, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdNotIn(List<Integer> values) {
            addCriterion("ws_order_buyer_id not in", values, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_buyer_id between", value1, value2, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderBuyerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_buyer_id not between", value1, value2, "wsOrderBuyerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdIsNull() {
            addCriterion("ws_order_seller_id is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdIsNotNull() {
            addCriterion("ws_order_seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdEqualTo(Integer value) {
            addCriterion("ws_order_seller_id =", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdNotEqualTo(Integer value) {
            addCriterion("ws_order_seller_id <>", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdGreaterThan(Integer value) {
            addCriterion("ws_order_seller_id >", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_order_seller_id >=", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdLessThan(Integer value) {
            addCriterion("ws_order_seller_id <", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdLessThanOrEqualTo(Integer value) {
            addCriterion("ws_order_seller_id <=", value, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdIn(List<Integer> values) {
            addCriterion("ws_order_seller_id in", values, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdNotIn(List<Integer> values) {
            addCriterion("ws_order_seller_id not in", values, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_seller_id between", value1, value2, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderSellerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_order_seller_id not between", value1, value2, "wsOrderSellerId");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressIsNull() {
            addCriterion("ws_order_address is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressIsNotNull() {
            addCriterion("ws_order_address is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressEqualTo(String value) {
            addCriterion("ws_order_address =", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressNotEqualTo(String value) {
            addCriterion("ws_order_address <>", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressGreaterThan(String value) {
            addCriterion("ws_order_address >", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ws_order_address >=", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressLessThan(String value) {
            addCriterion("ws_order_address <", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressLessThanOrEqualTo(String value) {
            addCriterion("ws_order_address <=", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressLike(String value) {
            addCriterion("ws_order_address like", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressNotLike(String value) {
            addCriterion("ws_order_address not like", value, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressIn(List<String> values) {
            addCriterion("ws_order_address in", values, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressNotIn(List<String> values) {
            addCriterion("ws_order_address not in", values, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressBetween(String value1, String value2) {
            addCriterion("ws_order_address between", value1, value2, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderAddressNotBetween(String value1, String value2) {
            addCriterion("ws_order_address not between", value1, value2, "wsOrderAddress");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceIsNull() {
            addCriterion("ws_order_price is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceIsNotNull() {
            addCriterion("ws_order_price is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceEqualTo(Double value) {
            addCriterion("ws_order_price =", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceNotEqualTo(Double value) {
            addCriterion("ws_order_price <>", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceGreaterThan(Double value) {
            addCriterion("ws_order_price >", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("ws_order_price >=", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceLessThan(Double value) {
            addCriterion("ws_order_price <", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceLessThanOrEqualTo(Double value) {
            addCriterion("ws_order_price <=", value, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceIn(List<Double> values) {
            addCriterion("ws_order_price in", values, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceNotIn(List<Double> values) {
            addCriterion("ws_order_price not in", values, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceBetween(Double value1, Double value2) {
            addCriterion("ws_order_price between", value1, value2, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderPriceNotBetween(Double value1, Double value2) {
            addCriterion("ws_order_price not between", value1, value2, "wsOrderPrice");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateIsNull() {
            addCriterion("ws_order_create_date is null");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateIsNotNull() {
            addCriterion("ws_order_create_date is not null");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateEqualTo(Date value) {
            addCriterion("ws_order_create_date =", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateNotEqualTo(Date value) {
            addCriterion("ws_order_create_date <>", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateGreaterThan(Date value) {
            addCriterion("ws_order_create_date >", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ws_order_create_date >=", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateLessThan(Date value) {
            addCriterion("ws_order_create_date <", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("ws_order_create_date <=", value, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateIn(List<Date> values) {
            addCriterion("ws_order_create_date in", values, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateNotIn(List<Date> values) {
            addCriterion("ws_order_create_date not in", values, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateBetween(Date value1, Date value2) {
            addCriterion("ws_order_create_date between", value1, value2, "wsOrderCreateDate");
            return (Criteria) this;
        }

        public Criteria andWsOrderCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("ws_order_create_date not between", value1, value2, "wsOrderCreateDate");
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