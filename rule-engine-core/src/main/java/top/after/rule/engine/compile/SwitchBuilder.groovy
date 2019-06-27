package top.after.rule.engine.compile

import top.after.rule.engine.domain.condition.Action
import top.after.rule.engine.domain.condition.Case
import top.after.rule.engine.domain.condition.Switch
import top.after.rule.engine.domain.condition.impl.CaseDelegate
import top.after.rule.engine.domain.condition.impl.SimpleValueAction
import top.after.rule.engine.domain.condition.impl.SwitchValue
import top.after.rule.engine.domain.operate.value.JavaValue

class SwitchBuilder {

	private OperationBuilder operationBuilder;
	public SwitchBuilder() {
		operationBuilder = new OperationBuilder();
	}
	
	public Switch build(switchNode) {
		def onCondition = operationBuilder.buildParamter(switchNode.parameter[0])
		if(onCondition == null) {
			throw new CompileException(switchNode.parameter[0].toString());
		}
		SwitchValue sv = new SwitchValue();
		
		switchNode.case.each {
			sv.addCase(buildCase(it,onCondition))
		}
		
		def defaultNode = switchNode.'action'[0];
		def action = null;
		if(defaultNode) {
			action = buildAction(defaultNode);
		}
		sv.setDefault(action);
		
		sv;
	}
	
	private Case buildCase(caseNode,before) {
		def operator = caseNode.operator[0]?.text()?.trim()
		if(!operator){
			throw new CompileException("operator can not be null:"+operationNode);
		}
		
		def op = OperationBuilderEnum.getOperation('boolean', operator)
		op.setBefore(before)
		if(caseNode.parameter[0] == null){
			throw new CompileException("caseNode must define paramter:"+caseNode);
		}
		def after = operationBuilder.buildParamter(caseNode.parameter[0]);
		op.setAfter(after);
		
		def acNode = caseNode.action[0];
		if(! acNode ) {
			throw new CompileException("找不到action："+caseNode);
		}
		def action = buildAction(caseNode.action[0]);
		
		new CaseDelegate(op,action);
	}
	
	public Object buildAction(actionNode) {
		def type = actionNode.'@type'
		if("enum" == type) {
			return new SimpleValueAction(new JavaValue(actionNode.'@with-param'));
		}else if("switch" == type) {
			return build(actionNode.'switch'[0])
		}
		throw new CompileException("找不到类型："+type);
	}
}
