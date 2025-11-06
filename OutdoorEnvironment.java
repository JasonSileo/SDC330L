/**********************************
 * OutdoorEnvironment.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Simulates outdoor growing conditions with drip irrigation.
 **********************************/

public class OutdoorEnvironment extends Environment {
    private LightSystem lightSystem;

    public OutdoorEnvironment() {
        super("Outdoor", 75.0, 68.0);
        this.lightSystem = new LightSystem("Sunlight", 0);
    }

    @Override
    public void regulate() {
        System.out.println("Outdoor drip system stabilizing water temperature at " + waterTemp + "°F.");
        System.out.println("Natural sunlight provides optimal light exposure for plant growth.");
    }

    public LightSystem getLightSystem() { return lightSystem; }
}
