/*
*Name: Ulysses Burden III
*Date: November 28, 2025
*Assignment: Sneaker Closet App
*Description: This class defines the style profile of a sneaker, including primary and secondary styles.
*Also shows composition relationship with Sneaker class. Sneaker "has a" StyleProfile.
 */
public class StyleProfile {

    // Attributes to define a sneaker's style profile
    private String primaryStyle;
    private String secondaryStyle;

    // Constructor
    public StyleProfile(String primaryStyle, String secondaryStyle) {
        this.primaryStyle = primaryStyle;
        this.secondaryStyle = secondaryStyle;
    }

    // Getters and Setters
    public String getPrimaryStyle() {
        return primaryStyle;
    }

    public void setPrimaryStyle(String primaryStyle) {
        this.primaryStyle = primaryStyle;
    }

    public String getSecondaryStyle() {
        return secondaryStyle;
    }

    public void setSecondaryStyle(String secondaryStyle) {
        this.secondaryStyle = secondaryStyle;
    }

    // toString method for displaying style profile
    //If secondaryStyle is empty, only return primaryStyle
    @Override
    public String toString() {
        if (secondaryStyle == null || secondaryStyle.isEmpty()) {
            return primaryStyle;
        } else {
            return primaryStyle + " | " + secondaryStyle;
        }
    }
}
