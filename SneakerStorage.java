/*
 * Name: Ulysses Burden III
 * Assignment: Sneaker Closet App
 * Date: December 3, 2025
 * Description: Handles all database operations for Sneakers (CRUD).
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SneakerStorage {

    // Database connection
    private Connection conn;

    // Constructor to establish connection
    public SneakerStorage(String databaseFile) {
        this.conn = SQLiteDatabase.connect(databaseFile);
        if (this.conn != null) {
            createTableIfNotExists();
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }

    // Create Sneakers table if it doesn't exist
    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Sneakers (
                SneakerID    INTEGER PRIMARY KEY,
                Name         TEXT NOT NULL,
                Brand        TEXT NOT NULL,
                Size         REAL NOT NULL,
                PrimaryStyle TEXT,
                SecondaryStyle TEXT,
                SneakerType  TEXT
            );
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating Sneakers table: " + e.getMessage());
        }
    }

    // CRUD operations to add, remove, get, and update sneakers
    public void addSneaker(Sneaker sneaker) {
        String sql = """
            INSERT INTO Sneakers (SneakerID, Name, Brand, Size, PrimaryStyle, SecondaryStyle, SneakerType)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sneaker.getSneakerID());
            pst.setString(2, sneaker.getName());
            pst.setString(3, sneaker.getBrand());
            pst.setDouble(4, sneaker.getSize());
            pst.setString(5, sneaker.getStyleProfile().getPrimaryStyle());
            pst.setString(6, sneaker.getStyleProfile().getSecondaryStyle());
            pst.setString(7, sneaker.getSneakerType());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting sneaker: " + e.getMessage());
        }
    }

    public void removeSneaker(int sneakerID) {
        String sql = "DELETE FROM Sneakers WHERE SneakerID = ?;";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sneakerID);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting sneaker: " + e.getMessage());
        }
    }

    public List<Sneaker> getAllSneakers() {
        List<Sneaker> sneakers = new ArrayList<>();

        String sql = "SELECT SneakerID, Name, Brand, Size, PrimaryStyle, SecondaryStyle, SneakerType FROM Sneakers;";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("SneakerID");
                String name = rs.getString("Name");
                String brand = rs.getString("Brand");
                double size = rs.getDouble("Size");
                String primaryStyle = rs.getString("PrimaryStyle");
                String secondaryStyle = rs.getString("SecondaryStyle");
                String sneakerType = rs.getString("SneakerType");

                // Rebuild the StyleProfile from DB values
                StyleProfile styleProfile = new StyleProfile(primaryStyle, secondaryStyle);

                Sneaker sneaker;
                if ("HIGH".equalsIgnoreCase(sneakerType)) {
                    sneaker = new HighTopSneaker(id, name, brand, size, styleProfile);
                } else if ("LOW".equalsIgnoreCase(sneakerType)) {
                    sneaker = new LowTopSneaker(id, name, brand, size, styleProfile);
                } else {
                    // Fallback to base Sneaker if null/unknown type
                    sneaker = new Sneaker(id, name, brand, size, styleProfile);
                }

                sneakers.add(sneaker);
            }

        } catch (SQLException e) {
            System.out.println("Error reading sneakers: " + e.getMessage());
        }

        return sneakers;
    }

    public Sneaker getSneakerById(int sneakerID) {
        String sql = "SELECT SneakerID, Name, Brand, Size, PrimaryStyle, SecondaryStyle, SneakerType "
                + "FROM Sneakers WHERE SneakerID = ?;";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sneakerID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("SneakerID");
                    String name = rs.getString("Name");
                    String brand = rs.getString("Brand");
                    double size = rs.getDouble("Size");
                    String primaryStyle = rs.getString("PrimaryStyle");
                    String secondaryStyle = rs.getString("SecondaryStyle");
                    String sneakerType = rs.getString("SneakerType");

                    StyleProfile styleProfile = new StyleProfile(primaryStyle, secondaryStyle);

                    Sneaker sneaker;
                    if ("HIGH".equalsIgnoreCase(sneakerType) || "H".equalsIgnoreCase(sneakerType)) {
                        sneaker = new HighTopSneaker(id, name, brand, size, styleProfile);
                    } else if ("LOW".equalsIgnoreCase(sneakerType) || "L".equalsIgnoreCase(sneakerType)) {
                        sneaker = new LowTopSneaker(id, name, brand, size, styleProfile);
                    } else {
                        sneaker = new Sneaker(id, name, brand, size, styleProfile);
                    }

                    return sneaker;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching sneaker by ID: " + e.getMessage());
        }
        return null; // not found
    }

    public boolean updateSneaker(Sneaker sneaker) {
        String sql = """
        UPDATE Sneakers
        SET Name = ?, Brand = ?, Size = ?, PrimaryStyle = ?, SecondaryStyle = ?, SneakerType = ?
        WHERE SneakerID = ?;
        """;

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sneaker.getName());
            pst.setString(2, sneaker.getBrand());
            pst.setDouble(3, sneaker.getSize());
            pst.setString(4, sneaker.getStyleProfile().getPrimaryStyle());
            pst.setString(5, sneaker.getStyleProfile().getSecondaryStyle());
            pst.setString(6, sneaker.getSneakerType());
            pst.setInt(7, sneaker.getSneakerID());

            int rows = pst.executeUpdate();
            return rows > 0;   // true if something was updated
        } catch (SQLException e) {
            System.out.println("Error updating sneaker: " + e.getMessage());
            return false;
        }
    }

}
