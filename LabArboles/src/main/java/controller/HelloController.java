package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text messageText;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void home(ActionEvent event) {
        this.messageText.setText("Laboratory 8");
        this.bp.setCenter(ap);
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void graphicBTreeOnAction(ActionEvent actionEvent) {
        loadPage("graphicBTree.fxml");
    }

    @FXML
    public void bTreeTourOnAction(ActionEvent actionEvent) {
        loadPage("BTree_Tour.fxml");
    }

    @FXML
    public void bTreeOperationsOnAction(ActionEvent actionEvent) {
        loadPage("bTreeOperations.fxml");
    }
}