package top.after.rule.engine.domain.operate;

import top.after.rule.engine.domain.entity.DecisionContext;

import java.io.Serializable;

public interface Value extends Serializable {
	Object get(DecisionContext context);
}
