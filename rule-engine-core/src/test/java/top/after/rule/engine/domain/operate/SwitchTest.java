package top.after.rule.engine.domain.operate;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import top.after.rule.engine.domain.condition.impl.CalculateAction;
import top.after.rule.engine.domain.condition.impl.CaseDelegate;
import top.after.rule.engine.domain.condition.impl.Constructor;
import top.after.rule.engine.domain.condition.impl.SwitchValue;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.operate.value.JavaValue;

public class SwitchTest {

	public SwitchTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() {
		SwitchValue sv = mock();
		DecisionContext context=new DecisionContext();
		Object r = sv.get(context);
		assertEquals(10,r);
	}
	
	private SwitchValue mock() {
		SwitchValue sv = new SwitchValue();
		DomainObjectDefine define=new DomainObjectDefine(null,null,"enum");
		CaseDelegate c1 = new CaseDelegate(o->o.toString().equals("1"),new CalculateAction(new Constructor(define, new JavaValue(10))));
		CaseDelegate c2 = new CaseDelegate(o->o.toString().equals("2"),new CalculateAction(new Constructor(define, new JavaValue(20))));
		sv.addCase(c1);
		sv.addCase(c2);
		return sv;
	}
}
