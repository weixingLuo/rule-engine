package top.after.rule.engine.domain.operate.value;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class DomainObject implements Value {
	private static final long serialVersionUID = 3417105134851558129L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DomainObject.class);
	private String[] path;
	private DomainObjectDefine referenceNode;
	
	public DomainObject(DomainObjectDefine referenceNode,String... path){
		this.referenceNode = referenceNode;
		this.path = path;
	}

	public DomainObjectDefine getReferenceNode() {
		return referenceNode;
	}

	@Override
	public Object get(DecisionContext context) {
		Object object = get(path,context);
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("%s=%s", this.toString(),object));
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	private Object get(String[] path,DecisionContext context) {
		Map<String,Object> currentNode = context;
		int lastNode = path.length -1;
		for(int i=0;i<path.length;i++){
			if(lastNode == i)
				return get(currentNode,path[i]);
			currentNode = (Map<String,Object>)get(currentNode,path[i]);
		}
		return null;
	}
	
	private Object get(Map<String,Object> current,String name){
		if(current==null)
			return null;
		return current.get(name);
	}
	
	public String[] getPath(){
		return path;
	}

	@Override
	public String toString() {
		return "DomainObject [path=" + Arrays.toString(path) + ", referenceNode=" + referenceNode + "]";
	}
	
	
}
