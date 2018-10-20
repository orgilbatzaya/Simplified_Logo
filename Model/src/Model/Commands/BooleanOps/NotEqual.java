package Model.Commands.BooleanOps;

import Model.Command;

import java.util.List;

public class NotEqual extends Command {
    public NotEqual(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        return !(getArgsDouble(0).equals(getArgsDouble(1))) ? 1:0;
    }
}
