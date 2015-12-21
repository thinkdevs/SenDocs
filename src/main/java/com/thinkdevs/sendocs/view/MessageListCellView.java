package com.thinkdevs.sendocs.view;


import com.thinkdevs.sendocs.model.MessageModel;
import com.thinkdevs.sendocs.viewModel.MessageViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;

/**
 * @author Maxim Tikhanovskiy
 */
public class MessageListCellView extends ListCell<MessageModel> {

    @Override
    protected void updateItem(MessageModel message, boolean empty) {
        super.updateItem(message, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }
        if (message != null) {
            MessageViewModel messageView = new MessageViewModel(message);
            this.setPadding(new Insets(5));
            setGraphic(messageView.getView());
        }
    }
}
