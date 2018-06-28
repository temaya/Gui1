package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;

import javafx.stage.Stage;
import sample.Item;

public class FirstController {
    @FXML private ListView<Item> itemListView;

    @FXML private ListView<Item> itemListView1;

    @FXML private Button nextButton;

    @FXML private Button connectButton;

    @FXML private Button disconnectButton;

    @FXML private Button scanButton;

    @FXML private MenuItem newMenuItem;

    @FXML private MenuItem quitMenuItem;

    @FXML private MenuItem removeMenuItem;

    private ObservableList<Item> hostList;
    private ObservableList<Item> sensortagList;

    public void initialize(){
        hostList = FXCollections.observableArrayList();
        sensortagList = FXCollections.observableArrayList();

        //generateDefaultItems();
        itemListView.setItems(hostList);
        itemListView1.setItems(sensortagList);

        registerEventHandlers();
    }


    private void registerEventHandlers() {
        //Buttons
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    nextStage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        connectButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                connectSensors();
            }
        });

        disconnectButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                disconnectSensors();
            }
        });

        scanButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                generateDefaultItems();
                generateHostandSensors();
            }
        });

        //


        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));
        quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCombination.SHORTCUT_DOWN));
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                hostList.clear();
                sensortagList.clear();
            }
        });

        removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Item item = itemListView.getSelectionModel().getSelectedItem();
                hostList.remove(item);

            }
        });

    }

    private void generateDefaultItems() {
        for(int i=0;i<2;i++){
            hostList.add(new Item("host"+Integer.toString(i)));
            sensortagList.add(new Item("sensorTag"+Integer.toString(i)));
        }
    }

    private void generateHostandSensors() {
        //nhét hàm lấy tên host ở đây và add vào itemlist
    }

    private void connectSensors() {
        sensortagList.clear();
        sensortagList.add(new Item("sensor1 connected"));
    }

    private void disconnectSensors() {
    }

    private void nextStage() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxmls/secondStage.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage)scanButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

                //Stage stage = new Stage();
                //stage.initModality(Modality.APPLICATION_MODAL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
