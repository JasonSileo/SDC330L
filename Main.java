/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("HydroGarden Manager - Week 3 Project");
        System.out.println("By: Jason Sileo");
        System.out.println("==========================================");
        System.out.println("Welcome! Demonstrating Abstraction, Constructors, and Access Control.\n");

        Environment indoor = new IndoorEnvironment("Grow Tent", 6.0, 70, 85);
        Environment outdoor = new OutdoorEnvironment("Backyard Bed", 6.3, 75, "Summer");

        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new FruitPlant("Tomato", 6.0, "NPK 10-10-10", indoor));
        plants.add(new VegetablePlant("Lettuce", 6.3, "NPK 8-12-10", outdoor));

        for (Plant p : plants) {
            p.displayInfo();
            p.grow();
            p.harvest();
            p.checkStatus();
            System.out.println("----------------------------------");
        }

        System.out.println("\nThank you for using HydroGarden Manager!");
    }
}
