package com.thinkdevs.sendocs.viewModel;

import com.thinkdevs.sendocs.model.OrdersListModel;
import com.thinkdevs.sendocs.model.OrderModel;
import com.thinkdevs.sendocs.manager.OrdersListManager;
import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Maxim Tikhanovskiy
 */
public class OrdersListViewModel implements Initializable {

    @FXML
    private ListView<OrderModel> lvOrders;
    @FXML
    private Button btnAddOrder;
    @FXML
    private Button btnDelOrder;

    private ListProperty<OrderModel> ordersList;

    private OrdersListModel ordersListModel;

    private OrdersListManager ordersListManager;

    public void initialize(URL location, ResourceBundle resources) {
        //Получаем модель
        ordersListModel = OrdersListModel.getInstance();
        //Получаем менеджер списка
        ordersListManager = new OrdersListManager(ordersListModel);
        //Связывание модели и представления
        lvOrders.itemsProperty().bindBidirectional(ordersListModel.ordersListProperty());

        //Обработка действий
        btnAddOrder.setOnAction(event1 -> ordersListManager.add(new OrderModel(15)));
        btnDelOrder.setOnAction(event -> ordersListManager.delete());

        lvOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = lvOrders.getSelectionModel().getSelectedIndex();
            ordersListManager.selectOrder(newValue, index);
        });
    }
}
