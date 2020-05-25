package model;

import java.util.*;

public class Line
{
    public void playLine(String line, TextToSpeech voice)
    {
        voice.play(line);
    }

    public String playReverseLine(String line, TextToSpeech voice)
    {
        String reversedLine = "";
        List<String> words = Arrays.asList(line.split(" "));
        int numberOfWords = words.size();
        Collections.reverse(words);
        for(int i=0;i<numberOfWords-1;i++)
        {
            reversedLine = reversedLine + words.get(i) + " ";
        }
        reversedLine = reversedLine + words.get(numberOfWords-1);
        voice.play(reversedLine);
        return reversedLine;
    }

    public String playEncodedLine(String line, TextToSpeech voice,String encodingStrategy)
    {
        Strategy editedLine = StrategiesFactory.createStrategy(encodingStrategy);
        String encodedLine = editedLine.templateMethod(line);
        voice.play(encodedLine);
        return encodedLine;
    }
}
