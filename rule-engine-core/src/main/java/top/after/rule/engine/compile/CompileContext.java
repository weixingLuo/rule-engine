package top.after.rule.engine.compile;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import top.after.rule.engine.domain.DomainObjectEnum;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.operate.value.DomainObject;

public class CompileContext {
	private static final ThreadLocal<CompileContext> compileContext = new ThreadLocal<>();
	
	private Stack<String> domainVariablePath;
	private DomainObjectBuilder domainObjectBuilder;
//	private List<DomainEntity> domainVariable;
	private Map<String, Object> defines;
	
	public static CompileContext get(){
		return compileContext.get();
	}
	private CompileContext(){
		domainVariablePath = new Stack<>();
		defines = new HashMap<>();
	}
	public static void init(DomainObjectDefine rootDomain){
		CompileContext value=new CompileContext();
		value.domainObjectBuilder = new DomainObjectBuilder(rootDomain);
		value.domainVariableGoInto(DomainObjectEnum.上下文.name());
		compileContext.set(value);
	}
	public static void destroy(){
		compileContext.set(null);
	}
	
	public void domainVariableGoInto(String variable){
		domainVariablePath.push(variable);
	}
	public void domainVariableGoOut(){
		domainVariablePath.pop();
	}
	public String[] getPath(){
		return domainVariablePath.stream().toArray(String[]::new);
	}
	
	public DomainObject buildDomainObject(String placeHolder){
		return domainObjectBuilder.buildDomainObject(placeHolder);
	}
	public DomainObject createDomainObject(String placeHolder, DomainObjectDefine objectDefine){
		return domainObjectBuilder.createDomainObject(placeHolder, objectDefine);
	}

	public void putDefine(String key,Object value) {
		defines.put(key, value);
	}
	
	public Object getDefine(String key) {
		return defines.get(key);
	}
}
