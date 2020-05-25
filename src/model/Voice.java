package model;

import com.sun.speech.freetts.VoiceManager;

public class Voice implements TextToSpeech
{
    private com.sun.speech.freetts.Voice voice;



    public  Voice()
    {
        this.voice = VoiceManager.getInstance().getVoice("kevin16");
        this.voice.allocate();
    }

    @Override
    public void rateChanger(Float rate) {this.voice.setRate(rate);}

    @Override
    public void pitchChanger(Float pitch) {this.voice.setPitchShift(pitch);}

    @Override
    public void volumeChanger(Float volume) {this.voice.setVolume(volume);}

    @Override
    public void play(String content) {this.voice.speak(content);}
}
