package controller;

public class CommandsFactory
{
    public ICommand createCommand(String command)
    {
        switch (command) {
            case "CreateNewDocumentCommand":
                return new CreateNewDocumentCommand();
            case "SaveDocumentCommand":
                return new SaveDocumentCommand();
            case "LoadDocumentCommand":
                return new LoadDocumentCommand();
            case "TextToSpeechCommand":
                return new TextToSpeechCommand();
            case "ReverseTextToSpeechCommand":
                return new ReverseTextToSpeechCommand();
            case "ChangeRateCommand":
                return new ChangeRateCommand();
            case "ChangeVolumeCommand":
                return new ChangeVolumeCommand();
            case "ChangePitchCommand":
                return new ChangePitchCommand();
            case "EncodeDocumentCommand":
                return new EncodeDocumentCommand();
            case "LineToSpeechCommand":
                return new LineToSpeechCommand();
            case "ReverseLineToSpeechCommand":
                return new ReverseLineToSpeechCommand();
            case "EncodeLineCommand":
                return new EncodeLineCommand();
            default:
                throw new IllegalArgumentException();
        }
    }
}
