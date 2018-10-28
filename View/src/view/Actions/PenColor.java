package view.Actions;

import javafx.scene.paint.Color;
import view.TurtleDisplay;

import java.util.List;
import java.util.ResourceBundle;

public class PenColor extends Action {

    public PenColor(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        Integer index = (int) getArgsDouble(0);
        turtleDisplay.changePenColor(index);
    }
}

