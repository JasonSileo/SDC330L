/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class VegetablePlant extends Plant {
    // Default constructor
    public VegetablePlant() {
        super("Generic Vegetable", 6.5, "NPK 8-12-10", new Environment("Outdoor Garden", 6.5, 75));
    }

    // Parameterized constructor
    public VegetablePlant(String name, double idealPH, String nutrientMix, Environment environment) {
        super(name, idealPH, nutrientMix, environment);
    }

    @Override
    public void grow() {
        System.out.println(name + " is producing edible leaves.");
    }

    @Override
    public void harvest() {
        System.out.println("Harvesting fresh " + name + ".");
    }

    @Override
    public void checkStatus() {
        System.out.println(name + " plant status: Thriving with full leaf growth.");
    }
}
