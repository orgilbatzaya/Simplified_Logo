package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Tangent extends Command {
    public Tangent(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.tan(Math.toRadians(inputVal));
    }
}
