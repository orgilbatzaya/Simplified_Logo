package view;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class CommandLine {
    private TextArea userInput;
    private double commandLineHeight;
    private String myCommand;
    VBox myBox;

    public CommandLine() {
        userInput = createUserCommandLine();
        myBox = new VBox(userInput);
    }

    public TextArea createUserCommandLine() {
        TextArea input = new TextArea();
        input.setPrefWidth(300);
        input.setPrefHeight(20);
        commandLineHeight = input.getPrefHeight();
        input.setOnKeyPressed(e -> increaseHeight(e.getCode()));
        return input;
    }

    public void increaseHeight(KeyCode code) {
        if(code == KeyCode.ENTER) {
            userInput.setPrefHeight(userInput.getPrefHeight() + commandLineHeight);
        }
    }

    public String getCommand() {
        myCommand = userInput.getText();
        reset();
        return myCommand;
    }

    private void reset() {
        userInput.clear();
        userInput.setPrefHeight(commandLineHeight);
    }

    public VBox getDisplay() {
        return myBox;
    }
}
