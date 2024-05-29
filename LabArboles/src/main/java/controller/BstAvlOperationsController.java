package controller;

import domain.BTree;
import domain.BTreeNode;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BstAvlOperationsController
{
    @javafx.fxml.FXML
    private Canvas canvas;
    @javafx.fxml.FXML
    private TextArea textInfo;
    @javafx.fxml.FXML
    private Button containsButton;
    @javafx.fxml.FXML
    private Button removeButton;
    @javafx.fxml.FXML
    private Button addButton;
    @javafx.fxml.FXML
    private Button nodeHeightButton;
    @javafx.fxml.FXML
    private Button treeHeighButton;
    @javafx.fxml.FXML
    private Pane drawTreePane;
    @javafx.fxml.FXML
    private TextArea tourInfoTextArea;
    @javafx.fxml.FXML
    private Button randomizeButton;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void treeHeighOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void nodeHeightOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
    }
}