package com.family.financial.management.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class GroupsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GroupsExample() {
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

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupMembersIsNull() {
            addCriterion("group_members is null");
            return (Criteria) this;
        }

        public Criteria andGroupMembersIsNotNull() {
            addCriterion("group_members is not null");
            return (Criteria) this;
        }

        public Criteria andGroupMembersEqualTo(String value) {
            addCriterion("group_members =", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersNotEqualTo(String value) {
            addCriterion("group_members <>", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersGreaterThan(String value) {
            addCriterion("group_members >", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersGreaterThanOrEqualTo(String value) {
            addCriterion("group_members >=", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersLessThan(String value) {
            addCriterion("group_members <", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersLessThanOrEqualTo(String value) {
            addCriterion("group_members <=", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersLike(String value) {
            addCriterion("group_members like", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersNotLike(String value) {
            addCriterion("group_members not like", value, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersIn(List<String> values) {
            addCriterion("group_members in", values, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersNotIn(List<String> values) {
            addCriterion("group_members not in", values, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersBetween(String value1, String value2) {
            addCriterion("group_members between", value1, value2, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andGroupMembersNotBetween(String value1, String value2) {
            addCriterion("group_members not between", value1, value2, "groupMembers");
            return (Criteria) this;
        }

        public Criteria andExtNo1IsNull() {
            addCriterion("ext_no1 is null");
            return (Criteria) this;
        }

        public Criteria andExtNo1IsNotNull() {
            addCriterion("ext_no1 is not null");
            return (Criteria) this;
        }

        public Criteria andExtNo1EqualTo(Long value) {
            addCriterion("ext_no1 =", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1NotEqualTo(Long value) {
            addCriterion("ext_no1 <>", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1GreaterThan(Long value) {
            addCriterion("ext_no1 >", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1GreaterThanOrEqualTo(Long value) {
            addCriterion("ext_no1 >=", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1LessThan(Long value) {
            addCriterion("ext_no1 <", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1LessThanOrEqualTo(Long value) {
            addCriterion("ext_no1 <=", value, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1In(List<Long> values) {
            addCriterion("ext_no1 in", values, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1NotIn(List<Long> values) {
            addCriterion("ext_no1 not in", values, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1Between(Long value1, Long value2) {
            addCriterion("ext_no1 between", value1, value2, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo1NotBetween(Long value1, Long value2) {
            addCriterion("ext_no1 not between", value1, value2, "extNo1");
            return (Criteria) this;
        }

        public Criteria andExtNo2IsNull() {
            addCriterion("ext_no2 is null");
            return (Criteria) this;
        }

        public Criteria andExtNo2IsNotNull() {
            addCriterion("ext_no2 is not null");
            return (Criteria) this;
        }

        public Criteria andExtNo2EqualTo(String value) {
            addCriterion("ext_no2 =", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2NotEqualTo(String value) {
            addCriterion("ext_no2 <>", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2GreaterThan(String value) {
            addCriterion("ext_no2 >", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2GreaterThanOrEqualTo(String value) {
            addCriterion("ext_no2 >=", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2LessThan(String value) {
            addCriterion("ext_no2 <", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2LessThanOrEqualTo(String value) {
            addCriterion("ext_no2 <=", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2Like(String value) {
            addCriterion("ext_no2 like", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2NotLike(String value) {
            addCriterion("ext_no2 not like", value, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2In(List<String> values) {
            addCriterion("ext_no2 in", values, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2NotIn(List<String> values) {
            addCriterion("ext_no2 not in", values, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2Between(String value1, String value2) {
            addCriterion("ext_no2 between", value1, value2, "extNo2");
            return (Criteria) this;
        }

        public Criteria andExtNo2NotBetween(String value1, String value2) {
            addCriterion("ext_no2 not between", value1, value2, "extNo2");
            return (Criteria) this;
        }

        public Criteria andGroupManagerIsNull() {
            addCriterion("group_manager is null");
            return (Criteria) this;
        }

        public Criteria andGroupManagerIsNotNull() {
            addCriterion("group_manager is not null");
            return (Criteria) this;
        }

        public Criteria andGroupManagerEqualTo(Long value) {
            addCriterion("group_manager =", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerNotEqualTo(Long value) {
            addCriterion("group_manager <>", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerGreaterThan(Long value) {
            addCriterion("group_manager >", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerGreaterThanOrEqualTo(Long value) {
            addCriterion("group_manager >=", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerLessThan(Long value) {
            addCriterion("group_manager <", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerLessThanOrEqualTo(Long value) {
            addCriterion("group_manager <=", value, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerIn(List<Long> values) {
            addCriterion("group_manager in", values, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerNotIn(List<Long> values) {
            addCriterion("group_manager not in", values, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerBetween(Long value1, Long value2) {
            addCriterion("group_manager between", value1, value2, "groupManager");
            return (Criteria) this;
        }

        public Criteria andGroupManagerNotBetween(Long value1, Long value2) {
            addCriterion("group_manager not between", value1, value2, "groupManager");
            return (Criteria) this;
        }

        public Criteria andAllIncomeIsNull() {
            addCriterion("all_income is null");
            return (Criteria) this;
        }

        public Criteria andAllIncomeIsNotNull() {
            addCriterion("all_income is not null");
            return (Criteria) this;
        }

        public Criteria andAllIncomeEqualTo(Long value) {
            addCriterion("all_income =", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeNotEqualTo(Long value) {
            addCriterion("all_income <>", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeGreaterThan(Long value) {
            addCriterion("all_income >", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeGreaterThanOrEqualTo(Long value) {
            addCriterion("all_income >=", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeLessThan(Long value) {
            addCriterion("all_income <", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeLessThanOrEqualTo(Long value) {
            addCriterion("all_income <=", value, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeIn(List<Long> values) {
            addCriterion("all_income in", values, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeNotIn(List<Long> values) {
            addCriterion("all_income not in", values, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeBetween(Long value1, Long value2) {
            addCriterion("all_income between", value1, value2, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllIncomeNotBetween(Long value1, Long value2) {
            addCriterion("all_income not between", value1, value2, "allIncome");
            return (Criteria) this;
        }

        public Criteria andAllSpendingIsNull() {
            addCriterion("all_spending is null");
            return (Criteria) this;
        }

        public Criteria andAllSpendingIsNotNull() {
            addCriterion("all_spending is not null");
            return (Criteria) this;
        }

        public Criteria andAllSpendingEqualTo(Long value) {
            addCriterion("all_spending =", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingNotEqualTo(Long value) {
            addCriterion("all_spending <>", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingGreaterThan(Long value) {
            addCriterion("all_spending >", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingGreaterThanOrEqualTo(Long value) {
            addCriterion("all_spending >=", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingLessThan(Long value) {
            addCriterion("all_spending <", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingLessThanOrEqualTo(Long value) {
            addCriterion("all_spending <=", value, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingIn(List<Long> values) {
            addCriterion("all_spending in", values, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingNotIn(List<Long> values) {
            addCriterion("all_spending not in", values, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingBetween(Long value1, Long value2) {
            addCriterion("all_spending between", value1, value2, "allSpending");
            return (Criteria) this;
        }

        public Criteria andAllSpendingNotBetween(Long value1, Long value2) {
            addCriterion("all_spending not between", value1, value2, "allSpending");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Long value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Long value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Long value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Long value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Long value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Long> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Long> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Long value1, Long value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Long value1, Long value2) {
            addCriterion("balance not between", value1, value2, "balance");
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