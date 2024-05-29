package controller;

import domain.BTree;
import domain.BTreeNode;
import domain.TreeException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BTreeTourController  {
    @FXML
    private Button btn_Randomize;
    @FXML
    private Button btn_InOrder;
    @FXML
    private Button btn_PostOrder;
    @FXML
    private Pane treePane;
    @FXML
    private Button btn_PreOrder;
    @FXML
    private BorderPane bp;
    private Canvas canvas;
    private int counter;

    private BTree binaryTree;
    private List<BTreeNode> visitedNodes; // Lista para almacenar los nodos visitados

    @FXML
    private void initialize() {
        binaryTree = new BTree();
        visitedNodes = new ArrayList<>(); // Inicializa la lista de nodos visitados
    }

    @FXML
    public void RandomizeOnAction(ActionEvent actionEvent) throws TreeException {
        counter = 1;
        binaryTree.clear();
        for (int i = 0; i < 10; i++) {
            binaryTree.add(new Random().nextInt(100)); // Agrega números aleatorios al árbol
        }
        drawTree();
    }
    private void drawTree() {
        treePane.getChildren().clear();
        canvas = new Canvas(treePane.getWidth(), treePane.getHeight());
        GraphicsContext gC = canvas.getGraphicsContext2D();
        counter = 1; // Resetea el contador
        drawNode(gC, binaryTree.getRoot(), treePane.getWidth() / 2, 50, 150); // ajusta el espaciado horizontal
        treePane.getChildren().add(canvas);
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
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        preOrder(binaryTree.getRoot());
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
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        inOrder(binaryTree.getRoot());
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
        counter = 1;
        visitedNodes.clear(); // Limpia la lista de nodos visitados
        postOrder(binaryTree.getRoot());
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
