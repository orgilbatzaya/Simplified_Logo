package view.Actions;

import javafx.geometry.Point2D;
import view.TurtleDisplay;

import java.util.List;


public class Move extends Action{

    public Move(List<Double> args){
        super(args);
    }

    public void execute(TurtleDisplay turtleDisplay){
        double heading = turtleDisplay.getMyTurtle().getHeading();
        Point2D next = new Point2D(getArgsDouble(0)*Math.cos(Math.toRadians(heading)),
                                getArgsDouble(0)*Math.sin(Math.toRadians(heading)));
        System.out.println(next.getX()+" , "+next.getY());
        turtleDisplay.getMyTurtle().move(next);
        System.out.println("HERE");
    }
}
