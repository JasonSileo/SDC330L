/******************************************************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 10/24/2025
 * Class demonstrating use of access specifiers and encapsulation
*/

public class OutdoorEnvironment extends Environment {
    private String season;

    public OutdoorEnvironment(String name, double ph, int waterTemp, String season) {
        super(name, ph, waterTemp);
        this.season = season;
    }

    public OutdoorEnvironment() {
        super("Default Outdoor", 6.4, 75);
        this.season = "Summer";
    }

    @Override
    public void displayInfo() {
        System.out.println("\nEnvironment Type: Outdoor");
        super.displayInfo();
        System.out.println("Season: " + season);
    }
}
