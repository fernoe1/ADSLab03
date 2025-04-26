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
        table.put(12, "Nursultan Khaimuldin");
        table.put(23, "Askar Khaimuldin");

        // testing get
        System.out.println("Testing get method:");
        System.out.println("Value for key " + 1 + ": " + table.get(1));
        System.out.println("Value for key " + 12 + ": " + table.get(12));
        System.out.println("Value for key " + 23 + ": " + table.get(23));

        // testing remove
        System.out.println("Testing remove method:");
        System.out.println("Removing key " + 12);
        table.remove(12);

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
