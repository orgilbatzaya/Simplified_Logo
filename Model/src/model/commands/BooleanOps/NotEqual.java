package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class NotEqual extends Command {
    public NotEqual(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return !(getArgsDouble(0).equals(getArgsDouble(1))) ? 1:0;
    }
}
