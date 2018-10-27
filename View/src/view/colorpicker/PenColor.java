package view.colorpicker;

import javafx.scene.paint.Color;
import view.TurtleDisplay;

/**
 * This class controls how users will choose the color of the pen the turtle draws with
 * @author Austin Kao
 */
public class PenColor extends SLogoColor {
    private TurtleDisplay myDisplay;

    public PenColor(Color defaultColor, String label, TurtleDisplay display) {
        super(defaultColor, label);
        myDisplay = display;
    }

    @Override
    public void processChoice(Color c) {
        myDisplay.getPen().setPenColor(c);
    }
}
