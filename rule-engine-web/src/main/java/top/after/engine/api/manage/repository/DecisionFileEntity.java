package top.after.engine.api.manage.repository;

import org.springframework.data.repository.CrudRepository;

import top.after.rule.engine.run.boot.DecisionFileRepository;

public interface DecisionFileEntity extends CrudRepository<Long, DecisionFileEntity>, DecisionFileRepository {

}
