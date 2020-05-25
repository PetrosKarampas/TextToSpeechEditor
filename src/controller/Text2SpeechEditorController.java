package controller;

import java.util.HashMap;

public class Text2SpeechEditorController
{
    private String author;
    private String SaveLocation = null;
    private String numberOfLine = "0";
    private String encodingSelection;
    private String voiceType = "RealVoice";
    private CommandsFactory factory = new CommandsFactory();
    private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public Text2SpeechEditorController()
    {
        commands.put("CreateNewDocumentCommand",factory.createCommand("CreateNewDocumentCommand"));
        commands.put("SaveDocumentCommand",factory.createCommand("SaveDocumentCommand"));
        commands.put("LoadDocumentCommand",factory.createCommand("LoadDocumentCommand"));
        commands.put("TextToSpeechCommand",factory.createCommand("TextToSpeechCommand"));
        commands.put("ReverseTextToSpeechCommand",factory.createCommand("ReverseTextToSpeechCommand"));
        commands.put("ChangeRateCommand", factory.createCommand("ChangeRateCommand"));
        commands.put("ChangeVolumeCommand", factory.createCommand("ChangeVolumeCommand"));
        commands.put("ChangePitchCommand", factory.createCommand("ChangePitchCommand"));
        commands.put("EncodeDocumentCommand", factory.createCommand("EncodeDocumentCommand"));
        commands.put("ReverseLineToSpeechCommand", factory.createCommand("ReverseLineToSpeechCommand"));
        commands.put("LineToSpeechCommand", factory.createCommand("LineToSpeechCommand"));
        commands.put("EncodeLineCommand", factory.createCommand("EncodeLineCommand"));
    }

    public void setAuthor(String myAuthor) {this.author = myAuthor;}
    public void setSaveLocation(String saveLocation) {this.SaveLocation = saveLocation;}
    public void setNumberOfLine(String numberOfLine) {this.numberOfLine = numberOfLine;}
    public void setEncodingStrategy(String encodingStrategy){this.encodingSelection = encodingStrategy;}
    public void setVoiceType(String myVoiceType) { this.voiceType = myVoiceType; }

    public String activate(String CommandName, String task)
    {
        if(CommandName.equals("CreateNewDocumentCommand"))
        {
            commands.get(CommandName).myDocument.setAuthor(this.author);
        }
        if(CommandName.equals("SaveDocumentCommand"))
        {
            commands.get(CommandName).myDocument.setPath(SaveLocation);
        }
        if((CommandName).equals("EncodeDocumentCommand") || (CommandName).equals("EncodeLineCommand"))
        {
            commands.get(CommandName).myDocument.setEncodingStrategy(this.encodingSelection);
        }
        commands.get(CommandName).myDocument.setLineNumber(this.numberOfLine);
        commands.get(CommandName).myDocument.setVoiceType(this.voiceType);
        return commands.get(CommandName).execute(task);
    }
}
