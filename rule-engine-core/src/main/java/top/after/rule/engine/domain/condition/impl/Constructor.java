package top.after.rule.engine.domain.condition.impl;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.operate.Value;

public class Constructor implements Value{
	private static final long serialVersionUID = 5324497282549441616L;

	private DomainObjectDefine define;
	private Value param;
	
	public Constructor(DomainObjectDefine define,Value param) {
		this.define = define;
		this.param = param;
	}

	@Override
	public Object get(DecisionContext context) {
		Object o = param.get(context);
		if("enum".equals(define.getType())) {
			return o;
		}
		return o;
	}

}
