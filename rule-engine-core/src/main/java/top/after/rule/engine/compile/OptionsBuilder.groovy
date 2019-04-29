package top.after.rule.engine.compile

import top.after.rule.engine.domain.entity.DomainObjectDefine

class OptionsBuilder {

	public OptionsBuilder() {
		super()
	}
	
	public Map buildOptions(optionsNode) {
		def optionMap = new HashMap<>();
		optionsNode.option.each {
			def option = new DomainObjectDefine(it.'@name',it.'@code',it.'@type');
			optionMap.put(option.getName(), option);
		}
		optionMap;
	}
}
