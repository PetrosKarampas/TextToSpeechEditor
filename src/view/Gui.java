package view;

import controller.Text2SpeechEditorController;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Gui extends Application
{
    @FXML private TextField pitchValue;
    @FXML private TextField volumeValue;
    @FXML private Slider pitch;
    @FXML private Slider volume;
    @FXML private TextField rate;
    @FXML private TextArea myText;
    @FXML private MenuBar myMenuBar;
    @FXML private Text my_title;
    @FXML private Text my_author;
    @FXML private Text createdDate;
    @FXML private Text lastSaveDate;
    @FXML private Text editedContent;

    private Image main_image = new Image("/view/t2se.jpg");
    private Text2SpeechEditorController controller = new Text2SpeechEditorController();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(this.main_image);
        primaryStage.setTitle("Text2SpeechEditor");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void newDocument()
    {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Create New Document");
        dialog.setHeaderText("WARNING:\nYou are about to create a new document. \nAny unsaved progress will be lost! \n\nPlease enter the title and the the author of your document:");
        dialog.setGraphic(new ImageView(this.getClass().getResource("newdoc.jpg").toString()));

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("t2se.jpg").toString()));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField title = new TextField();
        TextField author = new TextField();

        gridPane.add(new Label("Title :"), 0, 0);
        gridPane.add(title, 1, 0);
        gridPane.add(new Label("Author :"), 0, 1);
        gridPane.add(author, 1, 1);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(gridPane);

        Node okButtonActivator = dialog.getDialogPane().lookupButton(okButton);
        okButtonActivator.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) -> {okButtonActivator.setDisable(newValue.trim().isEmpty() || author.getText().isEmpty());});
        author.textProperty().addListener((observable, oldValue, newValue) -> {okButtonActivator.setDisable(newValue.trim().isEmpty() || title.getText().isEmpty());});

        dialog.setResultConverter(dialogButton ->
        {
            if (dialogButton == okButton)
            {
                return new Pair<>(title.getText(), author.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(titleAuthor ->
        {
            this.controller.setAuthor(author.getText());
            this.createdDate.setText(this.controller.activate("CreateNewDocumentCommand",title.getText()));
            this.my_title.setText(title.getText());
            this.my_author.setText(author.getText());
            this.myText.setText("");
        });
    }

    @FXML
    public void save_as()
    {
        String pathSave;
        String myContent = myText.getText();
        Window stage = myMenuBar.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File as");
        fc.setInitialFileName("New Document");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("T2SE Files","*.t2se"));
        File file = fc.showSaveDialog(stage);
        if (file!=null) {
            fc.setInitialDirectory(file.getParentFile());
            pathSave=file.getPath();
            System.out.println("Save Path: "+pathSave);
            this.controller.setSaveLocation(pathSave);
            this.controller.activate("SaveDocumentCommand",myContent);
        }
        else {System.out.println("Invalid save file");}
    }

    @FXML
    public void openFile()
    {
        String pathOpen;
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("T2SE Files","*.t2se"));
        File selected = fc.showOpenDialog(null);
        if (selected!=null) {
            pathOpen=selected.getAbsolutePath();
            System.out.println("This is the path:"+pathOpen);
            String content = (controller.activate("LoadDocumentCommand",pathOpen));
            List<String> lines = Arrays.asList(content.split("\n"));
            int numberOfLines = lines.size();
            this.my_title.setText(lines.get(numberOfLines-4));
            this.my_author.setText(lines.get(numberOfLines-3));
            this.createdDate.setText(lines.get(numberOfLines-2));
            this.lastSaveDate.setText(lines.get(numberOfLines-1));
            StringBuilder myNewContent = new StringBuilder();
            for(int i=0; i<numberOfLines-4; i++)
            {
                myNewContent.append(lines.get(i)).append("\n");
            }
            this.myText.setText(myNewContent.toString());
        }
        else {System.out.println("Invalid File");}
    }

    @FXML
    public void textToSpeech()
    {
        System.out.println("TextToSpeech");
        String myContent = myText.getText();
        this.editedContent.setText(this.controller.activate("TextToSpeechCommand",myContent));
    }

    @FXML
    public void textToReverseSpeech()
    {
        System.out.println("TextToReverseSpeech");
        String myContent = myText.getText();
        this.editedContent.setText(this.controller.activate("ReverseTextToSpeechCommand",myContent));
    }

    @FXML
    public void lineToSpeech()
    {
        System.out.println("LineToSpeech");
        lineMethod("LineToSpeechCommand");
    }

    @FXML
    public void lineToReverseSpeech()
    {
        System.out.println("LineToReverseSpeech");
        lineMethod("ReverseLineToSpeechCommand");
    }

    @FXML
    public void encodeAtBash()
    {
        System.out.println("TextToAtBashEncode");
        this.controller.setEncodingStrategy("AtBash");
        this.editedContent.setText(this.controller.activate("EncodeDocumentCommand", this.myText.getText()));
    }

    @FXML
    public void encodeRot13()
    {
        System.out.println("TextToRot13Encode");
        this.controller.setEncodingStrategy("Rot13");
        this.editedContent.setText(this.controller.activate("EncodeDocumentCommand", this.myText.getText()));
    }

    @FXML
    public void encodeLineAtBash()
    {
        System.out.println("LineToAtBashEncode");
        this.controller.setEncodingStrategy("AtBash");
        lineMethod("EncodeLineCommand");
    }

    @FXML
    public void encodeLineRot13()
    {
        System.out.println("LineToRot13Encode");
        this.controller.setEncodingStrategy("Rot13");
        lineMethod("EncodeLineCommand");
    }

    @FXML
    public void setRate()
    {
        try
        {
            float f = Float.parseFloat(rate.getText());
            if (f>60)
            {
                System.out.println("Words per minute: " + rate.getText());
                this.controller.activate("ChangeRateCommand",rate.getText());

            }
        }
        catch(NumberFormatException b)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
            stage2.getIcons().add(new Image(this.getClass().getResource("t2se.jpg").toString()));
            alert.setGraphic(new ImageView(this.getClass().getResource("Error.jpg").toString()));
            alert.setTitle("Wrong input");
            alert.setHeaderText("Please enter a valid number");
            alert.showAndWait();

        }
    }

    @FXML
    public void setPitch()
    {
        pitchValue.textProperty().bind(Bindings.format("%.2f", pitch.valueProperty()));
        this.controller.activate("ChangePitchCommand",String.valueOf(this.pitch.getValue()));
    }

    @FXML
    public void setVolume()
    {
        volumeValue.textProperty().bind(Bindings.format("%.2f", volume.valueProperty()));
        this.controller.activate("ChangeVolumeCommand", String.valueOf(this.volume.getValue()));
    }

    @FXML
    public void reset()
    {
        System.out.println(pitch.getValue());
        this.pitch.setValue(1.0);
        this.volume.setValue(1.0);
        this.rate.clear();
        pitchValue.textProperty().bind(Bindings.format("%.2f", pitch.valueProperty()));
        volumeValue.textProperty().bind(Bindings.format("%.2f", volume.valueProperty()));
        this.controller.activate("ChangePitchCommand",String.valueOf(this.pitch.getValue()));
        this.controller.activate("ChangeVolumeCommand", String.valueOf(this.volume.getValue()));
        this.controller.activate("ChangeRateCommand", "150");
    }

    @FXML
    public void clearContent() {this.myText.setText("");}

    public void lineMethod(String commandName)
    {
        String myContent = myText.getText();
        TextInputDialog dialog = new TextInputDialog("1");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("t2se.jpg").toString()));
        dialog.setTitle("Select Line");
        dialog.setHeaderText("Select a line. \nWARNING: You can only enter an integer number!");
        dialog.setContentText("Please enter the line number:");

        Optional<String> result = dialog.showAndWait();
        List<String> lines = Arrays.asList(myText.getText().split("\n"));
        int numberOfLines = lines.size();
        try
        {
            int lineNumber = Integer.parseInt(dialog.getResult());
            if (result.isPresent() && lineNumber<=numberOfLines && lineNumber>0)
            {
                System.out.println("You Choose Line: " + result.get());
                this.controller.setNumberOfLine(result.get());
                this.editedContent.setText(this.controller.activate(commandName,myContent));
            }
        }
        catch (NumberFormatException nfe)
        {
            if (result.isPresent())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
                stage2.getIcons().add(new Image(this.getClass().getResource("t2se.jpg").toString()));
                alert.setGraphic(new ImageView(this.getClass().getResource("Error.jpg").toString()));
                alert.setTitle("Wrong Input");
                alert.setHeaderText("Please try again by giving an integer number!");
                alert.showAndWait();
            }
        }
    }


    public static void main(String[] args)
    {
        launch(args);
    }

}