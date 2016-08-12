package com.example.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FileRepository;
import com.example.dao.VersionRepository;
import com.example.entity.File;
import com.example.entity.Version;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private VersionRepository versionRepository;

	@Override
	public List<File> search(String fileName) {
		Iterator<Version> versionIterator = versionRepository.findAll().iterator();
		if (versionIterator.hasNext()) {
			int version = versionIterator.next().getValue();
			return fileRepository.findByNameAndVersion("*" + fileName + "*", version);
		} else {
			return fileRepository.findByName("*" + fileName + "*");
		}
	}
}
