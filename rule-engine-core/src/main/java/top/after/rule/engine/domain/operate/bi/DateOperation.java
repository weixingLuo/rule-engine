package top.after.rule.engine.domain.operate.bi;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateOperation implements Value {
    private BiFunctionSerializable<LocalDate, Integer, LocalDate> function;
    private Value before;
    private Value after;

    public DateOperation(BiFunctionSerializable<LocalDate, Integer, LocalDate> function) {
        super();
        this.function = function;
    }

    @Override
    public Object get(DecisionContext context) {
        Object a = before.get(context);
        Integer b = Integer.valueOf(after.get(context).toString()) ;
        return function.apply(convert(a), b);
    }

    private static LocalDate convert(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("数据计算参数不能为空");
        }
        try {
            LocalDate date = LocalDate.parse(o.toString());
            return date;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("第一个参数不是（yyyy-MM-dd）日期格式");
        }
    }

    public static void main(String[] args) {
        System.out.println(convert(null));
    }

    public Value getBefore() {
        return before;
    }

    public void setBefore(Value before) {
        this.before = before;
    }

    public Value getAfter() {
        return after;
    }

    public void setAfter(Value after) {
        this.after = after;
    }

    @Override
    public String toString() {
//        DateOperator operator = DateOperator.get(function);
//        return  "DateOperation[ (" + before + ") <" + operator.name()+ "> (" + after + ") ]";
        return  "DateOperation[ (" + before + ") <" + function + "> (" + after + ") ]";
    }
}
