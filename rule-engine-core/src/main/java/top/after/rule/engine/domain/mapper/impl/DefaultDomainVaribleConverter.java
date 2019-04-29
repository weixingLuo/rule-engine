package top.after.rule.engine.domain.mapper.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.AttributeKeeper;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.entity.HashMapAttributeKeeper;
import top.after.rule.engine.domain.mapper.DomainVaribleConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultDomainVaribleConverter implements DomainVaribleConverter {
	private static final long serialVersionUID = 9125727732660508395L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDomainVaribleConverter.class);
	private Map<String,DomainObjectDefine> nameMapper;
	private Map<String,DomainObjectDefine> codeMapper;
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public DefaultDomainVaribleConverter(DomainObjectDefine objectDefine){
		nameMapper = objectDefine.getFileds();
		codeMapper = initObject2keeper(objectDefine);
	}
	
	@Override
	public Map<String,Object> fromDomain(AttributeKeeper attribute) {
		Map<String,Object> map = new HashMap<>();

		DomainObjectDefine variableDefine;
		for(Map.Entry<String, Object> e:attribute.entrySet()){
			variableDefine = nameMapper.get(e.getKey());
			if(variableDefine == null){
				LOGGER.info(e.getKey()+"无法映射");
				continue;
			}
			map.put(variableDefine.getCode(),e.getValue());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AttributeKeeper toDomain(Object javaObject) {
		Map<String,String> map =null;
		if(javaObject instanceof Map) {
			map = (Map<String,String>) javaObject;
		}else {
			map = objectMapper.convertValue(javaObject, Map.class);
		}
		return map2Domain(map);
	}

	@SuppressWarnings("unused")
	private Object convertValue(String type, String valueStr){
		Object value = null;
		if(type == "date"){
			value = LocalDate.parse(valueStr, DateTimeFormatter.ISO_DATE);
		}else{
			value = valueStr;
		}
		return value;
	}
	
	public AttributeKeeper map2Domain(Map<String,String> map) {
		AttributeKeeper flowMap = new HashMapAttributeKeeper();

		DomainObjectDefine variableDefine;
		for(Map.Entry<String, String> e:map.entrySet()){
			variableDefine = codeMapper.get(e.getKey());
			if(variableDefine == null){
				LOGGER.info(e.getKey()+"无法映射");
				continue;
			}
			Object value = null;
			String type = variableDefine.getType();
			if(type == "date"){
				value = LocalDate.parse(e.getValue(), DateTimeFormatter.ISO_DATE);
			}else if(type == "domainObject") {
				DefaultDomainVaribleConverter c = new DefaultDomainVaribleConverter(variableDefine);
				value =c.toDomain(e.getValue());
			}else{
				value = e.getValue();
			}
			flowMap.put(variableDefine.getName(),value);
		}
		return flowMap;
	}
	
	private Map<String,DomainObjectDefine> initObject2keeper(DomainObjectDefine variableDefine){
		Map<String,DomainObjectDefine> mappings= new HashMap<>();
		Map<String, DomainObjectDefine> fileds = variableDefine.getFileds();
		if(fileds == null 
				|| fileds.isEmpty()){
			return mappings;
		}
		mappings = fileds.values()
				.stream()
				.collect(Collectors.toMap(m->m.getCode(),m->m));
		
		return mappings;
	}
}
