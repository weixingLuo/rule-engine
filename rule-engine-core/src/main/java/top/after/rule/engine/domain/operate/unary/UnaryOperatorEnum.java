package top.after.rule.engine.domain.operate.unary;

import java.util.Objects;

public enum UnaryOperatorEnum {
	不为空(UnaryOperatorEnum::notNull),
	为空(UnaryOperatorEnum::notNull),
	;
	
	private final FunctionSerializable<Object, Object> operator;

	UnaryOperatorEnum(FunctionSerializable<Object, Object> operator) {
		this.operator = operator;
	}

	public FunctionSerializable<Object, Object> getOperator(){
		return operator;
	} 
	
	public static FunctionSerializable<Object, Boolean> notNull(Object param) {
		if(param instanceof String) {
			return s -> s!=null && !s.equals("");
		}
		return Objects::nonNull;
	}
	
	public static FunctionSerializable<Object, Boolean> isNull(Object param) {
		if(param instanceof String) {
			return s -> s==null || s.equals("");
		}
		return Objects::isNull;
	}
}
