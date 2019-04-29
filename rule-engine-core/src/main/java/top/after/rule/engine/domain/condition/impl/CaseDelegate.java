package top.after.rule.engine.domain.condition.impl;

import top.after.rule.engine.domain.condition.Case;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.PredicateOperation;
import top.after.rule.engine.domain.operate.Value;

public class CaseDelegate implements Case {

	private Value action;
	private PredicateOperation predicate;
	public CaseDelegate(PredicateOperation predicate,Value action) {
		this.predicate = predicate;
		this.action = action;
	}

	@Override
	public Object action(DecisionContext context) {
		return action.get(context);
	}

	@Override
	public boolean test(DecisionContext context) {
		return predicate.test(context);
	}

	@Override
	public String toString() {
		return "CaseDelegate [action=" + action + ", predicate=" + predicate + "]";
	}

}
