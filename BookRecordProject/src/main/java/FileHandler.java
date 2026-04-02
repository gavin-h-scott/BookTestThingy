import java.util.ArrayList;
import java.io.*;

//Ava Lorimer Class - Save and Load records using serialisation

public class FileHandler {
    private String fileName;

    //Constructor
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }
    //Setter
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    //Getter
    public String getFileName() {
        return fileName;
    }

    //Saves the provided ArrayList to a file named by fileName using ObjectOutputStream. The ArrayList should be of type DisplayableRecord.
    public void saveRecords(ArrayList<DisplayableRecord> records) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getFileName()));
        out.writeObject(records);
        out.close();
    }
    //Loads and returns an ArrayList from the file named by fileName using ObjectInputStream. Any exceptions during loading should be caught here; display an error message and return null
    public ArrayList<DisplayableRecord> loadRecords() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<DisplayableRecord> records = (ArrayList<DisplayableRecord>) in.readObject();
            in.close();
            return records;
        } catch (IOException e) {
            // Error message displayed and null returned
            System.out.println("Error loading records: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            // Error message displayed and null returned
            System.out.println("Error: Class not found - " + e.getMessage());
            return null;
        }
    }
    //Writes the details of each record to readableFileName in a human-readable text format.
    public void exportReadableRecords(ArrayList<DisplayableRecord> records, String readableFileName) throws IOException {
        try (FileWriter out = new FileWriter(readableFileName)) {
            for (DisplayableRecord record : records) {
                out.write(record.toString() + "\n");
            }
        }
    }
}