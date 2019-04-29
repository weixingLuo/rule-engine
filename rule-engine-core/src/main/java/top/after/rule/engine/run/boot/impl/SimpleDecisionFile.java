package top.after.rule.engine.run.boot.impl;

import top.after.rule.engine.run.boot.DecisionFile;

public class SimpleDecisionFile implements DecisionFile {

	private String content;
	private String name;
	
	public SimpleDecisionFile(String name, String content) {
		super();
		this.content = content;
		this.name = name;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getName() {
		return name;
	}

}
