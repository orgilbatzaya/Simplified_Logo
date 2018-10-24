package view.colorpicker;

import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Class to potentially replace color pickers. Will need to think about implementation more.
 */

public abstract class SLogoColor {
    private VBox parentBox;
    private ColorPicker myColorPicker;

    public SLogoColor(Color defaultColor, String label) {
        myColorPicker = new ColorPicker(defaultColor);
        myColorPicker.setOnAction(e -> processChoice(myColorPicker.getValue()));
        Label colorLabel = new Label(label);
        parentBox = new VBox(colorLabel, myColorPicker);
    }

    public abstract void processChoice(Color c);

    public VBox getDisplay() {
        return parentBox;
    }
}
