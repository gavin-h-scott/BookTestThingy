//Gavin Scott Class - Stores and manages all book records in memory
import java.util.ArrayList;

public class RecordManager {

    private ArrayList<DisplayableRecord> records;

    public RecordManager() {
        records = new ArrayList<>();
    }

    public void addRecord(DisplayableRecord record) {
        for (DisplayableRecord r : records) {
            if (r.equals(record)) {
                throw new IllegalArgumentException("Record already exists");
            }
        }
        this.records.add(record);
    }

    public boolean removeRecord(String id){
        for (DisplayableRecord record : records) {
            if (record.getId().equals(id)) {
                records.remove(record);
                return true;
            }
        }
        return false;
    }

    public ArrayList<DisplayableRecord> getAllRecords(){
        return records;
    }

    public DisplayableRecord findRecordById(String id){
        for (DisplayableRecord record : records) {
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }

    public void setRecords(ArrayList<DisplayableRecord> records) {
        this.records = records;
    }

    public void displayAllRecords(){
        for (DisplayableRecord record : records) {
            record.getDisplayText();
        }
    }
}
