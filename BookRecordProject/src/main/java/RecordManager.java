import java.util.ArrayList;

public class RecordManager {

    private ArrayList<DisplayableRecord> records;

    public RecordManager() {
        records = new ArrayList<>();
    }

    public void addRecord(DisplayableRecord record) {
        if (this.records.contains(record)){
            throw new  IllegalArgumentException("Record already exists");
        }
        else{
            this.records.add(record);
        }
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
            System.out.println("ID: " + record.getId());
            System.out.println("Title: " + record.getTitle());
            System.out.println("Author: " + record.getAuthor());
            System.out.println("Year: " + record.getYear());
        }
    }

}
