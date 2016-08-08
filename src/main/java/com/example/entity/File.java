package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "file2", type = "file2")
public class File {

	@Id
	private String path;
	
	private String name;

	private Long lastModified;

	private Integer version;

	public File() {
		super();
	}

	public File(String name, String path, int version) {
		super();
		this.name = name;
		this.path = path;
		lastModified = System.currentTimeMillis();
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "File [path=" + path + ", name=" + name + ", lastModified=" + lastModified + ", version=" + version
				+ "]";
	}
	
	

}
