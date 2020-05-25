package model;

public interface TextToSpeech
{
    public void rateChanger(Float rate);
    public void pitchChanger(Float pitch);
    public void volumeChanger(Float volume);
    public void play(String content);
}
