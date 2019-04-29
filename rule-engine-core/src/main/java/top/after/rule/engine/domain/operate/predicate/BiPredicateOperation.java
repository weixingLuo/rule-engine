package top.after.rule.engine.domain.operate.predicate;

import java.util.function.BiPredicate;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.PredicateOperation;
import top.after.rule.engine.domain.operate.Value;

public class BiPredicateOperation implements PredicateOperation {
	private static final long serialVersionUID = -5088225815671554273L;
	private BiPredicateSerializable<Object, Object> biPredicate;
	private Value before;
	private Value after;
	
	public BiPredicateOperation(BiPredicateSerializable<Object, Object> biPredicate){
		this.biPredicate = biPredicate;
	}
	
	@Override
	public boolean test(DecisionContext context) {
		return biPredicate.test(before.get(context), after.get(context));
	}

	public void setBefore(Value before) {
		this.before = before;
	}

	public void setAfter(Value after) {
		this.after = after;
	}

	public BiPredicate<Object, Object> getBiPredicate() {
		return biPredicate;
	}

	public void setBiPredicate(BiPredicateSerializable<Object, Object> biPredicate) {
		this.biPredicate = biPredicate;
	}

	public Value getBefore() {
		return before;
	}

	public Value getAfter() {
		return after;
	}

	@Override
	public String toString() {
		return "BiPredicateOperation [biPredicate=" + biPredicate + ", before=" + before + ", after=" + after + "]";
	}

}
