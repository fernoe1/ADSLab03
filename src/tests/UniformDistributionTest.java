package tests;

import models.MyHashTable;
import models.MyTestingClass;
import models.Student;

import java.util.Random;

public class UniformDistributionTest {
    public static void start() {
        MyHashTable<MyTestingClass, Student> hashTable = new MyHashTable<>();
        Random random = new Random();
        System.out.println("---------------------------------------------");
        System.out.println("Uniform Distribution Test");
        System.out.println("---------------------------------------------");
        System.out.println();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass randomClass = new MyTestingClass(random.nextInt(10000));
            Student randomStudent = new Student("" + i, random.nextInt(122));
            hashTable.put(randomClass, randomStudent);
        }

        getBucketSizes(hashTable);
    }

    private static void getBucketSizes(MyHashTable<MyTestingClass, Student> hashTable) {
        System.out.println("The data for these buckets are randomly generated each time this test is ran");
        System.out.println("Each bucket should have " + (10000 / hashTable.getM() + " elements on average \n"));

        for (int i = 0; i < hashTable.getM(); i++) {
            System.out.println("Bucket " + i + " has a size of " + hashTable.getBucketSize(i));
        }
    }
}
