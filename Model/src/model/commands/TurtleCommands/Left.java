package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Left extends Command {
    public Left(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        double valueRotate = getArgsDouble(0);
        turtle.rotate(-valueRotate);
        return 0;
    }
}
