import controller.Text2SpeechEditorController;
import model.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncodingPlayTest {
    Text2SpeechEditorController this_Controller = new Text2SpeechEditorController();

    @Test
    public void encodeAtBashTest() {
        this_Controller.setVoiceType("FakeVoice");
        this_Controller.setEncodingStrategy("AtBash");
        String actualOutput = this_Controller.activate("EncodeDocumentCommand","Encoding Test");
        String expectedOutput = "Vmxlwrmt Gvhg";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void encodeRot13Test() {
        this_Controller.setVoiceType("FakeVoice");
        this_Controller.setEncodingStrategy("Rot13");
        String actualOutput = this_Controller.activate("EncodeDocumentCommand","Encoding Test");
        String expectedOutput = "Rapbqvat Grfg";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void encodeLineAtBashTest() {
        this_Controller.setVoiceType("FakeVoice");
        this_Controller.setEncodingStrategy("AtBash");
        this_Controller.setNumberOfLine("2");
        String actualOutput = this_Controller.activate("EncodeLineCommand","This is the\nLine Encoding Test");
        String expectedOutput = "Ormv Vmxlwrmt Gvhg";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void encodeLineRot13() {
        this_Controller.setVoiceType("FakeVoice");
        this_Controller.setEncodingStrategy("Rot13");
        this_Controller.setNumberOfLine("2");
        String actualOutput = this_Controller.activate("EncodeLineCommand","This is the\nLine Encoding Test");
        String expectedOutput = "Yvar Rapbqvat Grfg";
        assertEquals(expectedOutput,actualOutput);
    }
}