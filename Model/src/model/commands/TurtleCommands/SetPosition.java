package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class SetPosition extends Command {
    public SetPosition(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double xPos = getArgsDouble(0);
        double yPos = getArgsDouble(1);
        turtle.setXTurtle(xPos);
        turtle.setYTurtle(yPos);
        return 0;
    }
}
