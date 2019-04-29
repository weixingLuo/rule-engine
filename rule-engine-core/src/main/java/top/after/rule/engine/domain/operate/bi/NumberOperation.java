package top.after.rule.engine.domain.operate.bi;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

import java.math.BigDecimal;

public class NumberOperation implements Value {
	private static final long serialVersionUID = -4266352572697154543L;
	private BiFunctionSerializable<Number, Number, Number> function;
	private Value before;
	private Value after;
	
	public NumberOperation(BiFunctionSerializable<Number, Number, Number> function) {
		super();
		this.function = function;
	}
	
	public Number calculte(Number a, Number b){
//		return math.addImpl(a, b);
		return null;
	}
	@Override
	public Object get(DecisionContext context) {
		Object a = before.get(context);
		Object b = after.get(context);
		return function.apply(convert(a), convert(b));
	}
	
	private Number convert(Object o){
		if(o==null){
			throw new IllegalArgumentException("数据计算参数不能为空");
		}
		return new BigDecimal(o.toString());
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
//		NumberOperator operator = NumberOperator.get(function);
//		return  "NumberOperation[(" + before + ")<" + operator.name()+ ">(" + after + ")]";
		return  "NumberOperation[(" + before + ")<" + function+ ">(" + after + ")]";
	}
}
