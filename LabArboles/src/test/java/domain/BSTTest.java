package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    @Test
    void prueba() {
        try {
            BST bst = new BST();

            List<Integer> numerosAleatorios = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                int value = util.Utility.getRandom(500);
                numerosAleatorios.add(value);
                bst.add(value);
            }

            String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (char letra : alfabeto.toCharArray()) {
                bst.add(letra);
            }

            String[] nombres = {"Alex", "Valentina", "Jimena", "Diana", "Daniel", "Juan", "Luis", "Hank", "James", "Jack"};
            for (String nombre : nombres) {
                bst.add(nombre);
            }

            System.out.println("Recorridos del árbol:");
            System.out.println("PreOrder traversal:");
            System.out.println(bst.preOrder());
            System.out.println("InOrder traversal:");
            System.out.println(bst.inOrder());
            System.out.println("PostOrder traversal:");
            System.out.println(bst.postOrder());

            try {
                System.out.println("Tamaño del árbol: " + bst.size());
                System.out.println("Elemento mínimo en el árbol: " + bst.min());
                System.out.println("Elemento máximo en el árbol: " + bst.max());
            } catch (TreeException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 5; i++) {
                assertTrue(bst.contains(numerosAleatorios.get(i)));
            }

            char[] letrasParaVerificar = {'A', 'E', 'I', 'O', 'U'};
            for (char letra : letrasParaVerificar) {
                assertTrue(bst.contains(letra));
            }

            String[] nombresParaVerificar = {"Alex", "Jimena", "Daniel", "Luis", "Jack"};
            for (String nombre : nombresParaVerificar) {
                assertTrue(bst.contains(nombre));
            }

            for (int i = 0; i < 10; i++) {
                int numeroAleatorio = (int) (Math.random() * 21);
                bst.add(numeroAleatorio);
            }

            System.out.println("¿Está el árbol equilibrado? " + bst.isBalanced());

            for (int i = 0; i < 5; i++) {
                bst.remove(numerosAleatorios.get(i));
            }

            System.out.println("Recorridos del árbol después de las eliminaciones:");
            System.out.println("PreOrder traversal:");
            System.out.println(bst.preOrder());
            System.out.println("InOrder traversal:");
            System.out.println(bst.inOrder());
            System.out.println("PostOrder traversal:");
            System.out.println(bst.postOrder());

            System.out.println("¿Está el árbol equilibrado después de las eliminaciones? " + bst.isBalanced());

            System.out.println("Alturas de los elementos en el árbol:");
            for (String nombre : nombres) {
                try {
                    System.out.println(nombre + ": " + bst.height(nombre));
                } catch (TreeException e) {
                    e.printStackTrace();
                }
            }
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }
}
