package top.after.rule.engine.domain.operate.unary;

import java.util.function.Function;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class UnaryOperator implements Value {
	private static final long serialVersionUID = -4522555166079154072L;

	private Function<Object, Object> function;
	private Value param;
	
	public UnaryOperator(Function<Object, Object> function) {
		this.function = function;
	}

	@Override
	public Object get(DecisionContext context) {
		Object v = param.get(context);
		return function.apply(v);
	}

}
