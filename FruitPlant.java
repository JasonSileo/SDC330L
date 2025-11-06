/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class FruitPlant extends Plant {
    // Default constructor
    public FruitPlant() {
        super("Generic Fruit", 6.0, "NPK 10-10-10", new Environment("Indoor Tent", 6.0, 70));
    }

    // Parameterized constructor
    public FruitPlant(String name, double idealPH, String nutrientMix, Environment environment) {
        super(name, idealPH, nutrientMix, environment);
    }

    @Override
    public void grow() {
        System.out.println(name + " plant is flowering and setting fruit.");
    }

    @Override
    public void harvest() {
        System.out.println("Harvesting ripe fruit from " + name + ".");
    }

    @Override
    public void checkStatus() {
        System.out.println(name + " plant status: Healthy and fruiting.");
    }
}
