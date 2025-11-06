/**********************************
 * Environment.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Abstract class for different growing environments (Indoor/Outdoor).
 **********************************/

public abstract class Environment {
    protected String environmentType;
    protected double airTemp;
    protected double waterTemp;

    public Environment(String environmentType, double airTemp, double waterTemp) {
        this.environmentType = environmentType;
        this.airTemp = airTemp;
        this.waterTemp = waterTemp;
    }

    public String getEnvironmentType() { return environmentType; }
    public double getAirTemp() { return airTemp; }
    public double getWaterTemp() { return waterTemp; }

    public abstract void regulate();
}
