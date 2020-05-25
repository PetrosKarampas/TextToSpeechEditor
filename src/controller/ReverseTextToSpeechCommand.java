package controller;

public class ReverseTextToSpeechCommand implements ICommand
{
    @Override
    public String execute(String content)
    {
        return myDocument.playReverseContent(content);
    }
}
