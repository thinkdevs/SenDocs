package com.thinkdevs.sendocs.manager;

import com.thinkdevs.sendocs.model.OrderModel;

/**
 * @author Maxim Tikhanovskiy
 */
public interface IOrdersListManager {
    void add(OrderModel orderModel);
    void delete();
    void selectOrder(OrderModel orderModel, int index);
}
