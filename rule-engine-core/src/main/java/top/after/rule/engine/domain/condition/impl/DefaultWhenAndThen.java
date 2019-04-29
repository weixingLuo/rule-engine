package top.after.rule.engine.domain.condition.impl;

import java.util.Collection;
import java.util.function.Consumer;

import top.after.rule.engine.domain.condition.WhenAndThen;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.PredicateOperation;

public class DefaultWhenAndThen implements WhenAndThen{
	private static final long serialVersionUID = 5304861019215411160L;
	private PredicateOperation condition;
	private Collection<Consumer<DecisionContext>> actions;
	public DefaultWhenAndThen(PredicateOperation condition,
			Collection<Consumer<DecisionContext>> actions){
		this.condition=condition;
		this.actions = actions;
	}
	
	@Override
	public boolean when(DecisionContext context) {
		return condition.test(context);
	}

	@Override
	public void then(DecisionContext context) {
		actions.forEach(o->o.accept(context));
	}

}
