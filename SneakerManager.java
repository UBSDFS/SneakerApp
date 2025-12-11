/*
*Name: Ulysses Burden III
*Date: December 8, 2025
*Assignment: Sneaker Closet App
*Description: This class manages the sneaker collection in the Sneaker Closet Application.
*It holds the logic/loops for the menu and user interactions.
*It allows users to add, remove, view, and organize their sneakers.
 */

import java.util.List;
import java.util.Scanner;

public class SneakerManager {

    // Collection of sneakers and storage handler for database operations
    private SneakerStorage storage;
    private Scanner scanner;

    // Constructor
    public SneakerManager() {
        this.scanner = new Scanner(System.in);
        this.storage = new SneakerStorage("Sneaker.db");

    }

    // Methods to manage sneakers
    public void addSneaker(Sneaker sneaker) {
        storage.addSneaker(sneaker);
    }

    public void removeSneaker(int sneakerID) {
        storage.removeSneaker(sneakerID);
    }

    public List<Sneaker> viewSneakers() {
        return storage.getAllSneakers();
    }

    public void updateSneaker(Sneaker sneaker) {
        storage.updateSneaker(sneaker);
    }

    // Helper methods to read user input
    private String readString(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    // Read integer with validation
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Read double with validation
    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String line = scanner.nextLine();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Main application loop
    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt("Choose an option:");
            handleChoice(choice);
        } while (choice != 0);
    }

    // Display menu options
    private void showMenu() {
        System.out.println("=== Welcome to the Sneaker Closet App ===");
        System.out.println();
        System.out.println("Developed by Ulysses Burden III");
        System.out.println();
        System.out.println("You can manage your sneaker collection here.");
        System.out.println("Use the menu below to navigate:");
        System.out.println("1. Add Sneaker");
        System.out.println("2. Remove Sneaker");
        System.out.println("3. View Sneakers");
        System.out.println("4. Update Sneaker");
        System.out.println("0. Exit");
    }

    // Handle user menu choice
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addSneakerFromInput();
                break;
            case 2:
                removeSneakerFromInput();
                break;
            case 3:
                viewSneakersInStorage();
                break;
            case 4:
                updateSneakerFromInput();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Methods for menu actions
    private void addSneakerFromInput() {
        System.out.println("Adding a new sneaker...");

        int sneakerID = readInt("Enter Sneaker ID:");
        String name = readString("Enter Sneaker Name:");
        String brand = readString("Enter Sneaker Brand:");
        double size = readDouble("Enter Sneaker Size:");

        String primaryStyle = readString("Enter Primary Style:");
        String secondaryStyle = readString("Enter Secondary Style (or leave blank):");

        StyleProfile styleProfile = new StyleProfile(primaryStyle, secondaryStyle);
        String typeInput = readString("Enter Sneaker Type (H for High-Top, L for Low-Top):");
        Sneaker sneaker;
        if (typeInput.equalsIgnoreCase("H")) {
            sneaker = new HighTopSneaker(sneakerID, name, brand, size, styleProfile);
        } else if (typeInput.equalsIgnoreCase("L")) {
            sneaker = new LowTopSneaker(sneakerID, name, brand, size, styleProfile);
        } else {
            System.out.println("Invalid type entered. Defaulting to regular Sneaker.");
            sneaker = new Sneaker(sneakerID, name, brand, size, styleProfile);
        }
        addSneaker(sneaker);

        System.out.println("Sneaker added successfully!");
    }

    // Remove sneaker by ID
    private void removeSneakerFromInput() {
        System.out.println("Removing a sneaker...");
        int sneakerID = readInt("Enter Sneaker ID to remove:");
        removeSneaker(sneakerID);
        System.out.println("If a sneaker with that ID existed, it has been removed.");
    }

    // View all sneakers in the collection
    private void viewSneakersInStorage() {
        System.out.println("Viewing all sneakers...");
        List<Sneaker> sneakers = viewSneakers();

        if (sneakers.isEmpty()) {
            System.out.println("No sneakers in the collection.");
            return;
        }
        for (Sneaker sneaker : sneakers) {
            System.out.println(sneaker.display());
        }
    }

    //update sneaker
    private void updateSneakerFromInput() {
        System.out.println("Updating a sneaker...");
        int sneakerID = readInt("Enter Sneaker ID to update:");
        Sneaker existingSneaker = storage.getSneakerById(sneakerID);

        if (existingSneaker == null) {
            System.out.println("No sneaker found with that ID.");
            return;
        }

        // Display current details
        System.out.println("Current sneaker details:");
        System.out.println(existingSneaker.display());

        // Name (press Enter to keep current)
        String nameInput = readString("Enter new Sneaker Name (or press Enter to keep: "
                + existingSneaker.getName() + "):");
        String name = nameInput.isBlank() ? existingSneaker.getName() : nameInput;

        // Brand
        String brandInput = readString("Enter new Sneaker Brand (or press Enter to keep: "
                + existingSneaker.getBrand() + "):");
        String brand = brandInput.isBlank() ? existingSneaker.getBrand() : brandInput;

        // Size
        String sizeInput = readString("Enter new Sneaker Size (or press Enter to keep: "
                + existingSneaker.getSize() + "):");
        double size;
        if (sizeInput.isBlank()) {
            size = existingSneaker.getSize();
        } else {
            try {
                size = Double.parseDouble(sizeInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size, keeping existing value.");
                size = existingSneaker.getSize();
            }
        }

        // Primary Style
        String primaryInput = readString("Enter new Primary Style (or press Enter to keep: "
                + existingSneaker.getStyleProfile().getPrimaryStyle() + "):");
        String primaryStyle = primaryInput.isBlank()
                ? existingSneaker.getStyleProfile().getPrimaryStyle()
                : primaryInput;

        // Secondary Style
        String secondaryInput = readString("Enter new Secondary Style (or press Enter to keep: "
                + existingSneaker.getStyleProfile().getSecondaryStyle() + "):");
        String secondaryStyle = secondaryInput.isBlank()
                ? existingSneaker.getStyleProfile().getSecondaryStyle()
                : secondaryInput;

        StyleProfile updatedProfile = new StyleProfile(primaryStyle, secondaryStyle);

        // Preserve the sneaker type (HIGH / LOW / STANDARD)
        String sneakerType = existingSneaker.getSneakerType();

        Sneaker updatedSneaker;
        if ("HIGH".equalsIgnoreCase(sneakerType)) {
            updatedSneaker = new HighTopSneaker(sneakerID, name, brand, size, updatedProfile);
        } else if ("LOW".equalsIgnoreCase(sneakerType)) {
            updatedSneaker = new LowTopSneaker(sneakerID, name, brand, size, updatedProfile);
        } else {
            updatedSneaker = new Sneaker(sneakerID, name, brand, size, updatedProfile);
        }

        boolean success = storage.updateSneaker(updatedSneaker);

        if (success) {
            System.out.println("Sneaker updated successfully!");
        } else {
            System.out.println("No sneaker with that ID was found. Nothing was updated.");
        }

    }
}
