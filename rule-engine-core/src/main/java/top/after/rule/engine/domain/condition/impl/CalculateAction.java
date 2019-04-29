package top.after.rule.engine.domain.condition.impl;

import top.after.rule.engine.domain.condition.Action;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.entity.DomainEntity;
import top.after.rule.engine.domain.operate.Value;

public class CalculateAction implements Action,Value,DomainEntity{
	private static final long serialVersionUID = -4426642163804539759L;

	private Constructor calculate;
	private String name;
	private String code;
	
	public CalculateAction(Constructor calculate) {
		this.calculate = calculate;
	}

	@Override
	public Object action(DecisionContext context) {
		return get(context);
	}

	@Override
	public Object get(DecisionContext context) {
		return calculate.get(context);
	}
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
