package top.after.rule.engine.domain.condition;

import top.after.rule.engine.domain.entity.DecisionContext;

import java.io.Serializable;

public interface WhenAndThen extends Serializable{
	boolean when(DecisionContext context);
	void then(DecisionContext context);
}
