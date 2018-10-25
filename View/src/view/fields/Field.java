package view.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * A class that represents a field where users can input their own information
 * Extend this class to create the desired fields.
 * @author Austin Kao
 */
public class Field {
    private TextField myField;
    private VBox myBox;

    public Field(String label) {
        Label fieldLabel = new Label(label);
        myField = new TextField();
        myBox = new VBox(fieldLabel, myField);
    }

    public VBox getDisplay() {
        return myBox;
    }

    public TextField getField() {
        return myField;
    }
}
