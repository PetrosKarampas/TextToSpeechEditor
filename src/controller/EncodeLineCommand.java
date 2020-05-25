package controller;

public class EncodeLineCommand implements ICommand {
    @Override
    public String execute(String content)
    {
        return myDocument.playEncodedLine(content);
    }
}
