import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MainTest {

    @Test
    public void menuIsDisplayedAndExitChoiceTerminates() throws Exception {
        String input = "5\n"; // choose Exit
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));

            // Call main and ensure it does not throw
            assertDoesNotThrow(() -> Main.main(new String[]{}));

            String output = out.toString(StandardCharsets.UTF_8.name());
            // The menu should list the options 1..5 as per the specification
            assertTrue(output.contains("1. Add Book"), "Menu should contain '1. Add Book'");
            assertTrue(output.contains("5. Exit"), "Menu should contain '5. Exit'");
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void invalidChoicePromptsAgain() throws Exception {
        // Provide an invalid choice then exit
        String input = "invalid\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));

            assertDoesNotThrow(() -> Main.main(new String[]{}));

            String output = out.toString(StandardCharsets.UTF_8.name());
            // After an invalid choice, the menu should be displayed again (so at least two occurrences)
            int occurrences = (output.length() - output.replace("1. Add Book", "").length()) / "1. Add Book".length();
            assertTrue(occurrences >= 2, "Menu should be displayed again after invalid input");
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}




