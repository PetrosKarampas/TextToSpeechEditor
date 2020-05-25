import controller.Text2SpeechEditorController;
import org.junit.Test;


import static org.junit.Assert.*;

public class SpeakerTest {
    Text2SpeechEditorController testController = new Text2SpeechEditorController();


    @Test
    public void textToSpeechTest()
    {
        testController.setVoiceType("FakeVoice");
        String actualOutput = testController.activate("TextToSpeechCommand", "TestContent");
        String expectedOutput = "TestContent";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void lineToSpeechTest()
    {
        testController.setVoiceType("FakeVoice");
        testController.setNumberOfLine("1");
        String actualOutput = testController.activate("LineToSpeechCommand", "TestLine\nkudos");
        String expectedOutput = "TestLine";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void reverseContentToSpeechTest()
    {
        testController.setVoiceType("FakeVoice");
        String actualOutput = testController.activate("ReverseTextToSpeechCommand","This is the reverse\ntext test");
        String expectedOutput = "test text reverse the is This";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void reverseLineToSpeechTest()
    {
        testController.setVoiceType("FakeVoice");
        testController.setNumberOfLine("2");
        String actualOutput = testController.activate("ReverseLineToSpeechCommand", "This is the reverse\nSecond Line");
        String expectedOutput = "Line Second";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void tuneAudioTest()
    {
        testController.setVoiceType("FakeVoice");
        testController.activate("ChangeRateCommand","300");
        testController.activate("ChangePitchCommand","4");
        testController.activate("ChangeVolumeCommand","0.70");
        String actualOutput = testController.activate("TextToSpeechCommand", "TestContent");
        String expectedOutput = "TestContent";
        assertEquals(expectedOutput,actualOutput);
    }
}