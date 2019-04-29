package top.after.rule.engine.domain.mapper;

import java.io.Serializable;
import java.util.Map;

import top.after.rule.engine.domain.AttributeKeeper;

public interface DomainVaribleConverter extends Serializable{
	
	Map<String,Object> fromDomain(AttributeKeeper attribute);
	
	AttributeKeeper toDomain(Object javaObject);
	
}
