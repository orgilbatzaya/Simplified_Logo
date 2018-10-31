package view.colorpicker;

import javafx.scene.paint.Color;
import view.GUISetup;
import view.TurtleDisplay;

/**
 * This class picks the current background of the display.
 * @author Austin Kao
 */
public class BackgroundColor extends SLogoColor {
    private GUISetup parentGUI;

    public BackgroundColor(Color defaultColor, String label, GUISetup gui) {
        super(defaultColor, label);
        parentGUI = gui;
    }

    @Override
    public void processChoice(Color c) {
        parentGUI.getCurrentDisplay().setBgColor(c);
    }

}
