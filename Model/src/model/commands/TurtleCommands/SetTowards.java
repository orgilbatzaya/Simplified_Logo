package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class SetTowards extends Command {
    public SetTowards(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double xVec = getArgsDouble(0);
        double yVec = getArgsDouble(1);
        double newHeading = Math.tan(Math.toRadians(yVec/xVec));

        return turtle.setHeading(newHeading);
    }
}
