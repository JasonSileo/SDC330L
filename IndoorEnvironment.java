/**********************************
 * IndoorEnvironment.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Simulates controlled indoor growing conditions.
 **********************************/

public class IndoorEnvironment extends Environment {
    private LightSystem lightSystem;

    public IndoorEnvironment() {
        super("Indoor", 72.0, 68.0);
        this.lightSystem = new LightSystem("Full Spectrum", 15);
    }

    @Override
    public void regulate() {
        System.out.println("Indoor system maintaining stable air at " + airTemp + "°F, water at " + waterTemp + "°F.");
        System.out.println("Light System: " + lightSystem.getLightType() + " (" + lightSystem.getLightHours() + " hrs, " + lightSystem.getIntensity() + "% intensity)");
    }

    public LightSystem getLightSystem() { return lightSystem; }
}
