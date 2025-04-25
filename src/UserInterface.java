import tests.MyBSTTest;
import tests.MyHashTableTest;
import tests.UniformDistributionTest;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        start(new Scanner(System.in));
    }

    private static void start(Scanner sc) {
        while (true) {
            System.out.println("\n[1] Test MyBST");
            System.out.println("[2] Test MyHashTable");
            System.out.println("[3] Test Uniform Distribution");
            System.out.println("[4] Exit\n");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    MyBSTTest.start();
                    break;
                case 2:
                    MyHashTableTest.start();
                    break;
                case 3:
                    UniformDistributionTest.start();
                    break;
                case 4:
                    System.out.println("Exiting..");
                    return;
            }
        }
    }
}