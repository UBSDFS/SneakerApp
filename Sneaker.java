/*
*Name: Ulysses Burden III
*Date: December 5, 2025
*Assignment: Sneaker Closet App
*Description: This class represents a Sneaker in the Sneaker Closet Application.
*It includes attributes such as brand, size, color, and type.
 */
public class Sneaker implements Displayable {

    // Attributes inherited from Wearable
    private int sneakerID;
    private String name;
    private String brand;
    private double size;

    // Composition relationship with StyleProfile
    private StyleProfile styleProfile;

    // Constructor
    public Sneaker(int sneakerID, String name, String brand, double size, StyleProfile styleProfile) {
        this.sneakerID = sneakerID;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.styleProfile = styleProfile;
    }

    // Getters and Setters
    public int getSneakerID() {
        return sneakerID;
    }

    public void setSneakerID(int sneakerID) {
        this.sneakerID = sneakerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public StyleProfile getStyleProfile() {
        return styleProfile;
    }

    public void setStyleProfile(StyleProfile styleProfile) {
        this.styleProfile = styleProfile;
    }

    // Display method
    @Override
    public String display() {
        return "Sneaker ID: " + sneakerID
                + " | Name: " + name
                + " | Brand: " + brand
                + " | Size: " + size
                + " | Style Profile: " + styleProfile.toString();
    }
}
