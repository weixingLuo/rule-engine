package top.after.rule.engine;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

import org.junit.Test;

import top.after.rule.engine.compile.DecisionFileReader;
import top.after.rule.engine.domain.AttributeKeeper;
import top.after.rule.engine.domain.DecisionResultEnum;
import top.after.rule.engine.domain.DomainObjectEnum;
import top.after.rule.engine.domain.entity.Decision;
import top.after.rule.engine.domain.entity.DecisionContext;
import top.after.rule.engine.domain.entity.HashMapAttributeKeeper;

public class MainTest {

	public MainTest() {
		// TODO Auto-generated constructor stub
	}

    @Test
    public void test_build_ActiveRul() {
    	//"/com/crfchina/groovy/active-template.xml"
    	Reader xml = mockXml("/top/after/rule/engine/script/decision.xml");
    	DecisionFileReader reader = new DecisionFileReader(xml);
		Decision decision = reader.buildDecision();
		assertNotNull(decision);
		DecisionContext context=new DecisionContext();
		context.setMainObjectName(DomainObjectEnum.申请单.name());
		AttributeKeeper main=new HashMapAttributeKeeper();
		main.put("申请人内部黑名单", "2");
		main.put("IP城市与工作城市一致", "2");
		main.put("GPS城市与工作城市一致", "1");
		main.put("申请人年龄", "18");
		main.put("工作单位", "信息有限公司");
		context.setMainObject(main);
		Optional<DecisionResultEnum> result = decision.excute(context);
		System.out.println(result.get());
		System.out.println(context.get(DomainObjectEnum.规则明细.name()));
//		ActiveRule active = reader.getActive();
//		WhenAndThen action = active.scoreAction;
		/*assertNotNull(customerId);
		assertEquals("string", customerId.getType());
		assertEquals("customerId", customerId.getCode());*/
    }

	private Reader mockXml(String fileName){
		InputStream input = this.getClass().getResourceAsStream(fileName);
		return new InputStreamReader(input);
	}
}
