package Model.Commands.MathOps;

import Model.Command;
import java.util.List;

public class Minus extends Command {
    public Minus(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double firstVal = getArgsDouble(0);
        return firstVal*(-1);
    }
}