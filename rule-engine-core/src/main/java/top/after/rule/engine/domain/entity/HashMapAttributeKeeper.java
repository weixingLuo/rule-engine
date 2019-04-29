package top.after.rule.engine.domain.entity;

import java.util.HashMap;
import java.util.Map;

import top.after.rule.engine.domain.AttributeKeeper;

public class HashMapAttributeKeeper extends HashMap<String,Object> implements AttributeKeeper{
	private static final long serialVersionUID = 1L;

	public HashMapAttributeKeeper(){
		super();
	}
	public HashMapAttributeKeeper(Map<String,Object> map){
		super(map);
	}
}
