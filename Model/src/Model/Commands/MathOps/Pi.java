package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Pi extends Command {
    public Pi(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        return Math.PI;
    }
}
