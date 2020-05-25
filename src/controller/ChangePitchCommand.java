package controller;

public class ChangePitchCommand implements ICommand {
    @Override
    public String execute(String pitch) {
        myDocument.changePitch(pitch);
        return null;
    }
}
