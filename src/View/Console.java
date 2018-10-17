package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Console implements FrontExternal {
    private TextField userInput;
    private String currentCommand;
    private VBox consoleBox;
    private String currentLang;
    private ListView<String> pastCommandList;
    private ObservableList<String> pastCommands;

    public Console() {
        userInput = createUserCommandLine();
        Text consoleTitle = new Text("Enter your command here:");
        Button submitButton = new Button("Go!");
        pastCommandList = new ListView<>();
        pastCommands = FXCollections.observableArrayList();
        pastCommandList.setMaxHeight(200);
        submitButton.setOnAction(event -> processCommand());
        consoleBox = new VBox(consoleTitle, userInput, submitButton, pastCommandList);
    }

    public TextField createUserCommandLine() {
        TextField input = new TextField();
        return input;
    }

    public VBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand () {
        currentCommand = userInput.getText();
        pastCommands.add(0,currentCommand);
        pastCommandList.setItems(pastCommands);
        userInput.clear();
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
