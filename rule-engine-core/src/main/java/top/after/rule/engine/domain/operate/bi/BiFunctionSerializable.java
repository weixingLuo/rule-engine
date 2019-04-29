package top.after.rule.engine.domain.operate.bi;

import java.io.Serializable;
import java.util.function.BiFunction;

public interface BiFunctionSerializable<T, U, R> extends BiFunction<T, U, R>,Serializable {
}
