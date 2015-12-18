package com.thinkdevs.sendocs.viewModel;

import com.thinkdevs.sendocs.model.MessageModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Maxim Tikhanovskiy
 */
public class MessageViewModel implements Initializable {

    @FXML
    private AnchorPane apMsg;
    @FXML
    private Label lSubjectMsg;
    @FXML
    private Label lDateMsg;
    @FXML
    private TextFlow tfDesctiptionMsg;

    private MessageModel message;

    public MessageViewModel(MessageModel message) {
        this.message = message;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/messageView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lSubjectMsg.setText(message.getSubject());
        lDateMsg.setText(message.getDate());
        ObservableList<Node> lines = tfDesctiptionMsg.getChildren();
        lines.add(new Text(message.getText()));
        List<String> links = message.getLinks();
        if(null != links && links.size() != 0) {
            int i = 1;
            lines.add(new Text("\n Cсылки: \n"));
            for (String link : links){
                lines.add(new Text( i + ") "));
                Hyperlink hyperlink = new Hyperlink(link);
                hyperlink.setOnAction(event -> {
                    try {
                        Runtime.getRuntime().exec("explorer.exe /select," + "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                lines.add(hyperlink);
                lines.add(new Text(";"));
                i++;
            }
        }
    }

    public Node getView() {
        return apMsg;
    }
}
