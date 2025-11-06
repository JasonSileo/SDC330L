/**********************************
 * Main.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Console entry point for HydroGarden Manager.
 **********************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        db.connect();
        db.createTablesIfNeeded();
        db.seedCatalogIfEmpty();
        db.seedStarterGardens();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("           HydroGarden Manager");
        System.out.println("               By: Jason Sileo");
        System.out.println("==========================================");
        System.out.println("Integrated plant management system for indoor and outdoor growth.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Indoor Mode:");
        System.out.println(" - Monitors and regulates water temperature, nutrient balance,");
        System.out.println("   lighting cycles, air flow, and plant growth health.");
        System.out.println("Outdoor Mode:");
        System.out.println(" - Tracks soil nutrients, ground temperature, and moisture levels.");
        System.out.println(" - Uses weather data to optimize watering during hot conditions.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Available Crops:");
        System.out.println(" - 12 preset fruits and vegetables suitable for both environments.");
        System.out.println("   Examples include tomatoes, peppers, lettuce, strawberries,");
        System.out.println("   cucumbers, herbs, and more.\n");

        System.out.println("Quick Start Guide:");
        System.out.println(" 1) Select 'Start a new plant cycle' to begin growing a crop.");
        System.out.println(" 2) Choose a preset plant and environment (indoor or outdoor).");
        System.out.println(" 3) Monitor progress through 'View active plants and growth stats'.");
        System.out.println(" 4) End the cycle once harvesting is complete or conditions change.");
        System.out.println("--------------------------------------------------------------\n");

        while (running) {

            System.out.println("All systems stable. Monitoring growth environments...\n");

            System.out.println("1) Start a new plant cycle");
            System.out.println("2) View active plants and growth stats");
            System.out.println("3) End a current growing cycle");
            System.out.println("4) Exit program\n");
            System.out.print("Select an option (1-4): ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    startPlantFlow(sc, db);
                    break;
                case "2":
                    db.listActiveGrows();
                    System.out.println();
                    break;
                case "3":
                    System.out.print("Enter Garden number to end (or 'B' to go back): ");
                    String end = sc.nextLine().trim();
                    if (end.equalsIgnoreCase("B")) break;
                    try {
                        int gardenNum = Integer.parseInt(end);
                        db.endGrow(gardenNum);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        db.close();
        sc.close();
        System.out.println("\nAll plant systems secured.");
        System.out.println("Sensors entering standby mode...");
        System.out.println("Thank you for using HydroGarden Manager!");
        System.out.println("May your next harvest be your best. Goodbye!");
    }

    private static void startPlantFlow(Scanner sc, DatabaseManager db) {
        boolean inStartMenu = true;

        while (inStartMenu) {
            System.out.println("\n=== Start a Plant ===");
            System.out.println("1) Browse FRUITS");
            System.out.println("2) Browse VEGETABLES");
            System.out.println("3) Back to main menu");
            System.out.print("Choice: ");
            String t = sc.nextLine().trim();

            switch (t) {
                case "1":
                case "2":
                    String type = t.equals("1") ? "FRUIT" : "VEGETABLE";
                    List<Integer> ids = db.listCatalogByType(type);
                    if (ids.isEmpty()) {
                        System.out.println("No entries found for type: " + type);
                        continue;
                    }

                    System.out.print("Select plant number (1–" + ids.size() + ") or 'B' to go back: ");
                    String idText = sc.nextLine().trim();
                    if (idText.equalsIgnoreCase("B")) continue;

                    int selectionIndex;
                    try {
                        selectionIndex = Integer.parseInt(idText);
                        if (selectionIndex < 1 || selectionIndex > ids.size()) {
                            System.out.println("Invalid selection.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        continue;
                    }

                    int plantId = ids.get(selectionIndex - 1);

                    System.out.print("Environment (I for Indoor, O for Outdoor): ");
                    String envChoice = sc.nextLine().trim();

                    if (envChoice.equalsIgnoreCase("I")) {
                        db.startGrowIndoor(plantId);
                    } else if (envChoice.equalsIgnoreCase("O")) {
                        db.startGrowOutdoor(plantId);
                    } else {
                        System.out.println("Invalid choice. Use I or O.");
                    }
                    break;

                case "3":
                    inStartMenu = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
