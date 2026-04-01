//Oliver Brown Class - Contains the main program and menu.
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer i = -1;

        RecordManager recordManager = new RecordManager();
        FileHandler fileHandler = new FileHandler("records.txt");

        while (i != 5) {
            System.out.println("\n1. Add Book\n2. Display Books\n3. " +
                    "Save Records\n4. Load Records\n5. Exit");
            System.out.println("Enter your choice: ");
            String s = scanner.nextLine();

            i = stringToInteger(s);

            if (i == null) {
                System.out.println("Invalid input! Please enter a valid number.");
                i = -1;
                continue;
            }

            switch (i) {
                case 1:
                    addBook(scanner, recordManager);
                    break;
                case 2:
                    displayBooks(recordManager);
                    break;
                case 3:
                    saveRecords(scanner, fileHandler, recordManager);
                    break;
                case 4:
                    loadRecords(scanner, fileHandler, recordManager);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        scanner.close();
    }

    public static void addBook(Scanner scanner, RecordManager recordManager) {
        System.out.println("Enter title: ");
        String newTitle = scanner.nextLine();

        if (newTitle == null || newTitle.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.println("Enter author: ");
        String newAuthor = scanner.nextLine();

        if (newAuthor == null || newAuthor.isEmpty()) {
            System.out.println("Author cannot be empty.");
            return;
        }

        System.out.println("Enter year: ");
        String yearInput = scanner.nextLine();
        Integer newYear = stringToInteger(yearInput);

        if (newYear == null) {
            System.out.println("Invalid year. Please enter another year.");
            return;
        }

        try {
            BookRecord book = new BookRecord(newTitle, newAuthor, newYear);
            recordManager.addRecord(book);
            System.out.println("Record with ID " + book.getId() + " added successfully.");
        }

        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private static void displayBooks(RecordManager recordManager) {
        recordManager.displayAllRecords();
    }

    private static void saveRecords(Scanner scanner, FileHandler fileHandler, RecordManager recordManager) {
        System.out.println("Enter the filename to save to: ");
        String fileName = scanner.nextLine();

        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Filename cannot be empty.");
            return;
        }

        try {
            fileHandler.setFileName(fileName);
            fileHandler.saveRecords(recordManager.getAllRecords());
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void loadRecords(Scanner scanner, FileHandler fileHandler, RecordManager recordManager) {
        System.out.println("Enter filename to load from: ");
        String fileName = scanner.nextLine();

        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Filename cannot be empty.");
            return;
        }

        fileHandler.setFileName(fileName);
        ArrayList<DisplayableRecord> loaded = fileHandler.loadRecords();

        if (loaded != null) {
            recordManager.setRecords(loaded);
            System.out.println("Records have been successfully loaded.");
        }
    }

    public static Integer stringToInteger(String s) {

        try {
            return Integer.parseInt(s.trim());
        }

        catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return null;
        }
    }

}

