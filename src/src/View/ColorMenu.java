package View;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class ColorMenu extends DropdownMenu{
    private TurtleDisplay myDisplay;

    public ColorMenu(ArrayList<String> items) {
        super(items);
    }

    public void processChoice(String choice) {
        System.out.println(choice);
    }

    public void setMyDisplay(TurtleDisplay display) {
        myDisplay = display;
    }
}
