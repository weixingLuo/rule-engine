package top.after.rule.engine.domain.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * 积分计算领域对象<br/>
 * 
 * DomainObjectDefine定义业务可读的领域对象。
 * DomainObjectDefine由符合object-define.xsd规范的xml转换而来。
 * 
 * @author david
 *
 */
public class DomainObjectDefine implements Serializable,DomainEntity {
	private static final long serialVersionUID = -6373008123392751479L;
	/**
	 * 对象名称
	 * 一般为中文字符串，在积分规则配置时业务人员可以直接使用该字符串。
	 */
	private String name;
	/**
	 * 对象代码
	 * 一般为英文字符串，在数据存储或传输时使用的变量形参。
	 */
	private String code;
	/**
	 * 对象类型
	 * java原生类型，java对象，DomainVariable
	 */
	private String type;
	private Map<String,DomainObjectDefine> fileds = Collections.emptyMap();

	public DomainObjectDefine(String name) {
		super();
		this.name = name;
	}
	public DomainObjectDefine(String name,String code,String type) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, DomainObjectDefine> getFileds() {
		return fileds;
	}
	public void setFileds(Map<String, DomainObjectDefine> fileds) {
		this.fileds = fileds;
	}

	public String getCode() {
		return code;
	}

	public DomainObjectDefine setCode(String code) {
		this.code = code;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "'" + name + "'对象" ;
	}
}
