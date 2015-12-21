package com.thinkdevs.sendocs.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Maxim Tikhanovskiy
 */
public class OrderModel {

    private final SimpleIntegerProperty orderNumber;
    private final SimpleListProperty<MessageModel> messages;

    public OrderModel(int orderNumber) {
        this.orderNumber = new SimpleIntegerProperty(orderNumber);
        this.messages = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public int getOrderNumber() {
        return orderNumber.get();
    }

    public SimpleIntegerProperty orderNumberProperty() {
        return orderNumber;
    }

    public ObservableList<MessageModel> getMessages() {
        return messages.get();
    }

    public SimpleListProperty<MessageModel> messagesProperty() {
        return messages;
    }

    public void addMessage(MessageModel messageModel) {
        messages.add(messageModel);
    }

    @Override
    public String toString() {
        return String.valueOf(getOrderNumber());
    }
}
