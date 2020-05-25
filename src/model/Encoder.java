package model;

public abstract class Encoder implements Strategy
{
    public String templateMethod(String content)
    {
        return encode(content);
    }

    public String encode(String content)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < content.length(); i++)
        {
            char character = content.charAt(i);
            result.append(mapChar(character));
        }
        return result.toString();
    }

    protected abstract char mapChar(char character);
}
