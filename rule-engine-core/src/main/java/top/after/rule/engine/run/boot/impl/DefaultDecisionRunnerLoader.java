package top.after.rule.engine.run.boot.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;

import top.after.rule.engine.compile.DecisionFileReader;
import top.after.rule.engine.domain.DomainObjectEnum;
import top.after.rule.engine.domain.entity.Decision;
import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.run.boot.DecisionFile;
import top.after.rule.engine.run.boot.DecisionFileRepository;
import top.after.rule.engine.run.boot.DecisionRunnerLoader;
import top.after.rule.engine.run.service.DecisionRunner;
import top.after.rule.engine.run.service.impl.DefaultDecisionRunner;

public class DefaultDecisionRunnerLoader implements DecisionRunnerLoader {

	private DecisionFileRepository decisionFileRepository;
	public DefaultDecisionRunnerLoader(DecisionFileRepository decisionFileRepository) {
		this.decisionFileRepository = decisionFileRepository;
	}

	@Override
	@Cacheable
	public DecisionRunner lookup(String jndiName) {
		Optional<DecisionFile> op = decisionFileRepository.findByName(jndiName);
		if(!op.isPresent()) {
			return null;
		}
		DecisionFile f = op.get();
		
		Reader xml = new InputStreamReader(new ByteArrayInputStream(f.getContent().getBytes()));;
    	DecisionFileReader reader = new DecisionFileReader(xml);
    	
		Decision decision = reader.buildDecision();
		
		String mainObjectName = reader.getDomainVariableName();
		DomainObjectDefine root = reader.getDomainVariable();
		DomainObjectDefine mainObjectDefine=root.getChild(mainObjectName);
		DomainObjectDefine addtionDetailsDefine=root.getChild(DomainObjectEnum.审批详情.name());
		DefaultDecisionRunner runner = new DefaultDecisionRunner(decision,mainObjectName, mainObjectDefine, addtionDetailsDefine);
		return runner;
	}

}
