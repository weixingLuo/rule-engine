package top.after.rule.engine.compile;

public class DecisionManagerBuilder {
	
	/**
	 * 根据规则XML配置文件构造积分发放依赖的服务和参数
	 * @param xml
	 * @return
	 */
	/*
	 * public DecisionRunningManager build(String xml){ DecisionFileReader reader =
	 * new DecisionFileReader(new StringReader(xml)); Decision decision =
	 * reader.buildDecision(); String mainObjectName =
	 * reader.getDomainVariableName(); DefaultActiveRunningManager manager = new
	 * DefaultActiveRunningManager(); manager.setMainObjectName(mainObjectName);
	 * 
	 * Map<String, DomainObjectDefine> domainVariableDefine =
	 * reader.getDomainVariable().getFileds(); Map<String,DomainVaribleConverter>
	 * converters=new HashMap<>(); for(Entry<String, DomainObjectDefine>
	 * e:domainVariableDefine.entrySet()) { converters.put(e.getKey(), new
	 * DefaultDomainVaribleConverter(e.getValue())); }
	 * manager.setConverters(converters); DomainObjectDefine scoreDefine =
	 * domainVariableDefine.get(DomainObjectEnum.审批详情.name()); DefaultDecisionRunner
	 * builder = new DefaultDecisionRunner(decision, scoreDefine);
	 * manager.setScoreDetailBuilder(builder); return manager; }
	 */
}
