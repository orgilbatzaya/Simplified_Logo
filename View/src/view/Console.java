package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BackMain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class represents the console of SLogo.
 * It has a command line, a button to submit the command, and three currentEnvironmentDisplays that show past commands, current variables, and current user-defined functions
 * @author Austin Kao
 */

public class Console implements FrontExternal {

    private CommandLine myCommandLine;
    private String currentCommand;
    private HBox consoleBox;
    private ResourceBundle currentLang;
    private CurrentEnvironmentDisplay pastCommands;
    private CurrentEnvironmentDisplay currentVariables;
    private CurrentEnvironmentDisplay currentFunctions;
    private GUISetup parentGUI;
    private TurtleDisplay mainDisplay;

    public Console(GUISetup gui) {
        parentGUI = gui;
        Text consoleTitle = new Text("Enter your command here:");
        myCommandLine = new CommandLine();
        Button submitButton = new Button("Go!");
        HBox commandBox = new HBox(myCommandLine.getDisplay(), submitButton);
        commandBox.setSpacing(10);
        pastCommands = new CurrentEnvironmentDisplay(200, "Past commands:");
        currentVariables = new CurrentEnvironmentDisplay(100, "Current variables in environment:");
        currentFunctions = new CurrentEnvironmentDisplay(100, "Current user-defined commands in environment:");
        VBox rightColumn = new VBox(currentVariables.getDisplay(), currentFunctions.getDisplay());
        rightColumn.setSpacing(10);
        submitButton.setOnAction(event -> processCommand());
        VBox leftColumn = new VBox(consoleTitle, commandBox, pastCommands.getDisplay());
        consoleBox = new HBox(leftColumn, rightColumn);
        consoleBox.setSpacing(10);
    }

    public HBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand () {
        currentCommand = myCommandLine.getCommand();
        pastCommands.addItem(currentCommand);
    }

    public String getNextCommand(){
        return currentCommand;
    }

    private void setLanguage(ResourceBundle lang){
        currentLang = lang;
    }

    public ResourceBundle getLanguage() {
        return currentLang;
    }

}
