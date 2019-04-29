package top.after.rule.engine.domain.operate.predicate;

import java.util.Collection;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.PredicateOperation;

public class AndPredicateOperation implements PredicateOperation {
	private static final long serialVersionUID = 2273703650149443384L;

	private Collection<PredicateOperation> conditions;
	
	public AndPredicateOperation(Collection<PredicateOperation> conditions){
		this.conditions = conditions;
	}
	
	@Override
	public boolean test(DecisionContext context) {
		boolean value = conditions.stream().allMatch(o->o.test(context));
		return value;
	}

}
