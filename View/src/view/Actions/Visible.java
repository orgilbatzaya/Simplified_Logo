package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Visible extends Action{

    public Visible(List<Double> args){
        super(args);
    }

    public void execute(TurtleDisplay turtleDisplay){
        if(getArgsDouble(0) == 0.0){
            turtleDisplay.getMyTurtle().getView().setFitHeight(0);
            turtleDisplay.getMyTurtle().getView().setFitWidth(0);
        }
        else if(getArgsDouble(0) == 1.0){
            TurtleView turt = turtleDisplay.getMyTurtle();
            double h = turt.getDefaultTurtleHeight();
            double w = turt.getDefaultTurtleWidth();
            turtleDisplay.getMyTurtle().getView().setFitHeight(h);
            turtleDisplay.getMyTurtle().getView().setFitWidth(w);
        }

    }
}
