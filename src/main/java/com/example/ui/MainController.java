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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

	@Autowired
	private SearchService searchService;

	@FXML
	private TextField txtSearch;
	@FXML
	private TableView<File> table;
	@FXML
	private TableColumn columnPath;
	@FXML
	private TableColumn columnAction;

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	public void initialize() {
	}

	@PostConstruct
	public void init() {
		javafx.application.Platform.setImplicitExit(false);
		columnPath.setCellValueFactory(new PropertyValueFactory<>("path"));

		columnAction.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		Callback<TableColumn<File, String>, TableCell<File, String>> cellFactory = new Callback<TableColumn<File, String>, TableCell<File, String>>() {
			@Override
			public TableCell call(final TableColumn<File, String> param) {
				final TableCell<File, String> cell = new TableCell<File, String>() {

					final Button btn = new Button("Copy");

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								File person = getTableView().getItems().get(getIndex());
								System.out.println(person.getPath());
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		columnAction.setCellFactory(cellFactory);

	}

	@FXML
	public void search() {
		List<File> list = searchService.search(txtSearch.getText());
		List<String> strings = list.stream().map(i -> i.getPath()).collect(Collectors.toList());
		ObservableList<File> observableList = FXCollections.<File>observableArrayList(list);
		table.getItems().clear();
		table.setItems(observableList);
	}

}