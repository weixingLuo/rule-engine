package top.after.rule.engine.domain;

public enum DecisionResultEnum {
	通过(1), 转人工(2), 拒绝(3),无法决策(4);

	private int code;

	DecisionResultEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DecisionResultEnum create(int val) {
		DecisionResultEnum[] units = DecisionResultEnum.values();
		for (DecisionResultEnum unit : units) {
			if (unit.getCode() == val) {
				return unit;
			}
		}
		throw new IllegalArgumentException("DecisionResultEnum:"+val);
	}
	
	public static int compare(DecisionResultEnum a, DecisionResultEnum  b) {
		return Integer.compare(a.code,b.code);
	}
}
