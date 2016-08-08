package com.example.ui;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.swing.text.BadLocationException;
import javax.swing.text.View;
import javax.swing.text.Position.Bias;
import javafx.scene.control.ListView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.File;
import com.example.service.SearchService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

	@Autowired
	private SearchService searchService;

	@FXML
	private TextField txtSearch;
	@FXML
	private ListView<String> listItems;

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	public void initialize() {
	}

	@PostConstruct
	public void init() {

	}

	@FXML
	public void search() {
		List<File> list = searchService.search(txtSearch.getText());
		List<String> strings = list.stream().map(i -> i.getPath()).collect(Collectors.toList());
		ObservableList<String> observableList = FXCollections.<String>observableArrayList(strings);
		listItems.getItems().clear();
		listItems.getItems().addAll(observableList);

	}
}