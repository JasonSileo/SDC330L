/**********************************
 * Plant.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Abstract base class representing a plant with shared properties.
 **********************************/

public abstract class Plant implements Growable {
    protected String name;
    protected double idealPH;
    protected String nutrientMix;
    protected String type;

    public Plant(String name, double idealPH, String nutrientMix, String type) {
        this.name = name;
        this.idealPH = idealPH;
        this.nutrientMix = nutrientMix;
        this.type = type;
    }

    public String getName() { return name; }
    public double getIdealPH() { return idealPH; }
    public String getNutrientMix() { return nutrientMix; }
    public String getType() { return type; }

    public void displayInfo() {
        System.out.println(name + " (" + type + ") - Ideal pH: " + idealPH + ", Nutrients: " + nutrientMix);
    }

    @Override
    public abstract void grow();
}
