package controller;

public class LoadDocumentCommand implements ICommand
{
    public String execute(String path)
    {
        String documentContent = null;
        try
        {
            documentContent = myDocument.loadDocument(path);
        }catch (Exception e) {e.printStackTrace();}
        return documentContent;
    }
}
