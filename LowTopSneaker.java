/*
*Name: Ulysses Burden III
*Date: December 8, 2025
*Assignment: Sneaker Closet App
*Description: This class represents a Low-Top Sneaker in the Sneaker Closet Application.
*It extends the Sneaker class and adds specific attributes or methods for low-top sneakers.
* shows inheritance from Sneaker.
 */
public class LowTopSneaker extends Sneaker {

    public LowTopSneaker(int sneakerID, String name, String brand,
            double size, StyleProfile styleProfile) {
        super(sneakerID, name, brand, size, styleProfile);
    }

    @Override
    public String display() {
        return super.display() + " (Low-Top)";
    }

    @Override
    public String getSneakerType() {
        return "LOW";
    }
}
