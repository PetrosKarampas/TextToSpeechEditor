package model;

public class AtBashEncoding extends Encoder
{
    protected char mapChar(char character) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        if (alpha.indexOf(Character.toLowerCase(character)) > -1) {
            int n = Math.subtractExact(25, alpha.indexOf(Character.toLowerCase(character)));
            if (Character.isUpperCase(character)) {
                return (Character.toUpperCase(alpha.charAt(n)));
            } else {
                return (alpha.charAt(n));
            }
        } else {
            return character;
        }
    }
}
