package com.example.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.entity.Version;

public interface VersionRepository extends ElasticsearchRepository<Version, Integer> {

}
