package top.after.rule.engine.domain.operate.bi;

import java.time.LocalDate;

public enum DateOperator {

    前几天(DateOperator::minusDays),
    后几天(DateOperator::plusDays);

    private final BiFunctionSerializable<LocalDate, Integer, LocalDate> operator;

    DateOperator(BiFunctionSerializable<LocalDate, Integer, LocalDate> operator) {
        this.operator = operator;
    }

    public BiFunctionSerializable<LocalDate, Integer, LocalDate> getOperator() {
        return operator;
    }

    public static DateOperator get(BiFunctionSerializable<LocalDate, Integer, LocalDate> operator){
        for(DateOperator o : DateOperator.values() ){
            if (o.getOperator() == operator){
                return o;
            }
        }
        throw new NullPointerException();
    }

    private static LocalDate plusDays(Object before, Object after) {
        LocalDate left = (LocalDate) before;
        int right = (int) after;
        return left.plusDays(right);
    }

    private static LocalDate minusDays(Object before, Object after) {
        LocalDate left = (LocalDate) before;
        int right = (int) after;
        return left.minusDays(right);
    }
}
