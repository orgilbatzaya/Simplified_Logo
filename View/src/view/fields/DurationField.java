package view.fields;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import view.GUISetup;
import view.ViewResourceBundles;

/**
 * This class represents the field where users can specify the duration of the animations running onscreen.
 * @author Austin Kao
 */
public class DurationField extends Field implements ViewResourceBundles {
    private static final String SET_BUTTON_LABEL = "set";
    private static final double DEFAULT_DURATION = 1;
    private GUISetup parentGUI;
    private Button setButton;

    public DurationField(String label, GUISetup gui) {
        super(label);
        getField().setText(Double.toString(DEFAULT_DURATION));
        parentGUI = gui;
        setButton = new Button(getDefault(SET_BUTTON_LABEL));
        setButton.setOnAction(e -> setNewDuration());
        getDisplay().getChildren().add(setButton);
    }

    public void setNewDuration() {
        double newDuration = Double.parseDouble(getField().getText());
        parentGUI.getCurrentDisplay().setDuration(newDuration);
    }
}
