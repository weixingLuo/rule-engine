package top.after.rule.engine.domain.entity;

import java.io.Serializable;

import top.after.rule.engine.domain.operate.Value;

public class Rule implements Serializable,Value {
	private static final long serialVersionUID = 3321675008531596492L;
	private Value rule;
	private String name;
	
	public Rule(Value rule) {
		this.rule = rule;
		this.name = null;
	}

	@Override
	public Object get(DecisionContext context) {
		return rule.get(context);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
