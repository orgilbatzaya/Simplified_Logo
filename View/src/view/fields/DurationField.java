package view.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * This class represents the field where users can specify the duration of the animations running onscreen.
 * @author Austin Kao
 */
public class DurationField {
    private static final double DEFAULT_DURATION = 10;

    private TextField myField;
    private VBox myBox;

    public DurationField(String label) {
        Label fieldLabel = new Label(label);
        myField = new TextField();
        myField.setText(Double.toString(DEFAULT_DURATION));
        myBox = new VBox(fieldLabel, myField);
    }

    public double getDuration() {
        return Double.parseDouble(myField.getText());
    }

    public VBox getDisplay() {
        return myBox;
    }
}
