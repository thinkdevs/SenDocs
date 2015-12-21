package com.thinkdevs.sendocs.model;

import com.thinkdevs.sendocs.service.DataBaseService;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.List;

/**
 * @author Maxim Tikhanovskiy
 */
public class OrdersListModel {

    private static OrdersListModel instance;
    private SimpleListProperty<OrderModel> ordersList;
    private SimpleObjectProperty<OrderModel> selectedOrder;
    private int indSelectedOrder;

    public static OrdersListModel getInstance(){
        if(instance == null){
            instance = new OrdersListModel();
        }
        return instance;
    }

    private OrdersListModel() {
        ordersList = new SimpleListProperty<>(FXCollections.observableArrayList());
        List<Integer> ordersNumbers = DataBaseService.getOrdersNumbers();
        for(int number : ordersNumbers) {
            OrderModel orderModel = new OrderModel(number);
            MessageModel messageModel = new MessageModel("Схема " + number, "Новая");
            messageModel.addLink(number + "_Схема.jpg");
            orderModel.addMessage(messageModel);
            ordersList.add(orderModel);
        }
        //Инициализация текущего заказа
        selectedOrder = new SimpleObjectProperty<>(ordersList.get(0));
    }

    public SimpleObjectProperty<OrderModel> selectedOrderPropertyProperty() {
        return selectedOrder;
    }

    public void setSelectedOrder(OrderModel selectedOrder) {
        this.selectedOrder.set(selectedOrder);
    }

    public SimpleListProperty<OrderModel> ordersListProperty() {
        return ordersList;
    }

    public void add(OrderModel orderModel) {
        ordersList.add(orderModel);
    }

    private void delete(int index) {
        ordersList.remove(index);
    }

    public void delete(){
        delete(indSelectedOrder);
    }

    public void setSelectedOrder(OrderModel selectedOrderModel, int indSelectedOrder) {
        this.indSelectedOrder = indSelectedOrder;
        this.selectedOrder.set(selectedOrderModel);
    }

    public OrderModel getSelectedOrder() {
        return selectedOrder.get();
    }

    public SimpleObjectProperty<OrderModel> selectedOrderProperty() {
        return selectedOrder;
    }
}
