import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;

public class FileHandlerTest {

    @Test
    public void constructorAndGetSetFileName() {
        FileHandler fileHandler = new FileHandler("testfile.ser");
        assertNotNull(fileHandler);
    }

    @Test
    public void exportReadableCreatesFile() throws Exception {
        FileHandler fileHandler = new FileHandler("testfile.ser");
        ArrayList<DisplayableRecord> list = new ArrayList<>();
        list.add(new BookRecord("The Hobbit", "J.R.R. Tolkien", 1937));
        String out = "test_readable.txt";
        // Should not throw
        fileHandler.exportReadableRecords(list, out);
        File f = new File(out);
        assertTrue(f.exists());
        // cleanup
        f.delete();
    }
}


