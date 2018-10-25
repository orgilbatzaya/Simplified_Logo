package view.colorpicker;

import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

/**
 * This class represents a GUI element that allows users to select the color they want for a certain other elements, such as the background of the canvas and the color of the pen.
 * Extend this class if there is a need for a color picker.
 * @author Austin Kao
 */

public abstract class SLogoColor {
    private static final String DEFAULT_COLOR_RESOURCE = "resources/Color";

    private VBox parentBox;
    private ColorPicker myColorPicker;
    private ResourceBundle myColors;

    public SLogoColor(Color defaultColor, String label) {
        myColors = ResourceBundle.getBundle(DEFAULT_COLOR_RESOURCE);
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
        //System.out.println(myColorPicker.getValue().toString());
        if(myColors.containsKey(myColorPicker.getValue().toString())) {
            String color = myColors.getString(myColorPicker.getValue().toString());
            myColorPicker.setValue(Color.valueOf(color));
        }
        processChoice(myColorPicker.getValue());
    }
}
