import controller.Text2SpeechEditorController;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class CreateLoadSaveTest {
    private Text2SpeechEditorController test_controller = new Text2SpeechEditorController();

    @Test
    public void newDocumentTest() {
        DateTimeFormatter creationDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate today = LocalDate.now();
        String expectedOutput = creationDate.format(today);
        String actualOutput = this.test_controller.activate("CreateNewDocumentCommand", "TestTitle");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void saveDocumentTest() throws IOException {
        this.test_controller.setSaveLocation("testsPath/test.t2se");
        String actualOutput = test_controller.activate("SaveDocumentCommand", "The content of the document");
        String expectedOutput = new String(Files.readAllBytes(Paths.get("testsPath/test.t2se")));
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void loadDocumentTest() throws IOException
    {
        String actualOutput = test_controller.activate("LoadDocumentCommand", "testsPath/test.t2se");
        String expectedOutput = new String(Files.readAllBytes(Paths.get("testsPath/test.t2se")));
        assertEquals(expectedOutput,actualOutput);
    }
}