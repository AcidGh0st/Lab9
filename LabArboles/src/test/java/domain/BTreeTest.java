package domain;

import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import domain.stack.LinkedStack;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

class BTreeTest {

    //    @Test
    void test() {
        BTree bTree = new BTree();
        int a[] = new int[10];
        for (int i = 0; i < 10; i++) {
            int value = util.Utility.getRandom(50);
            bTree.add(value);
            a[i] = value;
        }

        try {
            System.out.println(bTree.toString());
            System.out.println("Size: " + bTree.size());
            int value = util.Utility.getRandom(50);
            if (bTree.contains(value)) {
//                bTree.remove(value);
                System.out.println("Value: " + value + " was deleted");
            } else System.out.println("Value: " + value + " does not exist");

            for (int i = 0; i < a.length; i++) {
                System.out.println("height(a[: " + a[i] + "]) = " + bTree.height(a[i]));
            }

            System.out.println("Tree Height : " + bTree.height());
            System.out.println(bTree.toString());

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void BTreeTest2(){
        try {
            BTree btree = new BTree();

            // Insertar objetos en el árbol
            SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
            CircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();
            LinkedStack linkedStack = new LinkedStack();
            LinkedQueue linkedQueue = new LinkedQueue();
            BTree subBTree = new BTree();

            for (int i = 0; i < 20; i++) {
                singlyLinkedList.add(util.Utility.getRandom(50) + 1);
            }

            circularDoublyLinkedList.add("Valentina");
            circularDoublyLinkedList.add("Jimena");
            circularDoublyLinkedList.add("Alex");
            circularDoublyLinkedList.add("Felipe");
            circularDoublyLinkedList.add("Cristina");
            circularDoublyLinkedList.add("Joel");
            circularDoublyLinkedList.add("Michael");
            circularDoublyLinkedList.add("Alejandro");
            circularDoublyLinkedList.add("María");
            circularDoublyLinkedList.add("Julian");
            circularDoublyLinkedList.add("Katherin");
            circularDoublyLinkedList.add("Leonardo");
            circularDoublyLinkedList.add("Mercedes");
            circularDoublyLinkedList.add("Nicol");
            circularDoublyLinkedList.add("Oscar");
            circularDoublyLinkedList.add("Paula");
            circularDoublyLinkedList.add("Maryam");
            circularDoublyLinkedList.add("Rosa");
            circularDoublyLinkedList.add("Samantha");
            circularDoublyLinkedList.add("Jorge");

            linkedStack.push("Costa Rica");
            linkedStack.push("Colombia");
            linkedStack.push("Argentina");
            linkedStack.push("Mexico");
            linkedStack.push("Brasil");
            linkedStack.push("Italia");
            linkedStack.push("España");
            linkedStack.push("Alemania");
            linkedStack.push("Francia");
            linkedStack.push("Panamá");


            for (int i = 0; i < 100; i++) {
                linkedQueue.enQueue(util.Utility.getRandom(4000) + 1000);
            }

            for (char ch = 'A'; ch <= 'Z'; ch++) {
                subBTree.add(ch);
            }

            btree.add(singlyLinkedList);
            btree.add(circularDoublyLinkedList);
            btree.add(linkedStack);
            btree.add(linkedQueue);
            btree.add(subBTree);

            System.out.println("Size: " + btree.size());
            System.out.println("Height: " + btree.height());
            System.out.println("Height of SinglyLinkedList: " + btree.height(singlyLinkedList));
            System.out.println("Contains CircularDoublyLinkedList: " + btree.contains(circularDoublyLinkedList));


            System.out.println();

            btree.remove(linkedQueue);
            int random = util.Utility.getRandom(50);
            for (int i = 0; i < 5; i++) {
                while(!singlyLinkedList.contains(random)){
                    random= util.Utility.getRandom(50);
                }
                singlyLinkedList.remove(random);
            }


            circularDoublyLinkedList.remove("Valentina");
            circularDoublyLinkedList.remove("Jimena");
            circularDoublyLinkedList.remove("Felipe");
            circularDoublyLinkedList.remove("Michael");
            circularDoublyLinkedList.remove("Alex");

            linkedStack.pop();
            linkedStack.pop();
            linkedStack.pop();
            linkedStack.pop();
            linkedStack.pop();

            for (int i = 0; i < 5; i++) {
                char ch = (char) (util.Utility.getRandom(26) + 'A');
                subBTree.remove(ch);
            }

            System.out.println(btree.toString());


            System.out.println("Height of SinglyLinkedList: " + btree.height(singlyLinkedList));
            System.out.println("Height of CircularDoublyLinkedList: " + btree.height(circularDoublyLinkedList));
            System.out.println("Height of LinkedStack: " + btree.height(linkedStack));
            System.out.println("Height of BTree: " + btree.height(subBTree));
        } catch (QueueException | StackException | TreeException | ListException e) {
            throw new RuntimeException(e);
        }
    }
}