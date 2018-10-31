package view.colorpicker;

import javafx.scene.paint.Color;
import view.GUISetup;
import view.TurtleDisplay;

/**
 * This class controls how users will choose the color of the pen the turtle draws with
 * @author Austin Kao
 */
public class PenColor extends SLogoColor {
    private GUISetup parentGUI;

    public PenColor(Color defaultColor, String label, GUISetup gui) {
        super(defaultColor, label);
        parentGUI = gui;
    }

    @Override
    public void processChoice(Color c) {
        parentGUI.getCurrentDisplay().getPen().setPenColor(c);
    }
}
