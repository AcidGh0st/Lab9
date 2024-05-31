package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    @Test
    void testBST() {
        try {
            BST bst = new BST();
            BST bstAlfabeto = new BST();
            BST bstNombres = new BST();

            // Insertar objetos en los árboles
            // a. Un árbol binario simple con 100 números aleatorios entre 0 y 500
            int addedCount = 0;
            while (addedCount < 100) {
                int value = util.Utility.getRandom(500);
                try {
                    if (!bst.contains(value)) {
                        bst.add(value);
                        addedCount++;
                    }
                } catch (TreeException e) {
                    //Por si el árbol está vacío, entonces añade un valor
                    bst.add(value);
                    addedCount++;
                }
            }

            // b. Un árbol binario de búsqueda con las letras del alfabeto
            String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (char letra : alfabeto.toCharArray()) {
                bstAlfabeto.add(letra);
            }

            // c. Un árbol binario simple con 10 nombres de personas
            String[] nombres = {"Alex", "Valentina", "Jimena", "Diana", "Daniel", "Juan", "Luis", "Hank", "James", "Jack"};
            for (String nombre : nombres) {
                bstNombres.add(nombre);
            }

            // b. Mostrar el contenido del árbol por consola
            System.out.println("Contenido del árbol bst:");
            System.out.println("PreOrder:");
            System.out.println(bst.preOrder());
            System.out.println("InOrder:");
            System.out.println(bst.inOrder());
            System.out.println("PostOrder:");
            System.out.println(bst.postOrder());

            // c. Mostrar el tamaño, mínimo y máximo del árbol bst
            System.out.println("Tamaño del árbol bst: " + bst.size());
            System.out.println("Elemento mínimo en el árbol bst: " + bst.min());
            System.out.println("Elemento máximo en el árbol bst: " + bst.max());

            // d. Comprobar la existencia de elementos en el árbol bst
            System.out.println("\nComprobando la existencia de elementos en el árbol bst:");
            List<Integer> numerosAleatorios = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                int value = util.Utility.getRandom(500);
                numerosAleatorios.add(value);
                System.out.println("¿El árbol bst contiene el valor " + value + "? " + (bst.contains(value) ? "Sí" : "No"));
            }

            // d. Comprobar la existencia de letras en el árbol bstAlfabeto
            System.out.println(" \nComprobando la existencia de letras en el árbol bstAlfabeto:");
            char[] letrasParaVerificar = {'A', 'E', 'I', 'O', 'U'};
            for (char letra : letrasParaVerificar) {
                System.out.println("¿El árbol bstAlfabeto contiene la letra '" + letra + "'? " + (bstAlfabeto.contains(letra) ? "Sí" : "No"));
            }

            // d. Comprobar la existencia de nombres en el árbol bstNombres
            System.out.println("\nComprobando la existencia de nombres en el árbol bstNombres:");
            String[] nombresParaVerificar = {"Alex", "Jimena", "Daniel", "Luis", "Jack"};
            for (String nombre : nombresParaVerificar) {
                System.out.println("¿El árbol bstNombres contiene el nombre '" + nombre + "'? " + (bstNombres.contains(nombre) ? "Sí" : "No"));
            }

            // e. Agregar 10 números aleatorios entre 0 y 20 en el árbol bst
            System.out.println("\nAgregando 10 números aleatorios entre 0 y 20 en el árbol bst:");
            for (int i = 0; i < 10; i++) {
                int numeroAleatorio = util.Utility.getRandom(20);
                bst.add(numeroAleatorio);
            }

            // f. Comprobar si el árbol bst está balanceado
            System.out.println("\n¿Está el árbol bst equilibrado? " + (bst.isBalanced() ? "Sí" : "No"));

            // g. Eliminar 5 elementos del árbol bst
            System.out.println("\nEliminando 5 elementos del árbol bst:");
            for (int i = 0; i < 5; i++) {
                int value = util.Utility.getRandom(500);
                bst.remove(value);
            }

            // h. Mostrar el contenido del árbol bst por consola
            System.out.println("Contenido del árbol bst después de las eliminaciones:");
            System.out.println("PreOrder:");
            System.out.println(bst.preOrder());
            System.out.println("InOrder:");
            System.out.println(bst.inOrder());
            System.out.println("PostOrder:");
            System.out.println(bst.postOrder());

            // i. Volver a comprobar si el árbol bst está balanceado
            System.out.println("\n¿Está el árbol bst equilibrado después de las eliminaciones? " + (bst.isBalanced() ? "Sí" : "No"));

            // j. Mostrar la altura de cada elemento del árbol bst
            System.out.println("\nAltura de cada elemento del árbol bst:");
            showHeight(bst.getRoot(), bst);

        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    private void showHeight(BTreeNode node, BST bst) throws TreeException {
        if (node != null) {
            System.out.println("Elemento: " + node.data + ", Altura: " + bst.height(node.data));
            showHeight(node.left, bst);
            showHeight(node.right, bst);
        }
    }
}
