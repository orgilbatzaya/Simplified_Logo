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
            turtleDisplay.getMyTurtle().getView().setFitHeight(HIDE_TURTLE_HEIGHT);
            turtleDisplay.getMyTurtle().getView().setFitWidth(HIDE_TURTLE_WIDTH);
        }
        else if(getArgsDouble(FIRST_INDEX) == ONE){
            TurtleView turt = turtleDisplay.getMyTurtle();
            double h = turt.getDefaultTurtleHeight();
            double w = turt.getDefaultTurtleWidth();
            turtleDisplay.getMyTurtle().getView().setFitHeight(h);
            turtleDisplay.getMyTurtle().getView().setFitWidth(w);
        }

    }
}
