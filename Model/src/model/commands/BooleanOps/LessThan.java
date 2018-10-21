package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class LessThan extends Command {
    public LessThan(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return (getArgsDouble(0).compareTo(getArgsDouble(1)))<0 ? 1:0;
    }
}
