package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.example.dao.FileRepository;
import com.example.dao.VersionRepository;
import com.example.entity.File;
import com.example.entity.Version;

@Service
public class ScraperServiceImpl implements ScraperService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private VersionRepository versionRepository;

	@Scheduled(fixedDelay = 1000 * 60 * 60 * 12)
	public void scanFileSystem() {
		Version version = null;
		Iterator<Version> versionIterator = versionRepository.findAll().iterator();
		if (versionIterator.hasNext()) {
			version = versionIterator.next();
			version.setValue(version.getValue() + 1);
		} else {
			version = new Version(1);
		}
		versionRepository.save(version);
		taskExecutor.execute(new Scrape(new java.io.File("/home/edgar/Documents/"), version));
	}

	public class Scrape implements Runnable {

		private java.io.File dir;

		private Version version;

		public Scrape() {
			super();
		}

		public Scrape(java.io.File dir, Version version) {
			super();
			this.dir = dir;
			this.version = version;
		}

		@Override
		public void run() {
			java.io.File[] files = dir.listFiles();
			List<File> list = new ArrayList<>();
			if (files != null) {
				for (java.io.File file : files) {
					if (file.isDirectory()) {
						taskExecutor.execute(new Scrape(file, version));
					} else {
						try {
							String path = file.getCanonicalPath();
							list.add(new com.example.entity.File(file.getName(), path, version.getValue()));
							System.out.println(path);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				if (!list.isEmpty()) {
					fileRepository.save(list);
				}
			}
		}
	}

}
