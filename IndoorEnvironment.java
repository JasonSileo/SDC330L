/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class IndoorEnvironment extends Environment {
    private LightSystem lightSystem;

    public IndoorEnvironment(String name, double ph, int waterTemp, int lightIntensity) {
        super(name, ph, waterTemp);
        this.lightSystem = new LightSystem(lightIntensity);
    }

    @Override
    public void displayInfo() {
        System.out.println("\nEnvironment Type: Indoor");
        super.displayInfo();
        lightSystem.displayInfo();
    }
}
