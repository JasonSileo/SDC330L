/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class LightSystem {
    private int intensity;

    public LightSystem() {
        this.intensity = 80;
    }

    public LightSystem(int intensity) {
        this.intensity = intensity;
    }

    public int getIntensity() {
        return intensity;
    }

    public void displayInfo() {
        System.out.println("Light Intensity: " + intensity + "%");
    }
}
