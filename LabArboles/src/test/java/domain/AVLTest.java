package domain;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {

    @Test
            public void testALV() throws TreeException {
        AVL avl = new AVL();

        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            avl.add(rand.nextInt(100));
        }

        System.out.println(avl.toString());


        System.out.println("Size: " + avl.size());
        System.out.println("Min: " + avl.min());
        System.out.println("Max: " + avl.max());

        System.out.println("Is balanced: " + avl.isBalanced());


        System.out.println("Secuencia de rotaciones: ");
        System.out.println(avl.getSequence());

        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(100);
            System.out.println("Removing: " + num);
            avl.remove(num);
        }

        System.out.println(avl.toString());


        System.out.println("Is balanced: " + avl.isBalanced());


        System.out.println(avl.toString());

        System.out.println("Is balanced: " + avl.isBalanced());

    }
}

