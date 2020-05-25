package controller;

public class EncodeDocumentCommand implements ICommand
{
    @Override
    public String execute(String content)
    {
        return myDocument.playEncodedContent(content);
    }
}
