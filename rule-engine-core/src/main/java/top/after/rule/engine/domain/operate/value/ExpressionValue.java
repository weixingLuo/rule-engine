package top.after.rule.engine.domain.operate.value;

import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.operate.Value;

public class ExpressionValue implements Value {
	private static final ExpressionParser parser = new SpelExpressionParser();
	private Expression expression;
	
	public ExpressionValue(String expressionString) {
		expression = parser.parseExpression(expressionString);
	}

	@Override
	public Object get(DecisionContext context) {
		return expression.getValue(context);
	}

	@Override
	public String toString() {
		return "ExpressionValue:" + expression;
	}

	public static void main(String[] args) {
		Map<String,Object> a = new HashMap<>();
		a.put("b", "b");
		Map<String,Object> c = new HashMap<>();
		c.put("d", "d");
		a.put("c", c);
		//addtion_info.suburb["tel"]
		String cd = "[c][d]";
		Expression cde = parser.parseExpression(cd);
		System.out.println(cde.getValue(a));
		cde = parser.parseExpression("[d][a]");
		System.out.println(cde.getValue(a));
	}
}
