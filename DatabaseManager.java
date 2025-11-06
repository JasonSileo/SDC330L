/**********************************
 * DatabaseManager.java
 * Name: Jason Sileo
 * Date: 11/01/25
 * Purpose: Manages SQLite database, plant catalog, and
 * garden CRUD operations with nutrient, pH, and lighting simulation.
 **********************************/

import java.sql.*;
import java.util.*;

public class DatabaseManager {

    private Connection conn;
    private final Random random = new Random();

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hydrogarden.db");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Database close failed: " + e.getMessage());
        }
    }

    public void createTablesIfNeeded() {
        String createCatalog = "CREATE TABLE IF NOT EXISTS Catalog (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, type TEXT, idealPH REAL)";
        String createGrows = "CREATE TABLE IF NOT EXISTS ActiveGrows (" +
                "gardenNum INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, type TEXT, env TEXT, pH REAL, targetPH REAL, " +
                "airTemp REAL, waterTemp REAL, nutrients INTEGER, light INTEGER, " +
                "stage TEXT, isStarter INTEGER DEFAULT 0)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createCatalog);
            stmt.execute(createGrows);
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void seedCatalogIfEmpty() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Catalog");
            if (rs.next() && rs.getInt(1) == 0) {
                insertCatalogFruits();
                insertCatalogVegetables();
            }
        } catch (SQLException e) {
            System.out.println("Catalog seed error: " + e.getMessage());
        }
    }

    private void insertCatalogFruits() throws SQLException {
        String[] fruits = {
            "Tomato", "Strawberry", "Blueberry", "Grape", "Watermelon", "Apple",
            "Peach", "Mango", "Pineapple", "Lemon", "Raspberry", "Cherry"
        };
        double[] ph = {6.0, 5.8, 4.8, 6.1, 6.4, 6.2, 6.3, 6.0, 5.5, 6.0, 5.7, 6.3};
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Catalog (name, type, idealPH) VALUES (?, 'FRUIT', ?)")) {
            for (int i = 0; i < fruits.length; i++) {
                ps.setString(1, fruits[i]);
                ps.setDouble(2, ph[i]);
                ps.executeUpdate();
            }
        }
    }

    private void insertCatalogVegetables() throws SQLException {
        String[] veg = {
            "Lettuce", "Bell Pepper", "Carrot", "Cucumber", "Broccoli", "Kale",
            "Celery", "Cauliflower", "Eggplant", "Onion", "Spinach", "Jalapeno"
        };
        double[] ph = {6.5, 6.2, 6.3, 6.3, 6.5, 6.1, 6.2, 6.5, 6.3, 6.2, 6.4, 6.2};
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Catalog (name, type, idealPH) VALUES (?, 'VEGETABLE', ?)")) {
            for (int i = 0; i < veg.length; i++) {
                ps.setString(1, veg[i]);
                ps.setDouble(2, ph[i]);
                ps.executeUpdate();
            }
        }
    }

    public void seedStarterGardens() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ActiveGrows");
            if (rs.next() && rs.getInt(1) == 0) {
                addStarter("Tomato", "FRUIT", "Indoor", 72, 68, 100, 100, "Bloom");
                addStarter("Strawberry", "FRUIT", "Indoor", 72, 68, 80, 75, "Grow");
                addStarter("Bell Pepper", "VEGETABLE", "Indoor", 72, 68, 60, 45, "Seedling");
                addStarter("Lettuce", "VEGETABLE", "Outdoor", 75, 68, 100, 0, "Bloom");
                addStarter("Carrot", "VEGETABLE", "Outdoor", 75, 68, 80, 0, "Grow");
                addStarter("Jalapeno", "VEGETABLE", "Outdoor", 75, 68, 60, 0, "Seedling");
            }
        } catch (SQLException e) {
            System.out.println("Starter seed error: " + e.getMessage());
        }
    }

    private void addStarter(String name, String type, String env, double air, double water, int nutr, int light, String stage) {
        String sql = "INSERT INTO ActiveGrows (name, type, env, pH, targetPH, airTemp, waterTemp, nutrients, light, stage, isStarter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, env);
            ps.setDouble(4, 6.0);
            ps.setDouble(5, 6.0);
            ps.setDouble(6, air);
            ps.setDouble(7, water);
            ps.setInt(8, nutr);
            ps.setInt(9, light);
            ps.setString(10, stage);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add starter error: " + e.getMessage());
        }
    }

    // ✅ Updated to return a list of actual catalog IDs
    public List<Integer> listCatalogByType(String type) {
        String sql = "SELECT id, name FROM Catalog WHERE type = ?";
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Available " + type + "S ---");
            int i = 1;
            while (rs.next()) {
                System.out.println(i + ") " + rs.getString("name"));
                ids.add(rs.getInt("id"));
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Catalog query failed: " + e.getMessage());
        }
        return ids;
    }

    public void startGrowIndoor(int plantId) {
        startGrow(plantId, "Indoor");
    }

    public void startGrowOutdoor(int plantId) {
        startGrow(plantId, "Outdoor");
    }

    private void startGrow(int plantId, String env) {
        String sql = "SELECT * FROM Catalog WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, plantId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                double idealPH = rs.getDouble("idealPH");

                double deviation = env.equals("Indoor") ? 0.5 : 0.8;
                double currentPH = idealPH + (random.nextDouble() * deviation * (random.nextBoolean() ? 1 : -1));

                int nutrients = 25;
                int light = env.equals("Indoor") ? 15 : 0;

                System.out.printf("\nStarting new plant: %s — %s\n", name, env);
                System.out.printf("Current pH: %.1f (target %.1f)\n", currentPH, idealPH);
                System.out.println("Adjusting conditions...");
                System.out.printf("Air Temp: %d°F | Water Temp: %d°F | Nutrients: %d%%", env.equals("Indoor") ? 72 : 75, 68, nutrients);
                if (env.equals("Indoor")) System.out.printf(" | Light: %d%%", light);
                System.out.println("\n");

                insertGrow(name, type, env, currentPH, idealPH, env.equals("Indoor") ? 72 : 75, 68, nutrients, light, "Seedling");
            }
        } catch (SQLException e) {
            System.out.println("Start grow error: " + e.getMessage());
        }
    }

    private void insertGrow(String name, String type, String env, double ph, double targetPH, double air, double water, int nutr, int light, String stage) {
        String sql = "INSERT INTO ActiveGrows (name, type, env, pH, targetPH, airTemp, waterTemp, nutrients, light, stage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, env);
            ps.setDouble(4, ph);
            ps.setDouble(5, targetPH);
            ps.setDouble(6, air);
            ps.setDouble(7, water);
            ps.setInt(8, nutr);
            ps.setInt(9, light);
            ps.setString(10, stage);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert grow error: " + e.getMessage());
        }
    }

    public void listActiveGrows() {
        String sql = "SELECT * FROM ActiveGrows ORDER BY gardenNum ASC";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\n--- Active Grows ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                String name = rs.getString("name");
                String type = rs.getString("type");
                String env = rs.getString("env");
                double ph = rs.getDouble("pH");
                double air = rs.getDouble("airTemp");
                double water = rs.getDouble("waterTemp");
                int nutr = rs.getInt("nutrients");
                int light = rs.getInt("light");
                String stage = rs.getString("stage");
                int gardenNum = rs.getInt("gardenNum");

                if (env.equals("Indoor")) {
                    System.out.printf("%-15s (G%d) | %-10s | %-7s | pH:%.1f | Air:%.0f°F | H2O:%.0f°F | Stage:%-10s | Nutr:%d %% | Light:%d %%\n",
                            name, gardenNum, type, env, ph, air, water, stage, nutr, light);
                } else {
                    System.out.printf("%-15s (G%d) | %-10s | %-7s | pH:%.1f | Air:%.0f°F | H2O:%.0f°F | Stage:%-10s | Nutr:%d %%\n",
                            name, gardenNum, type, env, ph, air, water, stage, nutr);
                }
            }
            if (!found) System.out.println("No active grows found.");
        } catch (SQLException e) {
            System.out.println("Failed to list active grows: " + e.getMessage());
        }
    }

    public void endGrow(int gardenNum) {
        String sql = "DELETE FROM ActiveGrows WHERE gardenNum = ? AND isStarter = 0";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gardenNum);
            int affected = ps.executeUpdate();
            if (affected > 0) {
                System.out.println("Grow G" + gardenNum + " ended successfully.");
            } else {
                System.out.println("Cannot delete starter or invalid garden number.");
            }
        } catch (SQLException e) {
            System.out.println("End grow error: " + e.getMessage());
        }
    }
}
