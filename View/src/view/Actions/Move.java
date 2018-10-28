package view.Actions;

import javafx.geometry.Point2D;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;


public class Move extends Action{
    private static final int DEFAULT_ROTATION = 90;
    private static final int FIRST_INDEX = 0;

    public Move(List<Double> args){
        super(args);
    }

    public void execute(TurtleDisplay turtleDisplay){
        TurtleView turtleView = turtleDisplay.getMyTurtle();
        Point2D next = new Point2D(getArgsDouble(FIRST_INDEX)*Math.cos(Math.toRadians(turtleView.getView().getRotate()-DEFAULT_ROTATION)),
                                getArgsDouble(FIRST_INDEX)*Math.sin(Math.toRadians(turtleView.getView().getRotate()-DEFAULT_ROTATION)));
        turtleDisplay.createNewAnimation(next);
    }
}
