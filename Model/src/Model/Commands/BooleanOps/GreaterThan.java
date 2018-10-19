package Model.Commands.BooleanOps;

import Model.Command;

import java.util.List;

public class GreaterThan extends Command {
    public GreaterThan(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        return (getArgsDouble(0).compareTo(getArgsDouble(1)))>0 ? 1:0;
    }
}
