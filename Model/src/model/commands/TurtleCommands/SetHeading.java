package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class SetHeading extends Command {
    public SetHeading(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double newHeading = getArgsDouble(0);
        return turtle.setHeading(newHeading);

    }
}
