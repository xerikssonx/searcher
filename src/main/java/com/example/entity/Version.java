package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "version2", type = "version2")
public class Version {

	@Id
	private String id;

	private Integer value;

	private Long date;

	public Version() {
		super();
	}

	public Version(Integer value) {
		super();
		this.value = value;
		date = System.currentTimeMillis();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", value=" + value + ", date=" + date + "]";
	}
	
	

}
