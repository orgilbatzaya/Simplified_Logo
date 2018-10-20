package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Console implements FrontExternal {
    private TextArea userInput;
    private double commandLineHeight;
    private String currentCommand;
    private VBox consoleBox;
    private String currentLang;
    private ListView<String> pastCommandList;
    private ObservableList<String> pastCommands;

    public Console() {
        userInput = createUserCommandLine();
        Text consoleTitle = new Text("Enter your command here:");
        Button submitButton = new Button("Go!");
        HBox commandBox = new HBox(userInput, submitButton);
        commandBox.setSpacing(10);
        pastCommandList = new ListView<>();
        pastCommands = FXCollections.observableArrayList();
        pastCommandList.setMaxHeight(200);
        submitButton.setOnAction(event -> processCommand());
        consoleBox = new VBox(consoleTitle, commandBox, pastCommandList);
    }

    public TextArea createUserCommandLine() {
        TextArea input = new TextArea();
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

    public VBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand () {
        currentCommand = userInput.getText();
        pastCommands.add(0,currentCommand);
        pastCommandList.setItems(pastCommands);
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
