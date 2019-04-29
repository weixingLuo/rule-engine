package top.after.rule.engine.run.boot;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import top.after.rule.engine.run.boot.impl.DefaultDecisionRunnerLoader;
import top.after.rule.engine.run.boot.impl.LocalDecisionFileRepository;
import top.after.rule.engine.run.service.DecisionRunner;

public class DecisionRunnerLoaderTest {

	public DecisionRunnerLoaderTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() {
		DefaultDecisionRunnerLoader loader = new DefaultDecisionRunnerLoader(new LocalDecisionFileRepository());
		DecisionRunner runner = loader.lookup("/top/after/rule/engine/script/decision.xml");
		assertNotNull(runner);
	}
}
