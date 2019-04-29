package top.after.engine.api.manage.repository;

import org.springframework.data.repository.CrudRepository;

import top.after.rule.engine.run.boot.DecisionFileRepository;

public interface DecisionFileEntityRepository extends CrudRepository<DecisionFileEntityRepository,Long>, DecisionFileRepository {

}
