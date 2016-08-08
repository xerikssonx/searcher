package com.example.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.entity.File;

public interface FileRepository extends ElasticsearchRepository<File, String> {

	List<File> findByNameAndVersion(String name, Integer version);

	List<File> findByName(String name);

	List<File> findByVersion(Integer version);
}
