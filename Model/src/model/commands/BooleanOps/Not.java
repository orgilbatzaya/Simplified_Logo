package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Not extends Command {
    public Not(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return (!getArgsDouble(0).equals(1)) ? 1:0;
    }
}
