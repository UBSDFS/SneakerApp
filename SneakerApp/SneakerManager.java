
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
*Name: Ulysses Burden III
*Date: November 24, 2025
*Assignment: Sneaker Closet App
*Description: This class manages the sneaker collection in the Sneaker Closet Application.
*It holds the logic/loops for the menu and user interactions.
*It allows users to add, remove, view, and organize their sneakers.
 */
public class SneakerManager {

    private List<Sneaker> sneakerCollection;
    private Scanner scanner;

    // Constructor
    public SneakerManager() {
        this.sneakerCollection = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // ===== Core logic methods =====
    public void addSneaker(Sneaker sneaker) {
        sneakerCollection.add(sneaker);
    }

    public void removeSneaker(int sneakerID) {
        sneakerCollection.removeIf(sneaker -> sneaker.getSneakerID() == sneakerID);
    }

    public List<Sneaker> viewSneakers() {
        return sneakerCollection;
    }

    // ===== Input helpers =====
    private String readString(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

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

    // ===== Menu logic and user interaction =====
    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt("Choose an option:");
            handleChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("=== Sneaker Closet Menu ===");
        System.out.println("1. Add Sneaker");
        System.out.println("2. Remove Sneaker");
        System.out.println("3. View Sneakers");
        System.out.println("0. Exit");
    }

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
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addSneakerFromInput() {
        System.out.println("Adding a new sneaker...");

        int sneakerID = readInt("Enter Sneaker ID:");
        String name = readString("Enter Sneaker Name:");
        String brand = readString("Enter Sneaker Brand:");
        double size = readDouble("Enter Sneaker Size:");

        Sneaker sneaker = new Sneaker(sneakerID, name, brand, size);
        addSneaker(sneaker);

        System.out.println("Sneaker added successfully!");
    }

    private void removeSneakerFromInput() {
        System.out.println("Removing a sneaker...");
        int sneakerID = readInt("Enter Sneaker ID to remove:");
        removeSneaker(sneakerID);
        System.out.println("If a sneaker with that ID existed, it has been removed.");
    }

    private void viewSneakersInStorage() {
        System.out.println("Viewing all sneakers...");
        if (sneakerCollection.isEmpty()) {
            System.out.println("No sneakers in the collection.");
            return;
        }
        for (Sneaker sneaker : sneakerCollection) {
            System.out.println(sneaker.display());
        }
    }
}
