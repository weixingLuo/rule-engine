package top.after.engine.api.manage.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import top.after.rule.engine.run.boot.DecisionFile;
@Entity
public class DomainObjectFile implements DecisionFile {

	private Long id;
	private String content;
	private String name;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public DomainObjectFile() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
