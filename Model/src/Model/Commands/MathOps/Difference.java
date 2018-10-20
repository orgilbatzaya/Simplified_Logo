package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Difference extends Command {
    public Difference(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double firstVal = getArgsDouble(0);
        double secondVal = getArgsDouble(1);
        return firstVal-secondVal;
    }
}