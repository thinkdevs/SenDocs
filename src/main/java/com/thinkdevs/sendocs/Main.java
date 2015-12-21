package com.thinkdevs.sendocs;

import com.thinkdevs.sendocs.service.ViewCreatingService;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author Maxim Tikhanovskiy
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewCreatingService viewCreatingService = ViewCreatingService.getInstance();
        viewCreatingService.setPrimaryStage(primaryStage);
        viewCreatingService.initRoot();
    }

}
