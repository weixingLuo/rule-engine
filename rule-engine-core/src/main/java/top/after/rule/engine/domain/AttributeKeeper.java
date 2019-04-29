package top.after.rule.engine.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 积分计算依赖的上下文对象
 * <ul>
 * IngegralAttributeKeeper的目的是提供一个规则定义可以访问的数据容器。
 * 每个IngegralAttributeKeeper具有以下特点：
 * <li>1. key值是具有业务含义的一个中文字符串</li>
 * <li>2. value值是具有JAVA原生对象或者是一个IngegralAttributeKeeper对象</li>
 * <li>3. 所有key值和value值符合对应的DomainObjectDefine的定义</li>
 * <li>4. 所有实现IngegralAttributeKeeper的类都只在规则计算中使用</li>
 * @author david
 *
 */
public interface AttributeKeeper extends Serializable, Map<String, Object> {
	
}
