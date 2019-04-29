package top.after.rule.engine.run.boot;

import java.util.Optional;

public interface DecisionFileRepository {
	Optional<DecisionFile> findByName(String name);
}
