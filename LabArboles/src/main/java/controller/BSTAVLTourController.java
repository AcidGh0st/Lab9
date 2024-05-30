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
        ALV_RadioButton.setSelected(true);

        initializeTree();
        visitedNodes = new ArrayList<>();

        System.out.println("Initialization complete.");
    }


    private void initializeTree() {
        if (ALV_RadioButton.isSelected()) {
            avl = new AVL();
            treeBST = null;
            System.out.println("AVL Tree initialized.");
        } else if (BST_RadioButton.isSelected()) {
            treeBST = new BST();
            avl = null;
            System.out.println("BST Tree initialized.");
        }
    }


    @FXML
    public void RandomizeOnAction(ActionEvent actionEvent) throws TreeException {
        initializeTree();
        counter = 1;

        if (ALV_RadioButton.isSelected() && avl != null) {
            avl.clear();
            for (int i = 0; i < 10; i++) {
                avl.add(new Random().nextInt(100));
            }
        } else if (BST_RadioButton.isSelected() && treeBST != null) {
            treeBST.clear();
            for (int i = 0; i < 10; i++) {
                treeBST.add(new Random().nextInt(100));
            }
        }
        drawTree();
    }


    private void drawTree() {
        AVLTree_Pane.getChildren().clear();
        Canvas canvas = new Canvas(AVLTree_Pane.getWidth(), AVLTree_Pane.getHeight());
        GraphicsContext gC = canvas.getGraphicsContext2D();
        counter = 1;

        if (ALV_RadioButton.isSelected() && avl != null) {
            drawNode(gC, avl.getRoot(), AVLTree_Pane.getWidth() / 2, 50, 150); // Draw AVL tree
        } else if (BST_RadioButton.isSelected() && treeBST != null) {
            drawNode(gC, treeBST.getRoot(), AVLTree_Pane.getWidth() / 2, 50, 150); // Draw BST
        }
        AVLTree_Pane.getChildren().add(canvas);
    }


    private void drawNode(GraphicsContext gc, BTreeNode node, double x, double y, double spacing) {
        if (node != null) {
            if (node.getLeft() != null) {
                double childX = x - spacing;
                double childY = y + 70;
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getLeft(), childX, childY, spacing * 0.75);
            }
            if (node.getRight() != null) {
                double childX = x + spacing;
                double childY = y + 70;
                drawConnection(gc, x, y, childX, childY);
                drawNode(gc, node.getRight(), childX, childY, spacing * 0.75);
            }
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.fillOval(x - 20, y - 20, 40, 40);
            gc.strokeOval(x - 20, y - 20, 40, 40);
            gc.setFill(Color.BLACK);
            gc.fillText(node.getData().toString(), x - 5, y + 5);


            int index = visitedNodes.indexOf(node);
            if (index != -1) {
                gc.fillText(String.valueOf(index + 1), x - 5, y + 35);
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
        if (ALV_RadioButton.isSelected() && avl != null) {
            counter = 1;
            visitedNodes.clear();
            preOrder(avl.getRoot());
        } else if (BST_RadioButton.isSelected() && treeBST != null) {
            counter = 1;
            visitedNodes.clear();
            preOrder(treeBST.getRoot());
        }
        drawTree();
    }

    private void preOrder(BTreeNode node) {
        if (node != null) {
            visitedNodes.add(node);
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }


    @FXML
    public void InOrderOnAction(ActionEvent actionEvent) {
        if (ALV_RadioButton.isSelected() && avl != null) {
            counter = 1;
            visitedNodes.clear();
            inOrder(avl.getRoot());
        } else if (BST_RadioButton.isSelected() && treeBST != null) {
            counter = 1;
            visitedNodes.clear();
            inOrder(treeBST.getRoot());
        }
        drawTree();
    }

    private void inOrder(BTreeNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            visitedNodes.add(node);
            inOrder(node.getRight());
        }
    }


    @FXML
    public void PostOrderOnAction(ActionEvent actionEvent) {
        if (ALV_RadioButton.isSelected() && avl != null) {
            counter = 1;
            visitedNodes.clear();
            postOrder(avl.getRoot());
        } else if (BST_RadioButton.isSelected() && treeBST != null) {
            counter = 1;
            visitedNodes.clear();
            postOrder(treeBST.getRoot());
        }
        drawTree();
    }


    private void postOrder(BTreeNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            visitedNodes.add(node);
        }
    }
}
