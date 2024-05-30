package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Optional;

public class BstAvlOperationsController
{
    @javafx.fxml.FXML
    private RadioButton radioButtonBST;
    @javafx.fxml.FXML
    private RadioButton radioButtonAVL;
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
    @FXML
    private Label lbl_Balanced;

    private BST bst;
    private AVL avl;
    private boolean isBSTSelected = true;

    @javafx.fxml.FXML
    public void initialize() {
        bst = new BST();
        avl = new AVL();
        textInfo.setVisible(true);
    }

    @javafx.fxml.FXML
    public void treeHeighOnAction(ActionEvent actionEvent)  {
        try {
            int height;
            if (isBSTSelected)
                height = bst.height();
            else
                height = avl.height();
            textInfo.setText("Tree Height: " + height);
        } catch (TreeException e) {
            textInfo.setText("Error: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void nodeHeightOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Altura del Nodo");
        dialog.setHeaderText("Ingresar el valor del nodo");
        dialog.setContentText("Valor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int height;
                if (isBSTSelected)
                    height = bst.height(Integer.parseInt(result.get()));
                else
                    height = avl.height(Integer.parseInt(result.get()));
                textInfo.setText("Altura del nodo " + result.get() + ": " + height);
            } catch (NumberFormatException | TreeException e) {
                textInfo.setText("Error: Por favor, ingrese un valor entero válido.");
            }
        }
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Nodo");
        dialog.setHeaderText("Ingresar el valor del nodo a eliminar");
        dialog.setContentText("Valor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int value = Integer.parseInt(result.get());
                if (isBSTSelected) {
                    if (bst.contains(value)) {
                        bst.remove(value);
                        drawTree();
                        textInfo.setText("Nodo " + result.get() + " eliminado.");
                    } else {
                        textInfo.setText("Nodo " + result.get() + " no existe en el árbol.");
                    }
                } else {
                    if (avl.contains(value)) {
                        avl.remove(value);
                        drawTree();
                        textInfo.setText("Nodo " + result.get() + " eliminado.");
                    } else {
                        textInfo.setText("Nodo " + result.get() + " no existe en el árbol.");
                    }
                }
            } catch (NumberFormatException | TreeException e) {
                textInfo.setText("Error: Por favor, ingrese un valor entero válido.");
            }
        }
    }


    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Nodo");
        dialog.setHeaderText("Ingresar el valor del nodo a agregar");
        dialog.setContentText("Valor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int value = Integer.parseInt(result.get());
                if (isBSTSelected) {
                    bst.add(value);
                    drawTree();
                    textInfo.setText("Nodo " + result.get() + " agregado.");
                } else {
                    avl.add(value);
                    drawTree();
                    textInfo.setText("Nodo " + result.get() + " agregado.");
                }
            } catch (NumberFormatException e) {
                textInfo.setText("Por favor, ingrese un valor entero válido.");
            }
        }
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        randomizeButton.setDisable(false);
        containsButton.setDisable(false);
        removeButton.setDisable(false);
        addButton.setDisable(false);
        nodeHeightButton.setDisable(false);
        treeHeighButton.setDisable(false);
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
        if (isBSTSelected)
            lbl_Balanced.setText("BST Balanced: " + bst.isBalanced());
        else
            lbl_Balanced.setText("AVL Balanced: " + avl.isBalanced());
        textInfo.setText("Árbol randomizado.");
    }


    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Contiene Nodo");
        dialog.setHeaderText("Ingresar el valor del nodo a buscar");
        dialog.setContentText("Valor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int value = Integer.parseInt(result.get());
                boolean contains;
                if (isBSTSelected) {
                    contains = bst.contains(value);
                } else {
                    contains = avl.contains(value);
                }

                if (contains) {
                    textInfo.setText("El nodo " + result.get() + " existe en el árbol.");
                } else {
                    textInfo.setText("El nodo " + result.get() + " no existe en el árbol.");
                }
            } catch (NumberFormatException | TreeException e) {
                textInfo.setText("Error: Por favor, ingrese un valor entero válido.");
            }
        }
    }

    @javafx.fxml.FXML
    public void radioButtonBST(ActionEvent actionEvent) {
        radioButtonAVL.setSelected(false);
        isBSTSelected = true;
        drawTree();
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
            gc.fillOval(x - 20, y - 20, 40, 40);
            gc.strokeOval(x - 20, y - 20, 40, 40);
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