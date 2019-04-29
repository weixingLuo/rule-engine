package top.after.rule.engine.domain.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import top.after.rule.engine.domain.DecisionResultEnum;
import top.after.rule.engine.domain.DomainObjectEnum;

public class Decision implements Serializable {
	private static final long serialVersionUID = -1973720299957268720L;
	protected List<Rule> ruleSet;
	
	public Decision() {
		ruleSet = new LinkedList<Rule>();
	}

	public Optional<DecisionResultEnum> excute(DecisionContext context){
		List<Object> result = new LinkedList<>();
		Map<String, Object> trace = new HashMap<>();
		int i=0;
		for(Rule rule:ruleSet) {
			Object object = rule.get(context);
			trace.put(++i+rule.getName(), object);
			result.add(object);
		}
		
		context.put(DomainObjectEnum.规则明细.name(), trace);
		
		return result.stream()
				.filter(Objects::nonNull)
				.map(Object::toString)
				.map(Integer::parseInt)
				.map(DecisionResultEnum::create)
				.max(DecisionResultEnum::compare);
	}
	
	public void addRule(Rule rule) {
		ruleSet.add(rule);
	}
}
