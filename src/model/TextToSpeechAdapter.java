package model;

public class TextToSpeechAdapter implements TextToSpeech
{
    private FakeVoice fakeVoice;

    public TextToSpeechAdapter(FakeVoice myFakeVoice){this.fakeVoice = myFakeVoice;}

    @Override
    public void rateChanger(Float rate) {this.fakeVoice.fakeRateChanger(rate);}

    @Override
    public void pitchChanger(Float pitch) {this.fakeVoice.fakePitchChanger(pitch);}

    @Override
    public void volumeChanger(Float volume) {this.fakeVoice.fakeVolumeChanger(volume);}

    @Override
    public void play(String content) {this.fakeVoice.fakePlay(content);}
}
