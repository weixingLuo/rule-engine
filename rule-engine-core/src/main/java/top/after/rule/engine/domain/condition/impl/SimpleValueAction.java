package top.after.rule.engine.domain.condition.impl;

import top.after.rule.engine.domain.condition.Action;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class SimpleValueAction implements Action, Value{
	private static final long serialVersionUID = -7725752030386426945L;
	private Value value;
	
	public SimpleValueAction(Value value) {
		this.value = value;
	}

	@Override
	public Object action(DecisionContext context) {
		return get(context);
	}

	@Override
	public Object get(DecisionContext context) {
		return value.get(context);
	}

}
