package top.after.rule.engine.domain.operate.predicate;

import java.io.Serializable;
import java.util.function.BiPredicate;

/**
 * Created by Administrator on 2018/1/31.
 */
public interface BiPredicateSerializable<T, T1> extends BiPredicate<T, T1>,Serializable {
    default public String prints() {
        return this.getClass().toString();
    }
}
