package Model.Commands.MathOps;

import Model.Command;
import java.util.List;

public class Minus extends Command {
    public Minus(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        double firstVal = getArgsDouble(0);
        return firstVal*(-1);
    }
}