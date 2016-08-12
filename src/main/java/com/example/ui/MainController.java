package com.example.ui;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.File;
import com.example.service.SearchService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;


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
		javafx.application.Platform.setImplicitExit(false);
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