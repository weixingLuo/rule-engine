package top.after.rule.engine.domain.condition.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.condition.Action;
import top.after.rule.engine.domain.condition.Case;
import top.after.rule.engine.domain.condition.Switch;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;
public class SwitchValue implements Value, Switch {
	private static final long serialVersionUID = -5516406534090587243L;
	private static final Logger LOGGER = LoggerFactory.getLogger(SwitchValue.class);
	private List<Case> cases;
	private Optional<Action> defaultAction;

	public SwitchValue() {
		cases = new ArrayList<>();
		defaultAction = Optional.empty();
	}

	public void setDefault(Action action) {
		if(action==null)
			return;
		defaultAction = Optional.of(action);
	}

	public void addCase(Case c) {
		cases.add(c);
	}

	@Override
	public Object get(DecisionContext context) {
		for (Case c : cases) {
			boolean test = c.test(context);
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("test:{} by case {}",test,c);
			}
			if (test) {
				return c.action(context);
			}
		}
		return defaultAction.orElseThrow(() -> new RuntimeException("没有配置默认方案")).action(context);
	}

}
