package controller;

public class SaveDocumentCommand implements ICommand
{
    public String execute(String documentContent)
    {
        return myDocument.saveDocument(documentContent);
    }
}
