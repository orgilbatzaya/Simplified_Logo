package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Equal extends Command {
    public Equal(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return (getArgsDouble(0).equals(getArgsDouble(1))) ? 1:0;
    }
}
