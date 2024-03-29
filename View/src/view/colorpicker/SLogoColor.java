package view.colorpicker;

import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.ViewResourceBundles;

import java.util.ResourceBundle;

/**
 * This class represents a GUI element that allows users to select the color they want for a certain other elements, such as the background of the canvas and the color of the pen.
 * Extend this class if there is a need for a color picker.
 * @author Austin Kao
 */

public abstract class SLogoColor implements ViewResourceBundles {

    private VBox parentBox;
    private ColorPicker myColorPicker;

    public SLogoColor(Color defaultColor, String label) {
        myColorPicker = new ColorPicker(defaultColor);
        myColorPicker.setOnAction(e -> update());
        Label colorLabel = new Label(label);
        parentBox = new VBox(colorLabel, myColorPicker);
    }

    public abstract void processChoice(Color c);

    public VBox getDisplay() {
        return parentBox;
    }

    public void update() {
        System.out.println(myColorPicker.getValue().toString());
        if(myColors.containsKey(myColorPicker.getValue().toString())) {
            String color = myColors.getString(myColorPicker.getValue().toString());
            myColorPicker.setValue(Color.valueOf(color));
        }
        processChoice(myColorPicker.getValue());
    }
}
