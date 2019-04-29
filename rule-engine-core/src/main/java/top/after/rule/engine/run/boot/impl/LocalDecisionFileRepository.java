package top.after.rule.engine.run.boot.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.stream.Collectors;

import top.after.rule.engine.run.boot.DecisionFile;
import top.after.rule.engine.run.boot.DecisionFileRepository;

public class LocalDecisionFileRepository implements DecisionFileRepository {
	@Override
	public Optional<DecisionFile> findByName(String name) {
		String xml = read(name);
		if(xml == null)
			return Optional.empty();
		DecisionFile file = new SimpleDecisionFile(name,xml);
		return Optional.of(file);
	}

	private String read(String fileName){
		InputStream inputStream = this.getClass().getResourceAsStream(fileName);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf8")))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
