package view.Actions;

import javafx.scene.paint.Color;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;
import java.util.ResourceBundle;

public class PenColor extends Action {
    private static final int FIRST_INDEX = 0;


    public PenColor(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {
        Integer index = (int) getArgsDouble(FIRST_INDEX);
        turtleDisplay.changePenColor(index);
    }
}

