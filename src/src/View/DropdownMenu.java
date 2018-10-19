package View;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public abstract class DropdownMenu {
    private ChoiceBox myChoiceBox;

    public DropdownMenu(ArrayList<String> items) {
        myChoiceBox = new ChoiceBox<>();
        myChoiceBox.setItems(FXCollections.observableArrayList(items));
        if(items.size() > 0) {
            myChoiceBox.setValue(items.get(0));
        }
        myChoiceBox.setOnAction(e -> processChoice(myChoiceBox.getValue().toString()));
    }

    public ChoiceBox getChoiceBox() {
        return myChoiceBox;
    }

    public abstract void processChoice(String choice);
}
