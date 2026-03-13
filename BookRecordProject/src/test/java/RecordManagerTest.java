import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RecordManagerTest {

    private RecordManager recordManager;

    @BeforeEach
    public void setUp() {
        recordManager = new RecordManager();
    }

    @Test
    public void initiallyEmpty() {
        assertNotNull(recordManager.getAllRecords());
        assertEquals(0, recordManager.getAllRecords().size());
    }

    @Test
    public void addAndFindRecord() {
        BookRecord br = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(br);
        assertEquals(1, recordManager.getAllRecords().size());
        assertSame(br, recordManager.findRecordById(br.getId()));
    }

    @Test
    public void addingDuplicateThrows() {
        BookRecord br = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(br);
        assertThrows(IllegalArgumentException.class, () -> recordManager.addRecord(br));
    }

    @Test
    public void removeRecordById() {
        BookRecord br = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(br);
        boolean removed = recordManager.removeRecord(br.getId());
        assertTrue(removed);
        assertNull(recordManager.findRecordById(br.getId()));
    }

    @Test
    public void setRecordsReplacesList() {
        ArrayList<DisplayableRecord> list = new ArrayList<>();
        list.add(new BookRecord("A", "B", 2000));
        recordManager.setRecords(list);
        assertEquals(1, recordManager.getAllRecords().size());
    }
}

