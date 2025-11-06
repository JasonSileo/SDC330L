/**********************************
 * VegetablePlant.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Represents a vegetable-type plant (derived from Plant).
 **********************************/

public class VegetablePlant extends Plant {
    private boolean isRootCrop;

    public VegetablePlant(String name, double idealPH, String nutrientMix, boolean isRootCrop) {
        super(name, idealPH, nutrientMix, "VEGETABLE");
        this.isRootCrop = isRootCrop;
    }

    @Override
    public void grow() {
        System.out.println(name + " is growing as a vegetable under ideal pH " + idealPH + " using " + nutrientMix + ".");
    }

    public boolean isRootCrop() { return isRootCrop; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Root Crop: " + (isRootCrop ? "Yes" : "No"));
    }
}
