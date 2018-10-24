package view.button;

import javafx.scene.control.Button;

/**
 * This class represents one of the buttons displayed on the GUI.
 * @author Austin Kao
 */
public abstract class SLogoButton {
    private Button myButton;

    public SLogoButton(String label) {
        myButton = new Button(label);
        myButton.setOnAction(e -> processCommand());
    }

    public abstract void processCommand();

    public Button getDisplay() {
        return myButton;
    }
}
