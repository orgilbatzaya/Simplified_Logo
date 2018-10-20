package model.commands.BooleanOps;

import model.Command;

import java.util.List;

public class And extends Command {
    public And(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        return ((getArgsDouble(0) != 0) && (getArgsDouble(1)!=0)) ? 1:0;
    }
}
