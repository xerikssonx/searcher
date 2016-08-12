//package com.example;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.awt.AWTException;
//import java.awt.MenuItem;
//import java.awt.PopupMenu;
//import java.awt.SystemTray;
//import java.awt.TrayIcon;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.URL;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import javax.imageio.ImageIO;
//
//
//@SpringBootApplication
//@EnableScheduling
//public class Application extends AbstractJavaFxApplicationSupport {
//
//    @Value("${ui.title:Searcher}")//
//    private String windowTitle;
//
//    @Autowired
//    private ConfigurationControllers.View view;
//    
//    private TrayIcon trayIcon;
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setTitle(windowTitle);
//        stage.setScene(new Scene(view.getView()));
//        stage.setResizable(true);
//        stage.centerOnScreen();
//        stage.show();
//        System.out.println(SystemTray.isSupported());
//    }
//    
//    
//    
//
//    public static void main(String[] args) {
//        launchApp(Application.class, args);
//    }
//
//
//}