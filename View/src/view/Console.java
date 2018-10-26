package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BackMain;
import view.environmentdisplays.CurrentEnvironmentDisplay;

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
        Map<String, Double> commandParams = parentGUI.getTurtleParams();

        BackMain back = new BackMain(parentGUI.getLanguage(), commandParams);
        back.performCommands(currentCommand);
        List<String> actionList = back.getMyTurtleActions();
        for(int i=0; i<actionList.size(); i++){
            System.out.print(actionList.get(i)+" ");
        }
        System.out.println();
        List<Double> actionArgs = back.getMyTurtleActionsArgs();
        for(int i=0; i<actionArgs.size(); i++){
            System.out.print(actionArgs.get(i)+" ");
        }
        System.out.println();
        ActionRunner actRun = new ActionRunner();
        actRun.performActions(actionList,actionArgs, parentGUI.getCurrentDisplay());
        for (Map.Entry entry : commandParams.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
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
