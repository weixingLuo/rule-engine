package top.after.rule.engine.domain.condition;

import top.after.rule.engine.domain.entity.DecisionContext;

public interface Action {
	Object action(DecisionContext context);
}
