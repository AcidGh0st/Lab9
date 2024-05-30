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
    private boolean isBSTSelected = true;
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
        isBSTSelected = true;
        drawTree();
    }

    @javafx.fxml.FXML
    public void levelsOnAction(ActionEvent actionEvent) {
        try {
            StringBuilder levels = new StringBuilder();
            if (radioButtonBST.isSelected()) {
                drawLevels(drawTreePane.getWidth() / 2, 50, 100, bst.getRoot(), 0);
                for (int i = 0; i <= bst.height(); i++) {
                    levels.append(i);
                }
                tourInfoTextArea.setText(levels.toString());
            } else if(radioButtonAVL.isSelected()) {
                drawLevels(drawTreePane.getWidth() / 2, 50, 100, avl.getRoot(), 0);
                for (int i = 0; i <= avl.height(); i++) {
                    levels.append(i);
                }
                tourInfoTextArea.setText(levels.toString());
            }
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void isBalancedOnAction(ActionEvent actionEvent) {
        if (isBSTSelected)
            lbl_Balanced.setText("BST Balanced: " + bst.isBalanced());
        else
            lbl_Balanced.setText("AVL Balanced: " + avl.isBalanced());
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        levelsButton.setDisable(false);
        isBalancedButton.setDisable(false);
        tourInfoButton.setDisable(false);
        drawTreePane.setVisible(true);
        bst.clear();
        avl.clear();
        for (int i = 0; i < 20; i++) {
            int randomValue = util.Utility.getRandom(100); // Generate random numbers between 0 and 99
            if (isBSTSelected)
                bst.add(randomValue);
            else
                avl.add(randomValue);
        }
        drawTree();
//        textInfo.setText("Árbol randomizado.");
    }

    @javafx.fxml.FXML
    public void tourInfoOnAction(ActionEvent actionEvent) {
        try {
            String info = "";
            if (isBSTSelected) {
                info = "Tree Height: " + bst.height() + "\n";
                info += "PreOrder: " + bst.preOrder() + "\n";
                info += "InOrder: " + bst.inOrder() + "\n";
                info += "PostOrder: " + bst.postOrder();
            }else {
                info = "Tree Height: " + avl.height() + "\n";
                info += "PreOrder: " + avl.preOrder() + "\n";
                info += "InOrder: " + avl.inOrder() + "\n";
                info += "PostOrder: " + avl.postOrder();
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tour Info");
            alert.setHeaderText("Information");
            alert.setContentText(info);
            alert.getDialogPane().setPrefSize(400, 300);
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void radioButtonAVL(ActionEvent actionEvent) {
        radioButtonBST.setSelected(false);
        isBSTSelected = false;
        drawTree();
    }
    private void drawTree() {
        drawTreePane.getChildren().clear();
        canvas = new Canvas(drawTreePane.getWidth(), drawTreePane.getHeight());
        GraphicsContext gC = canvas.getGraphicsContext2D();
        if (isBSTSelected) {
            drawNode(gC, bst.getRoot(), drawTreePane.getWidth() / 2, 50, 150);
        } else {
            drawNode(gC, avl.getRoot(), drawTreePane.getWidth() / 2, 50, 150);
        }
        drawTreePane.getChildren().add(canvas);
    }

    private void drawLevels(double x, double y, double spacing, BTreeNode node, int level) {
        GraphicsContext gC = canvas.getGraphicsContext2D();
        if (node != null) {
            double lineY = y + 20; // Ajuste adicional para bajar más las líneas horizontales
            gC.setStroke(Color.DARKGREEN);
            gC.setLineWidth(1);
            gC.strokeLine(50, lineY, drawTreePane.getWidth() - 50, lineY); // Ajusta la posición vertical de las líneas
            gC.setFill(Color.DARKGREEN);
            gC.fillText("Level " + level, drawTreePane.getWidth() - 100, y - 10); // Enumera el nivel
            if (node.getLeft() != null) {
                double childY = y + 75;
                drawLevels(x - spacing, childY, spacing * 0.75, node.getLeft(), level+1); // Llama recursivamente para el hijo izquierdo
            } if (node.getRight() != null) {
                double childY = y + 75;
                drawLevels(x + spacing, childY, spacing * 0.75, node.getRight(), level+1); // Llama recursivamente para el hijo derecho
            }
        }
    }


    private void drawNode(GraphicsContext gc, BTreeNode node, double x, double y, double spacing) {
        if (node != null) {
            if (node.getLeft() != null) {
                double childX = x - spacing;
                double childY = y + 70; // Reducir el espacio vertical entre los niveles
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getLeft(), childX, childY, spacing * 0.75); // Ajusta el espacio horizontal
            }
            if (node.getRight() != null) {
                double childX = x + spacing;
                double childY = y + 70; // Reducir el espacio vertical entre los niveles
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getRight(), childX, childY, spacing * 0.75); // Ajusta el espacio horizontal
            }
            gc.setFill(Color.CYAN);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.fillOval(x - 20, y - 20, 40, 40); // Reducir el tamaño de los nodos
            gc.strokeOval(x - 20, y - 20, 40, 40); // Reducir el tamaño de los nodos
            gc.setFill(Color.BLACK);
            gc.fillText(node.getData().toString(), x - 5, y + 5);
            gc.fillText(node.path, x - 20, y + 30); // Muestra el path más abajo del nodo
        }
    }

    private void drawConnection(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);
    }

}