package top.after.rule.engine.compile

import top.after.rule.engine.domain.operate.Value
import top.after.rule.engine.domain.operate.value.DateJavaValue
import top.after.rule.engine.domain.operate.value.JavaValue
import top.after.rule.engine.domain.operate.value.NumberJavaValue

import java.util.stream.Collectors

class OperationBuilder {
	private OptionsBuilder optionsBuilder = new OptionsBuilder();
	public Value build(operationNode){
		buildOperation(operationNode);
	}
	def buildOperation(operationNode){
		def operation_type = operationNode.'@operation-type'
		def result_type = operationNode.'@result-type'
		def operator = operationNode.operator[0]?.text()?.trim()
		if(!operator){
			throw new CompileException("operator can not be null:"+operationNode);
		}

		if(operation_type == "binary_operation"){

			def op = OperationBuilderEnum.getOperation(result_type, operator)
            def before = buildParamter(operationNode.parameter[0])
			if(before==null)
				throw new CompileException(operationNode.parameter[0].toString());
			op.setBefore(before)
			def after = buildParamter(operationNode.parameter[1])
			if(after==null)
				throw new CompileException(operationNode.parameter[1].toString());
			op.setAfter(after)
			return op
		}
		throw new IllegalArgumentException("unknown operation_type:"+operation_type);
	}

	def buildEnum(parameterNode,valueType) {
		def value = parameterNode.options.'@selected'[0];
		if (valueType == "number") {
			return new NumberJavaValue(parameterNode.text().trim());
		}
		if (valueType == 'string') {
			return new JavaValue(value);
		}
		optionsBuilder.buildOptions(parameterNode.options[0]);
	}
	
	def buildParamter(parameterNode){
		def name_type = parameterNode.'@name-type'
		def type = parameterNode.'@value-type'

		if (name_type == 'operation') {
			return buildOperation(parameterNode.operation[0])
		}
		if (name_type == 'variable') {
			def placeHolder = parameterNode.text().trim();
			return CompileContext.get().buildDomainObject(placeHolder);
		}
		if (name_type == 'input') {
			if (type == "number") {
				return new NumberJavaValue(parameterNode.text().trim());
			}
			if (type == 'date') {
				return new DateJavaValue(parameterNode.text().trim());
			}
			if (type == 'string') {
				return new JavaValue(parameterNode.text().trim());
			}
			if (type=='list'){
				Set<String> s = new TreeSet();
				String input = parameterNode.text().trim();
				if(input.indexOf(',')>=0){
					String[] a=input.split(',');
					for(String k:a){
						s.add(k);
					}
				}else{
					s.add(input);
				}
				return new JavaValue(s);
			}
		}
		if(name_type == 'enum') {
			return buildEnum(parameterNode,type);
		}
		throw new IllegalArgumentException("unknown parameterNode:"+parameterNode);
	}
}