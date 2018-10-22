package view;

import javafx.scene.paint.Color;

public class PenColor extends SLogoColor{
    private TurtleDisplay myDisplay;

    public PenColor(Color defaultColor, String label, TurtleDisplay display) {
        super(defaultColor, label);
        myDisplay = display;
    }

    @Override
    public void processChoice(Color c) {
        myDisplay.setPenColor(c);
    }
}
