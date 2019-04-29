package top.after.rule.engine.compile

import java.util.function.Consumer

import top.after.rule.engine.domain.DomainObjectEnum
import top.after.rule.engine.domain.condition.Switch
import top.after.rule.engine.domain.condition.impl.CalculateAction
import top.after.rule.engine.domain.condition.impl.Constructor
import top.after.rule.engine.domain.entity.Decision
import top.after.rule.engine.domain.entity.DecisionContext
import top.after.rule.engine.domain.entity.DomainObjectDefine
import top.after.rule.engine.domain.entity.Rule
import top.after.rule.engine.domain.operate.Value
import top.after.rule.engine.domain.operate.predicate.AndPredicateOperation
import top.after.rule.engine.domain.operate.setter.MappingSetter
import top.after.rule.engine.domain.operate.setter.OperationSetter

class DecisionFileReader {

	private rootElement
	private OperationBuilder operationBuilder
	
	private Decision decision
	private String mainDomainObjectName
	private String mainDomainObjectCode
	private DomainObjectDefine domainVariable
	private OptionsBuilder optionsBuilder;
	private SwitchBuilder switchBuilder;
	
	DecisionFileReader(){}
	
	DecisionFileReader(Reader reader){
		rootElement = new XmlParser().parse(reader);
		operationBuilder = new OperationBuilder();
		optionsBuilder = new OptionsBuilder();
		switchBuilder = new SwitchBuilder();
	}

	Decision buildDecision(){
//		buildDataSource(rootElement.'datasource');
		domainVariable = buildDomainVariable(rootElement.'domain-variable'[0]);
		CompileContext.init(domainVariable);
		buildDefine(rootElement.'define')
		decision = buildRules(rootElement.'rule-set'[0]);
		CompileContext.destroy()
		decision
	}

	def List<Consumer<DecisionContext>> buildDefine(defineElement){
		if(defineElement == null){
			return ;
		}
		defineElement.action.each{
			CompileContext.get().putDefine(it.'@code', it)
		}
	}
	
	def buildAction(actionNode) {
		def objectNode = actionNode.setter[0].object[0];
		Constructor construct = buildConstructor(objectNode);
		CalculateAction action = new CalculateAction(construct);
		action.setCode(actionNode.'@code')
		action.setName(actionNode.'@name')
		CompileContext.get().setDomainVariable(action);
	}
	
	def buildConstructor(objectNode) {
		
		DomainObjectDefine define = new DomainObjectDefine(objectNode.'@name');
		def fields = buildOptions(objectNode.options);
		define.setFileds(fields);
		Value param = operationBuilder.buildEnum(objectNode, null);
		new Constructor(define,param);
	}
	
	def buildDataSource(datasourceNode) {
		mainDomainObjectName  = datasourceNode[0].'@name';
		mainDomainObjectCode = datasourceNode[0].'@code';
	}
	def buildDomainVariable(domainNode){
		
		def domainVariable = new DomainObjectDefine(DomainObjectEnum.上下文.name());
		domainNode.object.each {
			buildDomainVariable(it, domainVariable);
		}
		domainVariable
	}
	
	def buildRules(rulesNode){
		def domain = rulesNode.'@domain';
		if(domain != null)
			CompileContext.get().domainVariableGoInto(domain)
		
		Decision decision = new Decision();
		rulesNode.rule.each { 
			Switch sw = switchBuilder.build(it.switch[0]);
			Rule rule = new Rule(sw);
			rule.setName(it.'@name')
			decision.addRule(rule)
		}

		if(domain != null)
			CompileContext.get().domainVariableGoOut();
		decision
	}
	
	def buildWhen(whenNode){
		def domain = whenNode.'@domain';
		if(domain != null)
			CompileContext.get().domainVariableGoInto(domain)
		def conditions = new LinkedList<>();
		whenNode.conditions[0].each{
			def o = buildOperation(it)
			conditions.add(o)
		}
		if(domain != null)
			CompileContext.get().domainVariableGoOut();
		new AndPredicateOperation(conditions);
	}

	def buildThen(thenNode){
		def domain = thenNode.'@domain';
		if(domain != null)
			CompileContext.get().domainVariableGoInto(domain)
		def actions = new LinkedList<>();
		thenNode.setter.each{
			def setter = buildSetter(it);
			actions.add(setter);
		}
		if(domain != null)
			CompileContext.get().domainVariableGoOut();
		 actions;
	}

	def buildOperation(operationNode){
		operationBuilder.build(operationNode);
	}

	def buildSetter(setterNode){
		def setter
		def type = setterNode.'@type'
		if(type == 'operation'){
			def formal = operationBuilder.buildParamter(setterNode.parameter[0]);
			if(formal==null)
				throw new CompileException(setterNode.parameter[0].toString());
			def value = operationBuilder.buildParamter(setterNode.parameter[1]);
			if(value==null)
				throw new CompileException(setterNode.parameter[1].toString());
				
			def forceSet = setterNode.'@forceSet'
			setter = forceSet ? new OperationSetter(formal, value, true) : new OperationSetter(formal, value)
		}else if(type == 'mapping'){
			setter = buildMappingSetter(setterNode);
		}
		setter
	}
	
	def buildMappingSetter(mappingNode){
		def objectNode = mappingNode.object[0]
		def name = objectNode.'@name';
		def objDefine = new DomainObjectDefine(name,objectNode.'@code',objectNode.'@type');
		def context = CompileContext.get()
		def obj = context.createDomainObject(name, objDefine);
		def mapNode = mappingNode.mapping[0]
		def value = context.buildDomainObject(mapNode.'@from')
		def map = new HashMap()
		mapNode.map.each{
			map.put(it.'@case', it.'@then')
		}
		mapNode.default.each{
			map.put("default",it.'@then');
		}
		new MappingSetter(obj,value,map);
	}
	
	def buildDomainVariable(objectNode, domainVariable) {
		def obj = new DomainObjectDefine(objectNode.'@name');
		def fields = domainVariable.getFileds();
		fields = fields?: new HashMap<>();
		domainVariable.setFileds(fields);
		fields.put(obj.getName(), obj);

		def fieldMap = new HashMap<>();
		obj.setFileds(fieldMap);
		objectNode.fields.field.each {
			def field = new DomainObjectDefine(it.'@name',it.'@code',it.'@type');
			fieldMap.put(field.getName(), field);
			if(field.type=='enum') {
				buildOptions(it.options);
			}
		}
	}


	def buildOptions(optionsNode) {
		optionsBuilder.buildOptions(optionsNode);
	}	
	public DomainObjectDefine getDomainVariable() {
		return domainVariable;
	}
	public String getDomainVariableName() {
		return mainDomainObjectName;
	}
	public static void  main(String[] args){
		println "xxxx";
	}
}
