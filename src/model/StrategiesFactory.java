package model;

public class StrategiesFactory  {

    public static Strategy createStrategy(String strategy) {
        switch (strategy) {
            case "Rot13":
                return new Rot13Encoding();
            case "AtBash":
                return new AtBashEncoding();
            default:
                throw new IllegalArgumentException();
        }
    }
}