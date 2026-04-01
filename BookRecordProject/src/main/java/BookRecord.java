import java.util.Objects;

public class BookRecord implements DisplayableRecord {

    private String id;
    private String title;
    private String author;
    private int year;

    public  BookRecord(String title, String author, int year) {
        if(title==null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if(author==null || author.isEmpty()){
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (year<0 || year>2026){
            throw new IllegalArgumentException("Year cannot be less than 0 or 2026");
        }

        this.title = title;
        this.author = author;
        this.year = year;
        generateID();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void generateID(){
        this.id = title.substring(0, 3) + year  + author.substring(0, 3);
    }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author + "(" + year + ")";
    }

    @Override
    public void getDisplayText() {
        System.out.println(toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o== null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        BookRecord other = (BookRecord) o;

        if (!this.id.equals(other.id)){
            return false;
        }
        if (!this.title.equals(other.title)){
            return false;
        }
        if (!this.author.equals(other.author)){
            return false;
        }
        return true;
    }
}
