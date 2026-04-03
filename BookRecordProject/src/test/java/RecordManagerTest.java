import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        BookRecord bookRecord = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(bookRecord);
        assertEquals(1, recordManager.getAllRecords().size());
        assertSame(bookRecord, recordManager.findRecordById(bookRecord.getId()));
    }

    @Test
    public void addingDuplicateThrows() {
        BookRecord bookRecord = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(bookRecord);
        assertThrows(IllegalArgumentException.class, () -> recordManager.addRecord(bookRecord));
    }

    @Test
    public void removeRecordById() {
        BookRecord bookRecord = new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937);
        recordManager.addRecord(bookRecord);
        boolean removed = recordManager.removeRecord(bookRecord.getId());
        assertTrue(removed);
        assertNull(recordManager.findRecordById(bookRecord.getId()));
    }

    @Test
    public void setRecordsReplacesList() {
        ArrayList<DisplayableRecord> list = new ArrayList<>();
        list.add(new BookRecord("ABC", "DEF", 2000));
        recordManager.setRecords(list);
        assertEquals(1, recordManager.getAllRecords().size());
    }
}

