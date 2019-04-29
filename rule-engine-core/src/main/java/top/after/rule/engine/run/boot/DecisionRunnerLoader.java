package top.after.rule.engine.run.boot;

import top.after.rule.engine.run.service.DecisionRunner;

public interface DecisionRunnerLoader {
	public DecisionRunner lookup(String jndiName);
}
