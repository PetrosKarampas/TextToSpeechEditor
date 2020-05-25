package controller;

public class LineToSpeechCommand implements ICommand {
    @Override
    public String execute(String content)
    {
        return myDocument.playLine(content);
    }
}
