package top.after.rule.engine.domain.operate;

import top.after.rule.engine.domain.entity.DecisionContext;

public interface PredicateOperation extends Value{
	boolean test(DecisionContext context);
	
	default Object get(DecisionContext context){
		return test(context);
	}
}
