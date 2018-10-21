package model.commands.TurtleQueries;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class IsPenDown extends Command {
    public IsPenDown(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        return turtle.getPenDown()? 1:0;
    }
}