package com.thinkdevs.sendocs.viewModel;

import com.thinkdevs.sendocs.model.MessageModel;
import com.thinkdevs.sendocs.model.OrdersListModel;
import com.thinkdevs.sendocs.model.OrderModel;
import com.thinkdevs.sendocs.service.ViewCreatingService;
import com.thinkdevs.sendocs.view.MessageListCellView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Maxim Tikhanovskiy
 */
public class OrderViewModel implements Initializable {
    @FXML
    private Label lOrderNumber;
    @FXML
    private ListView<MessageModel> lvMessages;
    @FXML
    private Button btnCreateMsg;

    private OrderModel orderModel;
    private SimpleObjectProperty<OrderModel> orderProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Получение текущего заказа
        orderProperty = OrdersListModel.getInstance().selectedOrderPropertyProperty();
        lOrderNumber.setText(String.valueOf(orderProperty.get().getOrderNumber()));
        //Связывание модели и представления
        orderProperty.addListener((observable, oldValue, newValue) -> {
            this.orderModel = newValue;
            lOrderNumber.setText(String.valueOf(orderModel.getOrderNumber()));
            lvMessages.setItems(orderModel.getMessages());
            lvMessages.refresh();

        });

        lvMessages.setCellFactory(new Callback<ListView<MessageModel>, ListCell<MessageModel>>() {
            @Override
            public ListCell<MessageModel> call(ListView<MessageModel> param) {
                return new MessageListCellView();
            }
        });

        btnCreateMsg.setOnAction(event -> {
            ViewCreatingService.getInstance().showMessageCreateDialog();
        });
    }
}
