package view;

import javafx.scene.paint.Color;

public class BackgroundColor extends SLogoColor{
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
