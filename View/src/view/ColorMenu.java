package view;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ColorMenu {
    private TurtleDisplay myDisplay;
    private ColorPicker myColorPicker;
    private Color myColor;

    public ColorMenu(ArrayList<String> items, TurtleDisplay display) {
        myColorPicker = new ColorPicker();
        myDisplay = display;
    }

    public void processChoice(String choice) {
        myDisplay.setBgColor(Color.valueOf(choice.toUpperCase()));
    }

    public void setCanvas(TurtleDisplay display) {
        myDisplay = display;
    }
}
