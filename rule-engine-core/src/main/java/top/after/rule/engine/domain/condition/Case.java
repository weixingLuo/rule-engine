package top.after.rule.engine.domain.condition;

import top.after.rule.engine.domain.entity.DecisionContext;

public interface Case extends Action{
	boolean test(DecisionContext context);
}
