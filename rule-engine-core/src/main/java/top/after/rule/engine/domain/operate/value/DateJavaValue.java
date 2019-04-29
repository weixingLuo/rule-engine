package top.after.rule.engine.domain.operate.value;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateJavaValue extends JavaValue implements Value  {
	private LocalDate java ;
	public DateJavaValue(String java) {
        super(java);
        if (StringUtils.isEmpty(java)) {
            throw new IllegalArgumentException("数据计算参数不能为空");
        }
        try {
            this.java = LocalDate.parse(java);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("日期参数不是yyyy-MM-dd格式");
        }
	}
	@Override
	public LocalDate get(DecisionContext context) {
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
		DateJavaValue other = (DateJavaValue) obj;
		if (java == null) {
			if (other.java != null)
				return false;
		} else if (!java.equals(other.java))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DateJavaValue:" + java;
	}
}
