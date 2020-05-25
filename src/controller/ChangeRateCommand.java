package controller;

public class ChangeRateCommand implements ICommand
{
    @Override
    public String execute(String rate) {
        myDocument.changeRate(rate);
        return null;
    }
}
