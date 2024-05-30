package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GraphicBstAvlController
{
    @javafx.fxml.FXML
    private Button isBalancedButton;
    @javafx.fxml.FXML
    private Canvas canvas;
    @javafx.fxml.FXML
    private Button levelsButton;
    @javafx.fxml.FXML
    private Button tourInfoButton;
    @javafx.fxml.FXML
    private Pane drawTreePane;
    @javafx.fxml.FXML
    private TextArea tourInfoTextArea;
    @javafx.fxml.FXML
    private Button randomizeButton;
    @FXML
    private RadioButton radioButtonBST;
    @FXML
    private RadioButton radioButtonAVL;
    @FXML
    private Label lbl_Balanced;
    private BST bst;
    private AVL avl;

    @javafx.fxml.FXML
    public void initialize() {
        bst = new BST();
        avl = new AVL();
    }

    @javafx.fxml.FXML
    public void radioButtonBST(ActionEvent actionEvent) {
        radioButtonAVL.setSelected(false);
    }

    @javafx.fxml.FXML
    public void levelsOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void isBalancedOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void tourInfoOnAction(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void radioButtonAVL(ActionEvent actionEvent) {
        radioButtonBST.setSelected(false);
    }
}