package com.thinkdevs.sendocs;

import com.thinkdevs.sendocs.viewModel.OrdersListViewModel;
import com.thinkdevs.sendocs.viewModel.OrderViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Maxim Tikhanovskiy
 */
public class Main extends Application {

    private Stage primaryStage;
    private Scene primaryScene;
    private AnchorPane ordersList;
    private AnchorPane messagesList;
    private AnchorPane messageCreate;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRoot();
    }

    /**
     * Initialize rootView
     */
    public void initRoot (){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/rootView.fxml"));
            Parent parent = loader.load();
            primaryScene = new Scene(parent);

            ordersList = (AnchorPane) primaryScene.lookup("#anchorPaneOrdersList");
            messagesList = (AnchorPane) primaryScene.lookup("#anchorPaneMessagesList");
            messageCreate = (AnchorPane) primaryScene.lookup("#anchorPaneCreateMessage");

            //Заполнение видами
            ordersList.getChildren().add(initOrdersList());
            System.out.println(messagesList);
            System.out.println(messagesList.getChildren());
            messagesList.getChildren().add(initOrderHistory());

            primaryStage.setTitle("SenDoc");
            primaryStage.setScene(primaryScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize ordersListView
     * @return ordersList
     */
    public Node initOrdersList (){
        Node ordersList;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ordersListView.fxml"));
            loader.setController(new OrdersListViewModel());
            ordersList = loader.load();
            return ordersList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initialize orderHistoryView
     * @return orderHistory
     */
    public Node initOrderHistory (){
        Node orderHistory;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/orderHistoryView.fxml"));
            loader.setController(new OrderViewModel());
            orderHistory = loader.load();
            return orderHistory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
