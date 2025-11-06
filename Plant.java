/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public abstract class Plant implements Growable {
    protected String name;
    protected double idealPH;
    protected String nutrientMix;
    protected Environment environment;

    // Default constructor
    public Plant() {
        this.name = "Generic Plant";
        this.idealPH = 6.0;
        this.nutrientMix = "Standard Mix";
        this.environment = new Environment("Default Environment", 6.0, 70);
    }

    // Parameterized constructor
    public Plant(String name, double idealPH, String nutrientMix, Environment environment) {
        this.name = name;
        this.idealPH = idealPH;
        this.nutrientMix = nutrientMix;
        this.environment = environment;
    }

    // Copy constructor
    public Plant(Plant other) {
        this.name = other.name;
        this.idealPH = other.idealPH;
        this.nutrientMix = other.nutrientMix;
        this.environment = other.environment;
    }

    // Display information
    public void displayInfo() {
        System.out.println("Plant: " + name);
        System.out.println("Ideal pH: " + idealPH);
        System.out.println("Nutrient Mix: " + nutrientMix);
        environment.displayInfo();
    }

    // Abstract method demonstrating additional abstraction
    public abstract void checkStatus();
}
