package controller;

public class TextToSpeechCommand implements ICommand {
    @Override
    public String execute(String content)
    {
        return myDocument.playContent(content);
    }
}
