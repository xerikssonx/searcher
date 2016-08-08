package com.example.service;

import java.util.List;

import com.example.entity.File;

public interface SearchService {

	List<File> search(String fileName);

}
