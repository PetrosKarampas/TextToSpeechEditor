package model;

public class Rot13Encoding extends Encoder
{
    public char mapChar(char character)
    {
        if  (Character.isUpperCase(character))
        {
            return (char)(((int)character+13-65)%26+65);
        }
        else if (Character.isLowerCase(character))
        {
            return (char)(((int)character+13-97)%26+97);
        }
        else
        {
            return character;
        }
    }

}
