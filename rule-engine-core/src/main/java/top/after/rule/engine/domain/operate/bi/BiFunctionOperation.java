package top.after.rule.engine.domain.operate.bi;

import java.util.function.BiFunction;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class BiFunctionOperation implements Value {
	private static final long serialVersionUID = -9111233992012164203L;
	private BiFunction<Object, Object, Object> function;
	private Value before;
	private Value after;
	
	public BiFunctionOperation(BiFunction<Object, Object, Object> function) {
		super();
		this.function = function;
	}
	
	@Override
	public Object get(DecisionContext context) {
		return function.apply(before.get(context), after.get(context));
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

}
