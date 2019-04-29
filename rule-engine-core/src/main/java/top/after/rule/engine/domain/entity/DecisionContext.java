package top.after.rule.engine.domain.entity;

import top.after.rule.engine.domain.AttributeKeeper;


public class DecisionContext extends HashMapAttributeKeeper{
	private static final long serialVersionUID = -4234452910428792504L;
	private String mainObjectName;

	public DecisionContext() {
		super();
	}
	
	public void setAttributeKeeper(String nanme, AttributeKeeper keeper) {
		super.put(nanme, keeper);
	}
	
	public AttributeKeeper getIngegralAttributeKeeper(String name) {
		return (AttributeKeeper)super.get(name);
	}
	
	public void setMainObject(AttributeKeeper main){
		setAttributeKeeper(mainObjectName, main);
	}
	
	public AttributeKeeper getMainObject(){
		return getIngegralAttributeKeeper(mainObjectName);
	}
	
	public void setMainObjectName(String name) {
		mainObjectName = name;
	}
}
