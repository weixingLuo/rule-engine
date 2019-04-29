package top.after.rule.engine.domain.operate.value;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class JavaValue implements Value {
	private static final long serialVersionUID = 6400282172026056069L;
	private Object java ;
	public JavaValue(Object java) {
		this.java = java;
	}
	@Override
	public Object get(DecisionContext context) {
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
		JavaValue other = (JavaValue) obj;
		if (java == null) {
			if (other.java != null)
				return false;
		} else if (!java.equals(other.java))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JavaValue:"  + java;
	}
}
