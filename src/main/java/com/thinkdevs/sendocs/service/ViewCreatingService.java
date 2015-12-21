package com.thinkdevs.sendocs.service;

import com.thinkdevs.sendocs.viewModel.MessageCreateDialogViewModel;
import com.thinkdevs.sendocs.viewModel.OrderViewModel;
import com.thinkdevs.sendocs.viewModel.OrdersListViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Maxim Tikhanovskiy
 */
public class ViewCreatingService {

    private Stage primaryStage;

    private static ViewCreatingService instance;

    private ViewCreatingService() {

    }

    public static ViewCreatingService getInstance(){
        if (instance == null) {
            instance = new ViewCreatingService();
        }
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initialize rootView
     */
    public void initRoot (){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/rootView.fxml"));
            Parent parent = loader.load();
            Scene primaryScene = new Scene(parent);

            AnchorPane ordersList = (AnchorPane) primaryScene.lookup("#anchorPaneOrdersList");
            AnchorPane messagesList = (AnchorPane) primaryScene.lookup("#anchorPaneMessagesList");

            //Заполнение видами
            ordersList.getChildren().add(initOrdersListPaneView());
            messagesList.getChildren().add(initOrderHistoryPaneView());

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
    public Node initOrdersListPaneView(){
        Node ordersList;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ordersListPaneView.fxml"));
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
    public Node initOrderHistoryPaneView(){
        Node orderHistory;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/orderHistoryPaneView.fxml"));
            loader.setController(new OrderViewModel());
            orderHistory = loader.load();
            return orderHistory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showMessageCreateDialog(){
        try {
            Stage dialogStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/messageCreateDialog.fxml"));
            loader.setController(new MessageCreateDialogViewModel(dialogStage));
            AnchorPane page = (AnchorPane) loader.load();

            dialogStage.setTitle("Новое сообщение");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
