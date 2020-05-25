package controller;

import model.Document;

public interface ICommand
{
    Document myDocument = new Document();
    String execute(String task);
}
