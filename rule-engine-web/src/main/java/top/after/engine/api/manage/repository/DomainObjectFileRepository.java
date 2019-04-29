package top.after.engine.api.manage.repository;

import org.springframework.data.repository.CrudRepository;

import top.after.engine.api.manage.entity.DomainObjectFile;

public interface DomainObjectFileRepository extends CrudRepository<DomainObjectFile, Long> {

}
