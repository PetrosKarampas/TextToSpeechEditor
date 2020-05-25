package controller;


public class CreateNewDocumentCommand implements ICommand
{
    public String execute(String title)
    {
        return myDocument.createNewDocument(title);
    }
}
