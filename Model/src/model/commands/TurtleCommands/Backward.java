package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Backward extends Command {
    public Backward(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double valueForward = getArgsDouble(0);
        turtle.move(-valueForward);
        return 0;
    }
}
