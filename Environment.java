/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class Environment {
    private String name;
    private double ph;
    private int waterTemp;

    public Environment() {
        this.name = "Default Environment";
        this.ph = 6.0;
        this.waterTemp = 70;
    }

    public Environment(String name, double ph, int waterTemp) {
        this.name = name;
        this.ph = ph;
        this.waterTemp = waterTemp;
    }

    public String getName() { return name; }
    public double getPh() { return ph; }
    public int getWaterTemp() { return waterTemp; }

    public void displayInfo() {
        System.out.println("Environment: " + name);
        System.out.println("Water Temp: " + waterTemp + "°F");
        System.out.println("pH Level: " + ph);
    }
}
