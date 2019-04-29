package top.after.rule.engine.domain.entity;

import java.util.Map;

import top.after.rule.engine.domain.DecisionResultEnum;


public class DecisionResult extends HashMapAttributeKeeper{
	private static final long serialVersionUID = -8765901960485229107L;

	public DecisionResult(Map<String,Object> map) {
		super(map);
	}
	
	public DecisionResult() {
		super();
	}

	public void setStatus(DecisionResultEnum status) {
		super.put("status",status);
	}
	public DecisionResultEnum getStatus() {
		return (DecisionResultEnum)super.get("status");
	}
	
	public void setRuleResult(Map<String,Object> ruleResult) {
		super.put("ruleResult",ruleResult);
	}
	public void setAddtionDetails(Map<String,Object> addtionDetails) {
		super.put("addtionDetails",addtionDetails);
	}
}
