package util;

import domain.BTree;
import domain.list.CircularDoublyLinkedList;
import domain.list.SinglyLinkedList;
import domain.queue.LinkedQueue;
import domain.stack.LinkedStack;

import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.StringTemplate.STR;

public class Utility {

    //static init
    static {
    }

    public static String format(double value){
        return new DecimalFormat("###,###,###.##").format(value);
    }
    public static String $format(double value){
        return new DecimalFormat("$###,###,###.##").format(value);
    }
    public static String show(int[] a, int size) {
        String result="";
        for (int i = 0; i < size; i++) {
            result+= STR."\{a[i]} ";
        }
        return result;
    }

    public static void fill(int[] a, int bound) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(bound);
        }
    }

    public static int getRandom(int bound) {
        return new Random().nextInt(bound)+1;
    }

    public static int compare(Object a, Object b) {
        switch (instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0; //0 == equal
            case "String":
                String st1 = (String)a; String st2 = (String)b;
                return st1.compareTo(st2)<0 ? -1 : st1.compareTo(st2) > 0 ? 1 : 0;
            case "Character":
                Character c1 = (Character)a; Character c2 = (Character)b;
                return c1.compareTo(c2)<0 ? -1 : c1.compareTo(c2)>0 ? 1 : 0;
            case "SinglyLinkendList":
                SinglyLinkedList s1 = (SinglyLinkedList)a; SinglyLinkedList s2 = (SinglyLinkedList)b;
                return s1==s2?0:-1;
            case "LinkedQueue":
                LinkedQueue l1 = (LinkedQueue)a; LinkedQueue l2 = (LinkedQueue)b;
                return l1==l2?0:-1;
            case "CircularDoublyLinkedList":
                CircularDoublyLinkedList cdl1 = (CircularDoublyLinkedList)a; CircularDoublyLinkedList cdl2 = (CircularDoublyLinkedList)b;
                return cdl1==cdl2?0:-1;
            case "LinkedStack":
                LinkedStack ls1 = (LinkedStack)a; LinkedStack ls2 = (LinkedStack)b;
                return ls1==ls2?0:-1;
            case "BTree":
                BTree bt1 = (BTree)a; BTree bt2 = (BTree)b;
                return bt1==bt2?0:-1;
        }
        return 2; //Unknown
    }

    private static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof SinglyLinkedList && b instanceof SinglyLinkedList) return "SinglyLinkendList";
        if(a instanceof LinkedQueue && b instanceof LinkedQueue) return "LinkedQueue";
        if(a instanceof CircularDoublyLinkedList && b instanceof CircularDoublyLinkedList) return "CircularDoublyLinkedList";
        if(a instanceof BTree && b instanceof BTree) return "BTree";
        if(a instanceof LinkedStack && b instanceof LinkedStack) return "LinkedStack";
        return "Unknown";
    }
}
