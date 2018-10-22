package view.button;

import javafx.scene.control.Button;

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
