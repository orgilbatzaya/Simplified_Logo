package view;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * Class to potentially replace color pickers. Will need to think about implementation more.
 */

public class SLogoColor {
    private ColorPicker myColorPicker;
    private Color myColor;

    public SLogoColor(Color defaultColor) {
        myColorPicker = new ColorPicker(defaultColor);
        myColor = myColorPicker.getValue();
        myColorPicker.setOnAction(e -> processChoice());
    }

    public void processChoice() {
        myColor = myColorPicker.getValue();
    }

    public Color getColor() {
        return myColor;
    }
}
