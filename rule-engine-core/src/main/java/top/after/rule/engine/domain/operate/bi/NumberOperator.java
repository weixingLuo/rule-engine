package top.after.rule.engine.domain.operate.bi;

import top.after.rule.engine.domain.operate.value.NumberJavaValue;

import java.math.BigDecimal;

public enum NumberOperator {

	加上(NumberOperator::addImpl),
	减去(NumberOperator::subtractImpl),
	乘以(NumberOperator::multiplyImpl),
	除以(NumberOperator::divideImpl);
//	除以(INSTANCE::divideImpl);

	private final BiFunctionSerializable<Number, Number,Number> operator;

	NumberOperator(BiFunctionSerializable<Number, Number,Number> operator){
		this.operator = operator;
	}
	
	public BiFunctionSerializable<Number, Number,Number> getOperator(){
		return operator;
	}

	public static NumberOperator get(BiFunctionSerializable<Number, Number,Number> operator){
		//TODO
		for(NumberOperator o : NumberOperator.values() ){
			if (o.getOperator() == operator){
				return o;
			}
		}
		throw new NullPointerException();
	}

	public static BigDecimal bigDecimalConvert(Object obj){
		BigDecimal value = null;
		if(obj instanceof BigDecimal){
			value = (BigDecimal) obj;
		}else if (obj instanceof NumberJavaValue){
			value = ((NumberJavaValue) obj).get(null);
		}
		if(null == value){
			throw new IllegalArgumentException(String.format("变量转换错误[%s]", obj));
		}
		return value;
	}

	public static Number addImpl(Object before,Object after){
		BigDecimal bef = bigDecimalConvert(before);
		BigDecimal aft = bigDecimalConvert(after);
		return bef.add(aft);
	}
	public static Number subtractImpl(Object before,Object after){
		BigDecimal bef = bigDecimalConvert(before);
		BigDecimal aft = bigDecimalConvert(after);
		return bef.subtract(aft);
	}
	public static Number multiplyImpl(Object before,Object after){
		BigDecimal bef = bigDecimalConvert(before);
		BigDecimal aft = bigDecimalConvert(after);
		return bef.multiply(aft);
	}
	public static Number divideImpl(Object before,Object after){
		BigDecimal bef = bigDecimalConvert(before);
		BigDecimal aft = bigDecimalConvert(after);
		return bef.divide(aft);
	}

}
