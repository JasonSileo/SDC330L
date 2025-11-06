/**********************************
 * FruitPlant.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Represents a fruit-bearing plant (derived from Plant).
 **********************************/

public class FruitPlant extends Plant {
    private boolean producesSeeds;

    public FruitPlant(String name, double idealPH, String nutrientMix, boolean producesSeeds) {
        super(name, idealPH, nutrientMix, "FRUIT");
        this.producesSeeds = producesSeeds;
    }

    @Override
    public void grow() {
        System.out.println(name + " is growing fruit under ideal pH " + idealPH + " using " + nutrientMix + ".");
    }

    public boolean hasSeeds() { return producesSeeds; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Produces Seeds: " + (producesSeeds ? "Yes" : "No"));
    }
}
