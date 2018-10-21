package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Or extends Command {
    public Or(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return ((getArgsDouble(0) != 0) || (getArgsDouble(1)!=0)) ? 1:0;
    }
}
