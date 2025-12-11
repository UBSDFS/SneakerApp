/*
*Name: Ulysses Burden III
*Date: December 5, 2025
*Assignment: Sneaker Closet App
*Description: This class represents a Sneaker in the Sneaker Closet Application.
*It includes attributes such as brand, size, color, and type.
 */
public class Sneaker implements Displayable {

    private int sneakerID;
    private String name;
    private String brand;
    private double size;
    private StyleProfile styleProfile;

    public Sneaker(int sneakerID, String name, String brand, double size, StyleProfile styleProfile) {
        this.sneakerID = sneakerID;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.styleProfile = styleProfile;
    }

    public int getSneakerID() {
        return sneakerID;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getSize() {
        return size;
    }

    public StyleProfile getStyleProfile() {
        return styleProfile;
    }

    // This is what subclasses call with super.display()
    @Override
    public String display() {
        return "Sneaker ID: " + sneakerID
                + "| Name: " + name
                + "| Brand: " + brand
                + "| Size: " + size
                + "| Style: ["
                + styleProfile.getPrimaryStyle()
                + (styleProfile.getSecondaryStyle() != null
                && !styleProfile.getSecondaryStyle().isBlank()
                ? ", " + styleProfile.getSecondaryStyle()
                : "")
                + "]";
    }

    // For DB type persistence
    public String getSneakerType() {
        return "STANDARD";
    }
}
