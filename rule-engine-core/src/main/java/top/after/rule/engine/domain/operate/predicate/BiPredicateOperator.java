package top.after.rule.engine.domain.operate.predicate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public enum BiPredicateOperator {

	等于(BiPredicateOperator::equals), 不等于(BiPredicateOperator::notEqual),

	在列表中(BiPredicateOperator::inSet),
	包含列表中词汇(BiPredicateOperator::containsStringinSet),
	// 日期比较，返回boolean
	早于(BiPredicateOperator::isBefore), 早于等于(BiPredicateOperator::isBeforeAndEqual), 晚于(BiPredicateOperator::isAfter),
	晚于等于(BiPredicateOperator::isAfterAndEqual),

	// 数字比较，返回boolean
	大于(BiPredicateOperator::gt), 大于等于(BiPredicateOperator::gte), 小于(BiPredicateOperator::lt),
	小于等于(BiPredicateOperator::lte)

	;

	private final BiPredicateSerializable<Object, Object> operator;

	BiPredicateOperator(BiPredicateSerializable<Object, Object> operator) {
		this.operator = operator;
	}

	public BiPredicateSerializable<Object, Object> getOperator() {
		return operator;
	}

	public static BiPredicateOperator get(BiPredicateSerializable<Object, Object> operator) {
		for (BiPredicateOperator o : BiPredicateOperator.values()) {
			BiPredicateSerializable<Object, Object> ope = o.getOperator();
			if (ope == operator) {
				System.out.println(ope.toString() + "-" + operator.toString());
				return o;
			}
		}
		throw new NullPointerException();
	}

	public static boolean inSet(Object instance, Object set) {
		if (!(set instanceof Set)) {
			throw new IllegalArgumentException("第二个参数不是集合。" + (set == null ? "null" : set.getClass()));
		}
		String entry = "";
		if (instance != null) {
			entry = instance.toString();
		}
		return ((Set) set).contains(entry);
	}

	public static boolean containsStringinSet(Object instance, Object set) {
		if (!(set instanceof Set)) {
			throw new IllegalArgumentException("第二个参数不是集合。" + (set == null ? "null" : set.getClass()));
		}
		final String entry = instance == null ? "" : instance.toString();
		boolean b = ((Set<Object>) set).stream().filter(Objects::nonNull).map(o -> o.toString())
				.anyMatch(s -> entry.contains(s));
		return b;
	}

	public static boolean equals(Object a, Object b) {
		return String.valueOf(a).equals(String.valueOf(b));
	}

	public static boolean notEqual(Object a, Object b) {
		return !equals(a, b);
	}

	public static BigDecimal bigDecimalConvert(Object obj) {
		if(obj == null){
		   return null;
		}
		BigDecimal value = null;
		if (obj instanceof BigDecimal) {
			value = (BigDecimal) obj;
		} else {
			value = new BigDecimal(obj.toString());
		}
		if (null == value) {
			throw new IllegalArgumentException(String.format("变量转换错误[%s]", obj));
		}
		return value;
	}

	public static boolean gt(Object a, Object b) {
		BigDecimal left = bigDecimalConvert(a);
		BigDecimal right = bigDecimalConvert(b);
		return left.compareTo(right) > 0;
	}

	public static boolean gte(Object a, Object b) {
		BigDecimal left = bigDecimalConvert(a);
		BigDecimal right = bigDecimalConvert(b);
		return left.compareTo(right) >= 0;
	}

	public static boolean lt(Object a, Object b) {
		BigDecimal left = bigDecimalConvert(a);
		BigDecimal right = bigDecimalConvert(b);
		return left.compareTo(right) < 0;
	}

	public static boolean lte(Object a, Object b) {
		BigDecimal left = bigDecimalConvert(a);
		BigDecimal right = bigDecimalConvert(b);
		return left.compareTo(right) <= 0;
	}

	private static boolean isBefore(Object before, Object after) {
		LocalDate left = LocalDate.parse(before.toString());
		LocalDate right = LocalDate.parse(after.toString());
		return left.isBefore(right);
	}

	private static boolean isBeforeAndEqual(Object before, Object after) {
		LocalDate left = LocalDate.parse(before.toString());
		LocalDate right = LocalDate.parse(after.toString());
		return (left.isBefore(right) || left.isEqual(right));
	}

	private static boolean isAfter(Object before, Object after) {
		LocalDate left = LocalDate.parse(before.toString());
		LocalDate right = LocalDate.parse(after.toString());
		return left.isAfter(right);
	}

	private static boolean isAfterAndEqual(Object before, Object after) {
		LocalDate left = LocalDate.parse(before.toString());
		LocalDate right = LocalDate.parse(after.toString());
		return left.isAfter(right) || left.isEqual(right);
	}
}
