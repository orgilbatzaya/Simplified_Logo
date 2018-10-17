package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Console implements FrontExternal {
    private TextField userInput;
    private String currentCommand;
    private VBox consoleBox;
    private String currentLang;

    public Console() {
        userInput = createUserCommandLine();
        Text consoleTitle = new Text("Enter your command here:");
        Button submitButton = new Button("Go!");
        submitButton.setOnAction(event -> processCommand());
        consoleBox = new VBox(consoleTitle, userInput, submitButton);
    }

    public TextField createUserCommandLine() {
        TextField input = new TextField();
        return input;
    }

    public VBox getConsoleBox() {
        return consoleBox;
    }

    private void processCommand () {
        currentCommand = userInput.getText();
        System.out.println(currentCommand);
    }

    public String getNextCommand(){
        return currentCommand;
    }

    private void setLanguage(String lang){
        currentLang = lang;
    }

    public String getLanguage() {
        return "";
    }

}
