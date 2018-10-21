package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Forward extends Command {
    public Forward(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double valueForward = getArgsDouble(0);
        turtle.move(valueForward,0);
        return 0;
    }
}
