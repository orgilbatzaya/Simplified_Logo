package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Visible extends Action{
    private static double HIDE_TURTLE_HEIGHT = .01;
    private static double HIDE_TURTLE_WIDTH = .01;
    private static final int FIRST_INDEX = 0;
    private static final int ZERO = 0;
    private static final int ONE = 0;

    public Visible(List<Double> args){
        super(args);
    }

    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay){
        if(getArgsDouble(FIRST_INDEX) == ZERO){
            turtle.getView().setFitHeight(HIDE_TURTLE_HEIGHT);
            turtle.getView().setFitWidth(HIDE_TURTLE_WIDTH);
        }
        else if(getArgsDouble(FIRST_INDEX) == ONE){
            double h = turtle.getDefaultTurtleHeight();
            double w = turtle.getDefaultTurtleWidth();
            turtle.getView().setFitHeight(h);
            turtle.getView().setFitWidth(w);
        }

    }
}
