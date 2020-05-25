package controller;

public class ReverseLineToSpeechCommand  implements ICommand {
    @Override
    public String execute(String content)
    {
        return myDocument.playReverseLine(content);
    }
}
