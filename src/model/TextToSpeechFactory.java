package model;

public class TextToSpeechFactory
{
    public static TextToSpeech createVoice(String voiceType) {
        switch (voiceType) {
            case "RealVoice":
                return new Voice();
            case "FakeVoice":
                FakeVoice myFakeVoice = new FakeVoice();
                return new TextToSpeechAdapter(myFakeVoice);
            default:
                throw new IllegalArgumentException();
        }
    }
}
