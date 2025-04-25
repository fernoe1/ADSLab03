package tests;

import models.MyBST;

public class MyBSTTest {
    public static void start() {
        MyBST<Integer, String> myBST = new MyBST<>();
        System.out.println("---------------------------------------------");
        System.out.println("My Binary Search Tree");
        System.out.println("---------------------------------------------");
        System.out.println();

        myBST.put(8, "Eight");
        myBST.put(3, "Three");
        myBST.put(10, "Ten");
        myBST.put(1, "One");
        myBST.put(6, "Six");
        myBST.put(4, "Four");
        myBST.put(7, "Seven");
        myBST.put(14, "Fourteen");

        // testing get
        System.out.println("Testing get method:");
        System.out.println("Getting key 3 " + myBST.get(3));

        // testing size
        System.out.println("Size of the BST: " + myBST.getSize());

        // testing delete
        System.out.println("Deleting a leaf node:");
        myBST.delete(1);
        System.out.println("Size after deletion: " + myBST.getSize());
        printInOrder(myBST);

        System.out.println("Deleting a node with one child:");
        myBST.delete(10);
        System.out.println("Size after deletion: " + myBST.getSize());
        printInOrder(myBST);

        System.out.println("Deleting a node with two children:");
        myBST.delete(6);
        System.out.println("Size after deletion: " + myBST.getSize());
        printInOrder(myBST);
    }

    private static void printInOrder(MyBST<Integer, String> myBST) {
        System.out.println("Printing in order..");
        for (MyBST.MyNode element : myBST) {
            System.out.println("Key " + element.getKey() + " Value " + element.getValue());
        }
    }
}
