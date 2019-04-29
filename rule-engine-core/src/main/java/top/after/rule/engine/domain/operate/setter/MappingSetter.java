package top.after.rule.engine.domain.operate.setter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;
import top.after.rule.engine.domain.operate.value.DomainObject;

public class MappingSetter extends OperationSetter{
	private static final long serialVersionUID = -827057656472999651L;
	private static final Logger LOGGER = LoggerFactory.getLogger(MappingSetter.class);
	private Map<Object,Object> mapping;
	
	/**
	 * 
	 * @param formal 形参
	 * @param value  值
	 */
	public MappingSetter(DomainObject formal,Value value,Map<Object,Object> mapping){
		this(formal,value,false,mapping);
	}
	
	public MappingSetter(DomainObject formal,Value value,boolean forceSet,Map<Object,Object> mapping){
		super(formal,value,forceSet);
		this.mapping = mapping;
	}
	
	@Override
	public void accept(DecisionContext context) {
		Object v = getValue(context);
		Object result = map(v);
		setValue(context, result);
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("mapping:"+mapping.toString());
			LOGGER.debug(String.format("map:'%s' to '%s'", v,result));
		}
	}
	
	private Object map(Object v){
		if(v==null)
			return null;
		Object result = mapping.get(v.toString());
		if(result == null)
			return mapping.get("default");
		return result;
	}

	@Override
	public String toString() {
		return "MappingSetter "+super.toString()+ " mapping=" + mapping + "]";
	}
}
