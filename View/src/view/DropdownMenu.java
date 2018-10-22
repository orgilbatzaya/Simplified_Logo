package view;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * This class represents a dropdown menu in the GUI.
 * In the basic implementation of SLogo, there is only one class that extends this class: LanguageMenu
 * If there is any need for other dropdown menus, this class can be extended.
 * @author Austin Kao
 */
public abstract class DropdownMenu {
    private VBox myDisplayBox;
    private ChoiceBox myChoiceBox;

    public DropdownMenu(ArrayList<String> items, String label) {
        myChoiceBox = new ChoiceBox<>();
        Label dropdownLabel = new Label(label);
        myChoiceBox.setItems(FXCollections.observableArrayList(items));
        if(items.size() > 0) {
            myChoiceBox.setValue(items.get(0));
        }
        myChoiceBox.setOnAction(e -> processChoice(myChoiceBox.getValue().toString()));
        myDisplayBox = new VBox(dropdownLabel, myChoiceBox);
    }

    public ChoiceBox getChoiceBox() {
        return myChoiceBox;
    }

    public VBox getDisplay() {
        return myDisplayBox;
    }

    public abstract void processChoice(String choice);
}
