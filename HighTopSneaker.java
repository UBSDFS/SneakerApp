/*
*Name: Ulysses Burden III
*Date: December 8, 2025
*Assignment: Sneaker Closet App
*Description: This class represents a High-Top Sneaker in the Sneaker Closet Application.
*It extends the Sneaker class and adds specific attributes or methods for high-top sneakers.
* shows inheritance from Sneaker.
 */
public class HighTopSneaker extends Sneaker {

    public HighTopSneaker(int sneakerID, String name, String brand,
            double size, StyleProfile styleProfile) {
        super(sneakerID, name, brand, size, styleProfile);
    }

    @Override
    public String display() {
        return super.display() + " (High-Top)";
    }

    @Override
    public String getSneakerType() {
        return "HIGH";
    }
}
