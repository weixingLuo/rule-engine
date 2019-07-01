package top.after.rule.engine.run.service.impl;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.after.rule.engine.domain.AttributeKeeper;
import top.after.rule.engine.domain.DecisionResultEnum;
import top.after.rule.engine.domain.DomainObjectEnum;
import top.after.rule.engine.domain.entity.Decision;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.entity.DecisionResult;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.mapper.DomainVaribleConverter;
import top.after.rule.engine.domain.mapper.impl.DefaultDomainVaribleConverter;
import top.after.rule.engine.run.service.DecisionRunner;

public class DefaultDecisionRunner implements DecisionRunner {
	private static final long serialVersionUID = -6386611833929165387L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDecisionRunner.class);
	private Decision decision;
	private String mainObjectName;
	private DomainVaribleConverter mainObjectConverter;
	private DomainVaribleConverter addtionDetailsConverter;

	public DefaultDecisionRunner(Decision decision, 
			String mainObjectName,
			DomainObjectDefine mainObjectDefine,
			DomainObjectDefine addtionDetailsDefine) {
		this.decision = decision;
		this.mainObjectName = mainObjectName;
		this.mainObjectConverter = new DefaultDomainVaribleConverter(mainObjectDefine);
		this.addtionDetailsConverter =addtionDetailsDefine==null?null: new DefaultDomainVaribleConverter(addtionDetailsDefine);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DecisionResult excute(Object object) {
		DecisionContext context=new DecisionContext();
		context.setMainObjectName(mainObjectName);
		AttributeKeeper mainObject = mainObjectConverter.toDomain(object);
		context.setMainObject(mainObject);
		
		Optional<DecisionResultEnum> result = decision.excute(context);
		
		DecisionResult dr = new DecisionResult();
		dr.setStatus(result.orElse(DecisionResultEnum.无法决策));
		
		Map<String,Object> map = (Map<String,Object>)context.get(DomainObjectEnum.规则明细.name());
		dr.setRuleResult(map);
		AttributeKeeper addtionDetails = (AttributeKeeper)context.get(DomainObjectEnum.审批详情.name());
		if(addtionDetails!=null && !addtionDetails.isEmpty()){
			dr.setAddtionDetails(addtionDetailsConverter.fromDomain(addtionDetails));
		}
		return dr;
	}
}
