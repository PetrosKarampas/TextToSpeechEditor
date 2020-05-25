package controller;

public class ChangeVolumeCommand implements ICommand {
    @Override
    public String execute(String volume) {
        myDocument.changeVolume(volume);
        return null;
    }
}
