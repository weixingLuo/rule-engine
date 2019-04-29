package top.after.rule.engine.domain.operate.unary;

import java.io.Serializable;
import java.util.function.Function;

public interface FunctionSerializable<P, R> extends Function<P, R>,Serializable {
}
