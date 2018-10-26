package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Visible extends Action{
    private static double HIDE_TURTLE_HEIGHT = .01;
    private static double HIDE_TURTLE_WIDTH = .01;
    public Visible(List<Double> args){
        super(args);
    }

    public void execute(TurtleDisplay turtleDisplay){
        if(getArgsDouble(0) == 0){
            turtleDisplay.getMyTurtle().getView().setFitHeight(HIDE_TURTLE_HEIGHT);
            turtleDisplay.getMyTurtle().getView().setFitWidth(HIDE_TURTLE_WIDTH);
        }
        else if(getArgsDouble(0) == 1){
            TurtleView turt = turtleDisplay.getMyTurtle();
            double h = turt.getDefaultTurtleHeight();
            double w = turt.getDefaultTurtleWidth();
            turtleDisplay.getMyTurtle().getView().setFitHeight(h);
            turtleDisplay.getMyTurtle().getView().setFitWidth(w);
        }

    }
}
