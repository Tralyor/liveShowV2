package org.liveshow.entity;

import java.util.ArrayList;
import java.util.List;

public class LearnRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LearnRecordExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
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

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(Integer value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(Integer value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(Integer value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(Integer value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<Integer> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<Integer> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andGmtInIsNull() {
            addCriterion("gmt_in is null");
            return (Criteria) this;
        }

        public Criteria andGmtInIsNotNull() {
            addCriterion("gmt_in is not null");
            return (Criteria) this;
        }

        public Criteria andGmtInEqualTo(String value) {
            addCriterion("gmt_in =", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInNotEqualTo(String value) {
            addCriterion("gmt_in <>", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInGreaterThan(String value) {
            addCriterion("gmt_in >", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_in >=", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInLessThan(String value) {
            addCriterion("gmt_in <", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInLessThanOrEqualTo(String value) {
            addCriterion("gmt_in <=", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInLike(String value) {
            addCriterion("gmt_in like", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInNotLike(String value) {
            addCriterion("gmt_in not like", value, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInIn(List<String> values) {
            addCriterion("gmt_in in", values, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInNotIn(List<String> values) {
            addCriterion("gmt_in not in", values, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInBetween(String value1, String value2) {
            addCriterion("gmt_in between", value1, value2, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtInNotBetween(String value1, String value2) {
            addCriterion("gmt_in not between", value1, value2, "gmtIn");
            return (Criteria) this;
        }

        public Criteria andGmtOutIsNull() {
            addCriterion("gmt_out is null");
            return (Criteria) this;
        }

        public Criteria andGmtOutIsNotNull() {
            addCriterion("gmt_out is not null");
            return (Criteria) this;
        }

        public Criteria andGmtOutEqualTo(String value) {
            addCriterion("gmt_out =", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutNotEqualTo(String value) {
            addCriterion("gmt_out <>", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutGreaterThan(String value) {
            addCriterion("gmt_out >", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_out >=", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutLessThan(String value) {
            addCriterion("gmt_out <", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutLessThanOrEqualTo(String value) {
            addCriterion("gmt_out <=", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutLike(String value) {
            addCriterion("gmt_out like", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutNotLike(String value) {
            addCriterion("gmt_out not like", value, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutIn(List<String> values) {
            addCriterion("gmt_out in", values, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutNotIn(List<String> values) {
            addCriterion("gmt_out not in", values, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutBetween(String value1, String value2) {
            addCriterion("gmt_out between", value1, value2, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andGmtOutNotBetween(String value1, String value2) {
            addCriterion("gmt_out not between", value1, value2, "gmtOut");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountIsNull() {
            addCriterion("face_rego_count is null");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountIsNotNull() {
            addCriterion("face_rego_count is not null");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountEqualTo(Integer value) {
            addCriterion("face_rego_count =", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountNotEqualTo(Integer value) {
            addCriterion("face_rego_count <>", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountGreaterThan(Integer value) {
            addCriterion("face_rego_count >", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("face_rego_count >=", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountLessThan(Integer value) {
            addCriterion("face_rego_count <", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountLessThanOrEqualTo(Integer value) {
            addCriterion("face_rego_count <=", value, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountIn(List<Integer> values) {
            addCriterion("face_rego_count in", values, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountNotIn(List<Integer> values) {
            addCriterion("face_rego_count not in", values, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountBetween(Integer value1, Integer value2) {
            addCriterion("face_rego_count between", value1, value2, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoCountNotBetween(Integer value1, Integer value2) {
            addCriterion("face_rego_count not between", value1, value2, "faceRegoCount");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessIsNull() {
            addCriterion("face_rego_success is null");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessIsNotNull() {
            addCriterion("face_rego_success is not null");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessEqualTo(Integer value) {
            addCriterion("face_rego_success =", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessNotEqualTo(Integer value) {
            addCriterion("face_rego_success <>", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessGreaterThan(Integer value) {
            addCriterion("face_rego_success >", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessGreaterThanOrEqualTo(Integer value) {
            addCriterion("face_rego_success >=", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessLessThan(Integer value) {
            addCriterion("face_rego_success <", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessLessThanOrEqualTo(Integer value) {
            addCriterion("face_rego_success <=", value, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessIn(List<Integer> values) {
            addCriterion("face_rego_success in", values, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessNotIn(List<Integer> values) {
            addCriterion("face_rego_success not in", values, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessBetween(Integer value1, Integer value2) {
            addCriterion("face_rego_success between", value1, value2, "faceRegoSuccess");
            return (Criteria) this;
        }

        public Criteria andFaceRegoSuccessNotBetween(Integer value1, Integer value2) {
            addCriterion("face_rego_success not between", value1, value2, "faceRegoSuccess");
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