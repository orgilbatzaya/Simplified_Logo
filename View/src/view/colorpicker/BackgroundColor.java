package view.colorpicker;

import javafx.scene.paint.Color;
import view.TurtleDisplay;
import view.colorpicker.SLogoColor;

/**
 * This class picks the current background of the display.
 * @author Austin Kao
 */
public class BackgroundColor extends SLogoColor {
    private TurtleDisplay myDisplay;

    public BackgroundColor(Color defaultColor, String label, TurtleDisplay display) {
        super(defaultColor, label);
        myDisplay = display;
    }

    @Override
    public void processChoice(Color c) {
        myDisplay.setBgColor(c);
    }

}
