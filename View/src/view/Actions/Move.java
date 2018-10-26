package view.Actions;

import javafx.geometry.Point2D;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;


public class Move extends Action{

    public Move(List<Double> args){
        super(args);
    }

    public void execute(TurtleDisplay turtleDisplay){
        TurtleView turtleView = turtleDisplay.getMyTurtle();
        Point2D next = new Point2D(getArgsDouble(0)*Math.cos(Math.toRadians(turtleView.getView().getRotate())),
                                getArgsDouble(0)*Math.sin(Math.toRadians(turtleView.getView().getRotate())));
        turtleDisplay.createNewAnimation(next);
        //turtleDisplay.getReturnValue();
    }
}
