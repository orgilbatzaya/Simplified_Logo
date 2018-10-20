package Model.Commands.BooleanOps;

import Model.Command;

import java.util.List;

public class Not extends Command {
    public Not(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        return (!getArgsDouble(0).equals(1)) ? 1:0;
    }
}
