package model;

public class FakeVoice
{
    public void fakeRateChanger(Float rate) {
        System.out.println("The rate was changed successfully. Rate: " + rate);
    }

    public void fakePitchChanger(Float pitch) {
        System.out.println("The pitch was changed successfully. Pitch: " + pitch);
    }

    public void fakeVolumeChanger(Float volume) {
        System.out.println("The volume was changed successfully. Volume: " + volume);
    }

    public void fakePlay(String content) {
        System.out.println("The content was played successfully");
    }
}
