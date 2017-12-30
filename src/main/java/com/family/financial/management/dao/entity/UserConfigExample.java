package com.family.financial.management.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class UserConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UserConfigExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAllowType1IsNull() {
            addCriterion("allow_type1 is null");
            return (Criteria) this;
        }

        public Criteria andAllowType1IsNotNull() {
            addCriterion("allow_type1 is not null");
            return (Criteria) this;
        }

        public Criteria andAllowType1EqualTo(Integer value) {
            addCriterion("allow_type1 =", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1NotEqualTo(Integer value) {
            addCriterion("allow_type1 <>", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1GreaterThan(Integer value) {
            addCriterion("allow_type1 >", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1GreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_type1 >=", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1LessThan(Integer value) {
            addCriterion("allow_type1 <", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1LessThanOrEqualTo(Integer value) {
            addCriterion("allow_type1 <=", value, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1In(List<Integer> values) {
            addCriterion("allow_type1 in", values, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1NotIn(List<Integer> values) {
            addCriterion("allow_type1 not in", values, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1Between(Integer value1, Integer value2) {
            addCriterion("allow_type1 between", value1, value2, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType1NotBetween(Integer value1, Integer value2) {
            addCriterion("allow_type1 not between", value1, value2, "allowType1");
            return (Criteria) this;
        }

        public Criteria andAllowType2IsNull() {
            addCriterion("allow_type2 is null");
            return (Criteria) this;
        }

        public Criteria andAllowType2IsNotNull() {
            addCriterion("allow_type2 is not null");
            return (Criteria) this;
        }

        public Criteria andAllowType2EqualTo(Integer value) {
            addCriterion("allow_type2 =", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2NotEqualTo(Integer value) {
            addCriterion("allow_type2 <>", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2GreaterThan(Integer value) {
            addCriterion("allow_type2 >", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2GreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_type2 >=", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2LessThan(Integer value) {
            addCriterion("allow_type2 <", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2LessThanOrEqualTo(Integer value) {
            addCriterion("allow_type2 <=", value, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2In(List<Integer> values) {
            addCriterion("allow_type2 in", values, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2NotIn(List<Integer> values) {
            addCriterion("allow_type2 not in", values, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2Between(Integer value1, Integer value2) {
            addCriterion("allow_type2 between", value1, value2, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType2NotBetween(Integer value1, Integer value2) {
            addCriterion("allow_type2 not between", value1, value2, "allowType2");
            return (Criteria) this;
        }

        public Criteria andAllowType3IsNull() {
            addCriterion("allow_type3 is null");
            return (Criteria) this;
        }

        public Criteria andAllowType3IsNotNull() {
            addCriterion("allow_type3 is not null");
            return (Criteria) this;
        }

        public Criteria andAllowType3EqualTo(Integer value) {
            addCriterion("allow_type3 =", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3NotEqualTo(Integer value) {
            addCriterion("allow_type3 <>", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3GreaterThan(Integer value) {
            addCriterion("allow_type3 >", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3GreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_type3 >=", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3LessThan(Integer value) {
            addCriterion("allow_type3 <", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3LessThanOrEqualTo(Integer value) {
            addCriterion("allow_type3 <=", value, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3In(List<Integer> values) {
            addCriterion("allow_type3 in", values, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3NotIn(List<Integer> values) {
            addCriterion("allow_type3 not in", values, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3Between(Integer value1, Integer value2) {
            addCriterion("allow_type3 between", value1, value2, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType3NotBetween(Integer value1, Integer value2) {
            addCriterion("allow_type3 not between", value1, value2, "allowType3");
            return (Criteria) this;
        }

        public Criteria andAllowType4IsNull() {
            addCriterion("allow_type4 is null");
            return (Criteria) this;
        }

        public Criteria andAllowType4IsNotNull() {
            addCriterion("allow_type4 is not null");
            return (Criteria) this;
        }

        public Criteria andAllowType4EqualTo(Integer value) {
            addCriterion("allow_type4 =", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4NotEqualTo(Integer value) {
            addCriterion("allow_type4 <>", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4GreaterThan(Integer value) {
            addCriterion("allow_type4 >", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4GreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_type4 >=", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4LessThan(Integer value) {
            addCriterion("allow_type4 <", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4LessThanOrEqualTo(Integer value) {
            addCriterion("allow_type4 <=", value, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4In(List<Integer> values) {
            addCriterion("allow_type4 in", values, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4NotIn(List<Integer> values) {
            addCriterion("allow_type4 not in", values, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4Between(Integer value1, Integer value2) {
            addCriterion("allow_type4 between", value1, value2, "allowType4");
            return (Criteria) this;
        }

        public Criteria andAllowType4NotBetween(Integer value1, Integer value2) {
            addCriterion("allow_type4 not between", value1, value2, "allowType4");
            return (Criteria) this;
        }
    }

    /**
     */
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