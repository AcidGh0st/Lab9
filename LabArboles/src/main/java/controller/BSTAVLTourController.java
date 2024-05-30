package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BSTAVLTourController {

    @FXML
    private Button btn_Randomize;
    @FXML
    private Button btn_InOrder;
    @FXML
    private Button btn_PostOrder;
    @FXML
    private Button btn_PreOrder;
    @FXML
    private AVL avl;

    private int counter;
    private List<BTreeNode> visitedNodes;
    @FXML
    private Pane AVLTree_Pane;
    @FXML
    private RadioButton BST_RadioButton;
    @FXML
    private RadioButton ALV_RadioButton;
    private AVL tree;
    private BST treeBST;

    @FXML
    private void initialize() {
        ToggleGroup treeToggleGroup = new ToggleGroup();
        ALV_RadioButton.setToggleGroup(treeToggleGroup);
        BST_RadioButton.setToggleGroup(treeToggleGroup);
        ALV_RadioButton.setSelected(true); // Default selection

        initializeTree();
        visitedNodes = new ArrayList<>(); // Initialize the visited nodes list

        System.out.println("Initialization complete.");
    }

    private void initializeTree() {
        if (ALV_RadioButton.isSelected()) {
            avl = new AVL();
            System.out.println("AVL Tree initialized.");
        } else if (BST_RadioButton.isSelected()) {
            treeBST = new BST();
            System.out.println("BST Tree initialized.");
        }
    }

    @FXML
    public void RandomizeOnAction(ActionEvent actionEvent) throws TreeException {
        if (avl == null) {
            System.out.println("AVL tree is not initialized.");
            return;
        }
        counter = 1;
        avl.clear();
        for (int i = 0; i < 10; i++) {
            avl.add(new Random().nextInt(100)); // Agrega números aleatorios al árbol
        }
        drawTree();
    }

    private void drawTree() {
        if (avl == null) {
            System.out.println("AVL tree is not initialized.");
            return;
        }
        AVLTree_Pane.getChildren().clear();
        Canvas canvas = new Canvas(AVLTree_Pane.getWidth(), AVLTree_Pane.getHeight());
        GraphicsContext gC = canvas.getGraphicsContext2D();
        counter = 1; // Resetea el contador
        drawNode(gC, avl.getRoot(), AVLTree_Pane.getWidth() / 2, 50, 150); // ajusta el espaciado horizontal
        AVLTree_Pane.getChildren().add(canvas);
    }

    private void drawNode(GraphicsContext gc, BTreeNode node, double x, double y, double spacing) {
        if (node != null) {
            if (node.getLeft() != null) {
                double childX = x - spacing;
                double childY = y + 100;
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getLeft(), childX, childY, spacing * 0.75); // ajusta el espacio horizontal
            }
            if (node.getRight() != null) {
                double childX = x + spacing;
                double childY = y + 100;
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getRight(), childX, childY, spacing * 0.75); // ajusta el espacio horizontal
            }
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.fillOval(x - 25, y - 25, 50, 50);
            gc.strokeOval(x - 25, y - 25, 50, 50);
            gc.setFill(Color.BLACK);
            gc.fillText(node.getData().toString(), x - 5, y + 5);

            // Dibuja el contador debajo del nodo si está en la lista de nodos visitados
            int index = visitedNodes.indexOf(node);
            if (index != -1) { // Si el nodo está en la lista de nodos visitados
                gc.fillText(String.valueOf(index + 1), x - 5, y + 35); // Muestra el índice debajo del nodo
            }
        }
    }

    private void drawConnection(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);
    }

    @FXML
    public void PreOrderOnAction(ActionEvent actionEvent) {
        if (avl == null) {
            System.out.println("AVL tree is not initialized.");
            return;
        }
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        preOrder(avl.getRoot());
        drawTree();
    }

    private void preOrder(BTreeNode node) {
        if (node != null) {
            visitedNodes.add(node); // Agrega el nodo a la lista de nodos visitados
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    @FXML
    public void InOrderOnAction(ActionEvent actionEvent) {
        if (avl == null) {
            System.out.println("AVL tree is not initialized.");
            return;
        }
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        inOrder(avl.getRoot());
        drawTree();
    }

    private void inOrder(BTreeNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            visitedNodes.add(node); // Agrega el nodo a la lista de nodos visitados
            inOrder(node.getRight());
        }
    }

    @FXML
    public void PostOrderOnAction(ActionEvent actionEvent) {
        if (avl == null) {
            System.out.println("AVL tree is not initialized.");
            return;
        }
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        postOrder(avl.getRoot());
        drawTree();
    }

    private void postOrder(BTreeNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            visitedNodes.add(node); // Agrega el nodo a la lista de nodos visitados
        }
    }
}
