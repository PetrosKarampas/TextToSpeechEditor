package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Document
{
    private TextToSpeech voice;
    private String title;
    private String author;
    private String createdDate;
    private String lastSavedDate;
    private String content;
    private String encodingStrategy;
    private String path;
    private String voiceType;
    private int lineNumber;
    private float rate = 150;
    private float volume = 1;
    private float pitch = 1;

    public Document() {}

    public String getAuthor() {return this.author;}
    public String getContent() {return this.content;}
    public void setAuthor(String author) {this.author = author;}
    public void setContent(String content) {this.content = content;}
    public void setPath(String path) {this.path = path;}
    public void setLineNumber(String lineNumber) {this.lineNumber = Integer.parseInt(lineNumber);}
    public void setEncodingStrategy(String strategy) { this.encodingStrategy = strategy; }
    public void setVoiceType(String myVoiceType) { this.voiceType = myVoiceType; }

    public void specs()
    {
        String empty = "";
        System.out.println("Document Specs -->");
        System.out.println("Document Title: " + this.title);
        System.out.println("Document Author: " + this.author);
        System.out.println("Document Creation Date: " + this.createdDate);
        if (this.lastSavedDate.equals(empty)) {System.out.println("Document Last Saved Date: Not Saved Yet.");}
        else{System.out.println("Document Last Saved Date: " + this.lastSavedDate);}
    }
    public void voiceCreator()
    {
        this.voice = TextToSpeechFactory.createVoice(this.voiceType);
        this.voice.rateChanger(this.rate);
        this.voice.pitchChanger(this.pitch);
        this.voice.volumeChanger(this.volume);
    }

    public String createNewDocument(String documentTitle)
    {
        DateTimeFormatter creationDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        this.title = documentTitle;
        this.createdDate = creationDate.format(now);
        this.lastSavedDate = "";
        this.content = "";
        specs();
        return this.createdDate;
    }

    public void documentRefresh(String content)
    {
        List<String> lines = Arrays.asList(content.split("\n"));
        int numberOfLines = lines.size();
        this.title = lines.get(numberOfLines-4);
        this.author = lines.get(numberOfLines-3);
        this.createdDate = lines.get(numberOfLines-2);
        this.lastSavedDate = lines.get(numberOfLines-1);
    }

    public String saveDocument(String documentContent)
    {
        DateTimeFormatter savedDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        this.lastSavedDate = savedDate.format(now);
        this.content = documentContent + "\n" + this.title + "\n" + this.author + "\n" + this.createdDate + "\n" + this.lastSavedDate;
        try {Files.write(Paths.get(this.path), this.content.getBytes());}
        catch (IOException e) {e.printStackTrace();}
        specs();
        return this.content;
    }

    public String loadDocument(String path) throws IOException
    {
        String documentContent;
        this.path = path;
        documentContent = new String(Files.readAllBytes(Paths.get(this.path)));
        documentRefresh(documentContent);
        specs();
        return documentContent;
    }

    public String playContent(String content)
    {
        voiceCreator();
        this.voice.play(content);
        return content;
    }

    public String playReverseContent(String content)
    {
        voiceCreator();
        String reversedContent = "";
        List<String> words = Arrays.asList(content.split("\n| "));
        int numberOfWords = words.size();
        Collections.reverse(words);
        for(int i=0;i<numberOfWords-1;i++)
        {
            reversedContent = reversedContent + words.get(i) + " ";
        }
        reversedContent = reversedContent + words.get(numberOfWords-1);
        voice.play(reversedContent);
        return reversedContent;
    }

    public String playLine(String  content){
        voiceCreator();
        List<String> lines = Arrays.asList(content.split("\n"));
        String myLineContent = lines.get(lineNumber-1);
        Line myLine = new Line();
        myLine.playLine(myLineContent,this.voice);
        return myLineContent;
    }

    public String playReverseLine(String content){
        voiceCreator();
        List<String> lines = Arrays.asList(content.split("\n"));
        String myLineContent = lines.get(lineNumber-1);
        Line myLine = new Line();
        return myLine.playReverseLine(myLineContent,this.voice);
    }

    public String playEncodedContent(String content)
    {
        voiceCreator();
        Strategy myEncoder = StrategiesFactory.createStrategy(this.encodingStrategy);
        String encodedContent = myEncoder.templateMethod(content);
        this.voice.play(encodedContent);
        return  encodedContent;
    }

    public String playEncodedLine(String content)
    {
        voiceCreator();
        List<String> lines = Arrays.asList(content.split("\n"));
        String myLineContent = lines.get(lineNumber-1);
        Line myLine = new Line();
        return myLine.playEncodedLine(myLineContent,this.voice,this.encodingStrategy);
    }

    public void changeRate(String rate)
    {
        this.rate = Float.parseFloat(rate);
    }

    public void changePitch(String pitch)
    {
        this.pitch = Float.parseFloat(pitch);
    }

    public void changeVolume(String volume)
    {
        this.volume = Float.parseFloat(volume);
    }
}
