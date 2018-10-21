package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class represents the console of SLogo.
 * It has a command line, a button to submit the command, and three currentEnvironmentDisplays that show past commands, current variables, and current user-defined functions
 * @author Austin Kao
 */

public class Console implements FrontExternal {
    private TextArea userInput;
    private double commandLineHeight;
    private String currentCommand;
    private HBox consoleBox;
    private String currentLang;
    private CurrentEnvironmentDisplay pastCommands;
    private CurrentEnvironmentDisplay currentVariables;
    private CurrentEnvironmentDisplay currentFunctions;

    public Console() {
        userInput = createUserCommandLine();
        Text consoleTitle = new Text("Enter your command here:");
        Button submitButton = new Button("Go!");
        HBox commandBox = new HBox(userInput, submitButton);
        commandBox.setSpacing(10);
        pastCommands = new CurrentEnvironmentDisplay(200);
        Text variableTitle = new Text("Current variables in environment:");
        currentVariables = new CurrentEnvironmentDisplay(100);
        Text functionTitle = new Text("Current user-defined commands in environment:");
        currentFunctions = new CurrentEnvironmentDisplay(100);
        VBox rightColumn = new VBox(variableTitle, currentVariables.getDisplay(), functionTitle, currentFunctions.getDisplay());
        rightColumn.setSpacing(10);
        submitButton.setOnAction(event -> processCommand());
        VBox leftColumn = new VBox(consoleTitle, commandBox, pastCommands.getDisplay());
        consoleBox = new HBox(leftColumn, rightColumn);
        consoleBox.setSpacing(10);
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

    public HBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand () {
        currentCommand = userInput.getText();
        pastCommands.addItem(userInput.getText());
        userInput.clear();
        userInput.setPrefHeight(commandLineHeight);
    }

    public String getNextCommand(){
        return currentCommand;
    }

    private void setLanguage(String lang){
        currentLang = lang;
    }

    public String getLanguage() {
        return currentLang;
    }

}
