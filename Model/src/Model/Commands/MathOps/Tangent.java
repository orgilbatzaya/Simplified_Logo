package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Tangent extends Command {
    public Tangent(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.tan(Math.toRadians(inputVal));
    }
}
