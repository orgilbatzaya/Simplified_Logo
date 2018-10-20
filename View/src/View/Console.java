package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
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
        HBox commandBox = new HBox(userInput, submitButton);
        commandBox.setSpacing(10);
        pastCommandList = new ListView<>();
        pastCommands = FXCollections.observableArrayList();
        pastCommandList.setMaxHeight(200);
        submitButton.setOnAction(event -> processCommand(KeyCode.ENTER));
        consoleBox = new VBox(consoleTitle, commandBox, pastCommandList);
    }

    public TextField createUserCommandLine() {
        TextField input = new TextField();
        return input;
    }

    public VBox getConsoleBox() {
        return consoleBox;
    }

    public void processCommand (KeyCode code) {
        if(code == KeyCode.TAB) {
            userInput.setText(userInput.getText()+"\n");
        }
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
