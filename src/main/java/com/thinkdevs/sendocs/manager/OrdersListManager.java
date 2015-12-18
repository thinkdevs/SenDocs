package com.thinkdevs.sendocs.manager;

import com.thinkdevs.sendocs.model.OrdersListModel;
import com.thinkdevs.sendocs.model.OrderModel;

/**
 * @author Maxim Tikhanovskiy
 */
public class OrdersListManager implements IOrdersListManager {

    private OrdersListModel ordersListModel;

    public OrdersListManager(OrdersListModel ordersListModel) {
        this.ordersListModel = ordersListModel;
    }

    @Override
    public void add(OrderModel orderModel) {
        ordersListModel.add(orderModel);
    }

    @Override
    public void delete() {
        ordersListModel.delete();
    }

    @Override
    public void selectOrder(OrderModel orderModel, int index) {
        ordersListModel.setSelectedOrder(orderModel, index);
    }
}
