package top.after.rule.engine.domain.operate.setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.AttributeKeeper;
import top.after.rule.engine.domain.entity.HashMapAttributeKeeper;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;
import top.after.rule.engine.domain.operate.value.DomainObject;

public class OperationSetter implements Consumer<DecisionContext>,Serializable{
	private static final long serialVersionUID = 8261404284231874134L;
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationSetter.class);
	private DomainObject formal;
	private Value value;
	private boolean forceSet;
	/**
	 * 
	 * @param formal 形参
	 * @param value  值
	 */
	public OperationSetter(DomainObject formal,Value value){
		this(formal,value,false);
	}
	
	public OperationSetter(DomainObject formal,Value value,boolean forceSet){
		this.formal = formal;
		this.value = value;
		this.forceSet = forceSet;
	}
	
	@Override
	public void accept(DecisionContext context) {
		Object v = getValue(context);
		setValue(context, v);
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("formal:'%s',value:'%s'", formal,v));
		}
	}

	protected void setValue(DecisionContext context, Object v) {
		String[] path = formal.getPath();
		if(path.length==1){
			context.put(path[0], v);
			return;
		}
		int lastIndex = path.length-1;
		String[] parentPath = Arrays.copyOf(path, lastIndex);
		AttributeKeeper parent =  getParaentAndCreateIfAbsent(parentPath,context);
		String nodeName = path[lastIndex];
		
		if(parent.get(nodeName)!=null && !forceSet){
			return;
		}
		parent.put(nodeName, v);
	}

	protected Object getValue(DecisionContext context) {
		Object v = value.get(context);
		return v;
	}

	protected AttributeKeeper getParaentAndCreateIfAbsent(String[] path,AttributeKeeper context){
		if(path.length==1)
			return getParaentAndCreateIfAbsent(path[0],context);
		
		String[] parentPath = Arrays.copyOf(path, path.length-1);
		AttributeKeeper parent =  getParaentAndCreateIfAbsent(parentPath,context);
		return getParaentAndCreateIfAbsent(path[path.length-1],parent);
	}

	
	private AttributeKeeper getParaentAndCreateIfAbsent(String path,AttributeKeeper context){
		AttributeKeeper node = (AttributeKeeper)context.get(path);
		if(node == null){
			node = new HashMapAttributeKeeper();
			context.put(path, node);
		}
		return node;
	}

	@Override
	public String toString() {
		return "[formal=" + formal + ", value=" + value + "]";
	}
	
}
