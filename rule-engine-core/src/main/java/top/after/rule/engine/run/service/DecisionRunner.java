package top.after.rule.engine.run.service;

import java.io.Serializable;

import top.after.rule.engine.domain.entity.DecisionResult;

public interface DecisionRunner extends Serializable{
	public DecisionResult excute(Object param);
}
