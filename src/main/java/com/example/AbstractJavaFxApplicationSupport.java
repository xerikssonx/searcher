package com.example;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


@SpringBootApplication
@EnableScheduling
public  class AbstractJavaFxApplicationSupport extends Application {
	
	
	@Value("${ui.title:Searcher}")//
    private String windowTitle;

    @Autowired
    private ConfigurationControllers.View view;
    
    private TrayIcon trayIcon;

	private static String[] savedArgs;

	protected ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AbstractJavaFxApplicationSupport.class);
		builder.headless(false);
		context = builder.run(savedArgs);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
	}

	protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clazz, String[] args) {
		AbstractJavaFxApplicationSupport.savedArgs = args;
		Application.launch(clazz, args);
	}

	 public static void main(String[] args) {
	        launchApp(AbstractJavaFxApplicationSupport.class, args);
	    }
	

	    @Override
	    public void start(Stage stage) throws Exception {
	        stage.setTitle(windowTitle);
	        stage.setScene(new Scene(view.getView()));
	        stage.setResizable(true);
	        stage.centerOnScreen();
	        stage.show();
	        createTrayIcon(stage);
	    }
	    
	    public void createTrayIcon(final Stage stage) {
	        if (SystemTray.isSupported()) {
	            // get the SystemTray instance
	            SystemTray tray = SystemTray.getSystemTray();
	            // load an image
	            
	            URL sqlScriptUrl = this.getClass()
	                       .getClassLoader().getResource("search.png");
	            Image image = Toolkit.getDefaultToolkit().getImage(sqlScriptUrl.getPath());

	            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	                @Override
	                public void handle(WindowEvent t) {
	                    hide(stage);
	                }
	            });
	            // create a action listener to listen for default action executed on the tray icon
	            final ActionListener closeListener = new ActionListener() {
	                @Override
	                public void actionPerformed(java.awt.event.ActionEvent e) {
	                    System.exit(0);
	                }
	            };

	            ActionListener showListener = new ActionListener() {
	                @Override
	                public void actionPerformed(java.awt.event.ActionEvent e) {
	                    Platform.runLater(new Runnable() {
	                        @Override
	                        public void run() {
	                            stage.show();
	                        }
	                    });
	                }
	            };
	            // create a popup menu
	            PopupMenu popup = new PopupMenu();

	            MenuItem showItem = new MenuItem("Show");
	            showItem.addActionListener(showListener);
	            popup.add(showItem);

	            MenuItem closeItem = new MenuItem("Close");
	            closeItem.addActionListener(closeListener);
	            popup.add(closeItem);
	            /// ... add other items
	            // construct a TrayIcon
	            trayIcon = new TrayIcon(image, "Searcher", popup);
	            // set the TrayIcon properties
	            trayIcon.addActionListener(showListener);
	            // ...
	            // add the tray image
	            try {
	                tray.add(trayIcon);
	            } catch (AWTException e) {
	                System.err.println(e);
	            }
	            // ...
	        }
	    }


	    private void hide(final Stage stage) {
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                if (SystemTray.isSupported()) {
	                    stage.hide();
	                } else {
	                    System.exit(0);
	                }
	            }
	        });
	    }
}