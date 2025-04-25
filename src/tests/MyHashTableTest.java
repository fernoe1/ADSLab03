package tests;

import models.MyHashTable;

public class MyHashTableTest {
    public static void start() {
        MyHashTable<Integer, String> table = new MyHashTable<>();
        System.out.println("---------------------------------------------");
        System.out.println("My Hash Table");
        System.out.println("---------------------------------------------");
        System.out.println();

        table.put(1, "Temirlan Sapargali");
        table.put(2, "Nursultan Khaimuldin");
        table.put(3, "Askar Khaimuldin");

        // testing get
        System.out.println("Testing get method:");
        System.out.println("Value for key " + 1 + ": " + table.get(1));
        System.out.println("Value for key " + 2 + ": " + table.get(2));
        System.out.println("Value for key " + 3 + ": " + table.get(3));

        // testing remove
        System.out.println("Testing remove method:");
        System.out.println("Removing key " + 2);
        table.remove(2);

        // testing contains
        System.out.println("Testing contains method:");
        System.out.println("Does table contain value Temirlan Sapargali? " + table.contains("Temirlan Sapargali"));
        System.out.println("Does table contain value Nursultan Khaimuldin? " + table.contains("Nursultan Khaimuldin"));

        // testing get key
        System.out.println("Testing getKey method:");
        System.out.println("Key for value Temirlan Sapargali: " + table.getKey("Temirlan Sapargali"));
        System.out.println("Key for value Nursultan Khaimuldin: " + table.getKey("Nursultan Khaimuldin"));
    }
}
