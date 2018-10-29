package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.BackMain;
import view.environmentdisplays.FunctionDisplay;
import view.environmentdisplays.PastCommandDisplay;
import view.environmentdisplays.VariableDisplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the console of SLogo.
 * It has a command line, a button to submit the command, and three currentEnvironmentDisplays that show past commands, current variables, and current user-defined functions
 * @author Austin Kao
 */

public class Console implements FrontExternal, ViewResourceBundles {
    private static final double PAST_COMMAND_DISPLAY_HEIGHT = 200;
    private static final double DISPLAY_HEIGHT = 100;
    private static final double COMMAND_WINDOW_WIDTH = 200;
    private static final double COMMAND_WINDOW_HEIGHT = 100;
    private static final String COMMAND_LINE_LABEL = "commandLine";
    private static final String SUBMIT_LABEL = "submit";
    private static final String PAST_COMMAND_LABEL = "past";
    private static final String VARIABLE_LABEL = "variable";
    private static final String FUNCTION_LABEL = "function";
    private static final String COMMAND_WINDOW_TITLE = "windowTitle";
    private static final String COMMAND_WINDOW_LABEL = "windowLabel";
    private static final String RUN_LABEL = "run";
    private static final String CANCEL_LABEL = "cancel";
    private static final String SPACING = "defaultMediumSpacing";

    private CommandLine myCommandLine;
    private String currentCommand;
    private HBox consoleBox;
    private PastCommandDisplay pastCommands;
    private VariableDisplay currentVariables;
    private FunctionDisplay currentFunctions;
    private GUISetup parentGUI;
    private HashMap<String,String> myVariables;
    private HashMap<String, List<String>> myFunctionsCommands;
    private HashMap<String, List<String>> myFunctionsParams;

    public Console(double x, double y, GUISetup gui) {
        parentGUI = gui;
        Text consoleTitle = new Text(myDefaults.getString(COMMAND_LINE_LABEL));
        myCommandLine = new CommandLine();
        Button submitButton = new Button(myDefaults.getString(SUBMIT_LABEL));
        HBox commandBox = new HBox(myCommandLine.getDisplay(), submitButton);
        commandBox.setSpacing(Double.parseDouble(myDefaults.getString(SPACING)));
        pastCommands = new PastCommandDisplay(PAST_COMMAND_DISPLAY_HEIGHT, myDefaults.getString(PAST_COMMAND_LABEL));
        pastCommands.getPastCommandList().setOnMouseClicked(e -> createRunInterface(pastCommands.getPastCommandList().getSelectionModel().getSelectedItem()));
        currentVariables = new VariableDisplay(DISPLAY_HEIGHT, myDefaults.getString(VARIABLE_LABEL));
        currentFunctions = new FunctionDisplay(DISPLAY_HEIGHT, myDefaults.getString(FUNCTION_LABEL));
        VBox rightColumn = new VBox(currentVariables.getDisplay(), currentFunctions.getDisplay());
        rightColumn.setSpacing(Double.parseDouble(myDefaults.getString(SPACING)));
        submitButton.setOnAction(event -> processCommand());
        VBox leftColumn = new VBox(consoleTitle, commandBox, pastCommands.getDisplay());
        consoleBox = new HBox(leftColumn, rightColumn);
        consoleBox.setSpacing(Double.parseDouble(myDefaults.getString(SPACING)));
        consoleBox.setLayoutX(x);
        consoleBox.setLayoutY(y);
        myVariables = new HashMap<>();
        myFunctionsCommands = new HashMap<>();
        myFunctionsParams = new HashMap<>();
    }

    public HBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand() {
        currentCommand = myCommandLine.getCommand();
        pastCommands.addItem(currentCommand);
        runCommand(currentCommand);
    }

    public void createRunInterface(String value) {
        Stage commandEdit = new Stage();
        commandEdit.setTitle(myDefaults.getString(COMMAND_WINDOW_TITLE));
        Label editLabel = new Label(myDefaults.getString(COMMAND_WINDOW_LABEL));
        TextField editor = new TextField();
        editor.setText(value);
        Button run = new Button(myDefaults.getString(RUN_LABEL));
        Button cancel = new Button(myDefaults.getString(CANCEL_LABEL));
        run.setOnAction(e -> runCommand(editor.getText()));
        cancel.setOnAction(e -> commandEdit.close());
        HBox buttonBox = new HBox(run, cancel);
        buttonBox.setSpacing(Double.parseDouble(myDefaults.getString(SPACING)));
        VBox parentBox = new VBox(editLabel, editor, buttonBox);
        parentBox.setSpacing(Double.parseDouble(myDefaults.getString(SPACING)));
        var root = new Group();
        root.getChildren().addAll(parentBox);
        commandEdit.setScene(new Scene(root, COMMAND_WINDOW_WIDTH, COMMAND_WINDOW_HEIGHT));
        commandEdit.show();
    }

    public void runCommand(String command) {
        Map<String, Double> commandParams = parentGUI.getTurtleParams();
        HashMap<String, String> vars = myVariables;
        BackMain back = new BackMain(parentGUI.getLanguage(), commandParams,vars, myFunctionsCommands, myFunctionsParams);
        back.performCommands(command);
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
        actRun.performActions(actionList, actionArgs, parentGUI.getCurrentDisplay());
        parentGUI.getTurtleInfoDisplay().update(parentGUI.getCurrentDisplay());
        myVariables = back.getVariables();
        myFunctionsCommands = back.getFunctions();
        myFunctionsParams = back.getFunctionsParms();
        currentVariables.update(myVariables);
        //currentFunctions.update(myFunctionsCommands);
    }
}
