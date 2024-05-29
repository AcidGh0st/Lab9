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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GraphicBTreeController {

    private BTree binaryTree;

    @FXML
    private TextArea tourInfoTextArea;

    @FXML
    private Canvas canvas;

    @FXML
    private Button levelsButton;
    @FXML
    private Button tourInfoButton;
    @FXML
    private Button randomizeButton;
    @FXML
    private Pane drawTreePane;

    @FXML
    private void initialize() {
        binaryTree = new BTree();
    }

    @FXML
    public void levelsOnAction(ActionEvent actionEvent) {
        try {
            StringBuilder levels = new StringBuilder();
            drawLevels(drawTreePane.getWidth() / 2, 50, 150, binaryTree.getRoot(), 0);
            for (int i = 0; i <= binaryTree.height(); i++) {
                levels.append(i);
            }
            tourInfoTextArea.setText(levels.toString());
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        levelsButton.setDisable(false);
        tourInfoButton.setDisable(false);
        drawTreePane.setVisible(true);
        binaryTree.clear();
        for (int i = 0; i < 10; i++) {
            binaryTree.add(util.Utility.getRandom(100));
        }
        drawTree(); // Dibuja el árbol
    }

    @FXML
    public void tourInfoOnAction(ActionEvent actionEvent) {
        try {
            String info = "Tree Height: " + binaryTree.height() + "\n";
            info += "PreOrder: " + binaryTree.preOrder() + "\n";
            info += "InOrder: " + binaryTree.inOrder() + "\n";
            info += "PostOrder: " + binaryTree.postOrder();

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

    private void drawTree() {
        drawTreePane.getChildren().clear();
        canvas = new Canvas(drawTreePane.getWidth(), drawTreePane.getHeight());
        GraphicsContext gC = canvas.getGraphicsContext2D();
        drawNode(gC, binaryTree.getRoot(), drawTreePane.getWidth() / 2, 50, 150); // ajusta el espaciado horizontal
        drawTreePane.getChildren().add(canvas);
    }

    private void drawLevels(double x, double y, double spacing, BTreeNode node, int level) {
        GraphicsContext gC = canvas.getGraphicsContext2D();
        if (node != null) {
            double lineY = y + 40; // Ajuste adicional para bajar más las líneas horizontales
            gC.setStroke(Color.DARKGREEN);
            gC.setLineWidth(1);
            gC.strokeLine(50, lineY, drawTreePane.getWidth() - 50, lineY); // Ajusta la posición vertical de las líneas
            gC.setFill(Color.DARKGREEN);
            gC.fillText("Level " + level, drawTreePane.getWidth() - 100, y - 10); // Enumera el nivel
            if (node.getLeft() != null) {
                double childY = y + 100;
                drawLevels(x - spacing, childY, spacing * 0.75, node.getLeft(), level+1); // Llama recursivamente para el hijo izquierdo
            } if (node.getRight() != null) {
                double childY = y + 100;
                drawLevels(x + spacing, childY, spacing * 0.75, node.getRight(), level+1); // Llama recursivamente para el hijo derecho
            }
        }
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
            gc.fillText(node.path, x - 25, y + 40); // Muestra el path más abajo del nodo
        }
    }

    private void drawConnection(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);
    }
}
