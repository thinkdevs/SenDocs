package com.thinkdevs.sendocs.viewModel;

import com.thinkdevs.sendocs.Settings;
import com.thinkdevs.sendocs.model.MessageModel;
import com.thinkdevs.sendocs.model.OrderModel;
import com.thinkdevs.sendocs.model.OrdersListModel;
import com.thinkdevs.sendocs.utils.FileSender;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Maxim Tikhanovskiy
 */
public class MessageCreateDialogViewModel implements Initializable {

    private final String ORDER_NUMBER = String.valueOf(OrdersListModel.getInstance().getSelectedOrder().getOrderNumber());
    private final String URL_FROM = Settings.FROM_FOLDER + ORDER_NUMBER + "\\";
    private final String URL_TO = Settings.ORDERS_REPOSITORY + ORDER_NUMBER + "\\" + Settings.RZIA_FOLDER;

    @FXML
    private TextField tfSubjectMsg;
    @FXML
    private TextArea taTextMsg;
    @FXML
    private ListView<String> lvFilesMsg;
    @FXML
    private Button btnAddFileMsg;
    @FXML
    private Button btnSendMsg;
    @FXML
    private Button btnCancelMsg;

    private Stage stage;
    private SimpleListProperty<String> files = new SimpleListProperty<>(FXCollections.observableArrayList());

    public MessageCreateDialogViewModel(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSendMsg.setOnAction(event -> {
            String subject = tfSubjectMsg.getText();
            String text = taTextMsg.getText();

            FileSender fileSender = new FileSender(URL_TO);
            fileSender.send(files.get());
            MessageModel message = new MessageModel(subject, text, files);
            OrdersListModel.getInstance().getSelectedOrder().addMessage(message);
            try {
                Runtime.getRuntime().exec("explorer.exe "
                        + URL_TO);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });

        btnCancelMsg.setOnAction(event -> stage.close());

        btnAddFileMsg.setOnAction(event -> {
        FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(URL_FROM));
        lvFilesMsg.setItems(files);
        List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
            for (File file : fileList) {
                files.add(file.getAbsolutePath());
            }
        });

    }

    /**
     * Validates user input in the text fields
     * @return true if the input valid
     */
//    private boolean isInputValid() {
//        String errorMessage = "";
//        if(null == tfSubjectMsg.getText() || tfSubjectMsg.getText().length() == 0){
//            errorMessage += "Введите тему! \n";
//        }
//
//        if(errorMessage.length() == 0){
//            return true;
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.initOwner(dialogStage);
//            alert.setTitle("Некоректные данные");
//            alert.setHeaderText("Исправте некоректные данные");
//            alert.setContentText(errorMessage);
//
//            alert.showAndWait();
//
//            return false;
//        }
//    }
}
