package top.after.rule.engine.domain.operate.value;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class NumberJavaValue extends JavaValue implements Value {
    private BigDecimal java;

    public NumberJavaValue(String java) {
        super(java);
        if (StringUtils.isEmpty(java)) {
            throw new IllegalArgumentException("数据计算参数不能为空");
        }
        try {
            this.java = new BigDecimal(java);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("number类型参数必须是数字");
        }
    }

    @Override
    public BigDecimal get(DecisionContext context) {
        return java;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((java == null) ? 0 : java.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NumberJavaValue other = (NumberJavaValue) obj;
        if (java == null) {
            if (other.java != null)
                return false;
        } else if (!java.equals(other.java))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NumberJavaValue:" + java;
    }
}
