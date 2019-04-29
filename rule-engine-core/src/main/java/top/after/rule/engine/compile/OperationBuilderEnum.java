package top.after.rule.engine.compile;

import top.after.rule.engine.domain.operate.Value;
import top.after.rule.engine.domain.operate.bi.DateOperation;
import top.after.rule.engine.domain.operate.bi.DateOperator;
import top.after.rule.engine.domain.operate.bi.NumberOperation;
import top.after.rule.engine.domain.operate.bi.NumberOperator;
import top.after.rule.engine.domain.operate.predicate.BiPredicateOperation;
import top.after.rule.engine.domain.operate.predicate.BiPredicateOperator;

public enum OperationBuilderEnum {
    BOOLEAN(BiPredicateOperator.class),
    NUMBER(NumberOperator.class),
    DATE(DateOperator.class);

    public Class<? extends Enum> enumType;

    OperationBuilderEnum(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }

    public Enum get(String name) {
        return Enum.valueOf(enumType, name);
    }

    public static Value getOperation(String returnType, String operatorStr) {
        OperationBuilderEnum opEnum = OperationBuilderEnum.valueOf(returnType.toUpperCase());
        Enum operatorEnum = opEnum.get(operatorStr);
        if (operatorEnum instanceof BiPredicateOperator) {
            BiPredicateOperator operator = (BiPredicateOperator) operatorEnum;
            return new BiPredicateOperation(operator.getOperator());
        }
        if (operatorEnum instanceof NumberOperator) {
            NumberOperator operator = (NumberOperator) operatorEnum;
            return new NumberOperation(operator.getOperator());
        }
        if (operatorEnum instanceof DateOperator) {
            DateOperator operator = (DateOperator) operatorEnum;
            return new DateOperation(operator.getOperator());
        }
        throw new IllegalArgumentException("unknown resultType:" + returnType);
    }

    public static void main(String[] args) {
        String returnType = "number";
        String operator = "加上";
        OperationBuilderEnum a = OperationBuilderEnum.valueOf(returnType.toUpperCase());
        Enum operatorEnum = a.get("加上");

        Value value = a.getOperation(returnType, operator);
        System.out.println("vaule =" + value);

        System.out.println(operatorEnum.toString());
    }
}
