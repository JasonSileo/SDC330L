/**********************************
 * LightSystem.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Manages light settings for indoor and outdoor environments.
 **********************************/

public class LightSystem {
    private String lightType;
    private int lightHours;
    private int intensity;

    public LightSystem(String lightType, int lightHours) {
        this.lightType = lightType;
        this.lightHours = lightHours;
        this.intensity = 15; // start low for seedlings
    }

    public void adjustIntensity(int amount) {
        intensity = Math.max(15, Math.min(100, intensity + amount));
    }

    public String getLightType() { return lightType; }
    public int getLightHours() { return lightHours; }
    public int getIntensity() { return intensity; }

    public void displayStatus() {
        System.out.println("Light Type: " + lightType + " | Hours: " + lightHours + " | Intensity: " + intensity + "%");
    }
}
