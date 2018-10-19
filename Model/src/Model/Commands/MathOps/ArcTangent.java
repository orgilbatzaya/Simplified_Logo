package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class ArcTangent extends Command {
    public ArcTangent(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.atan(Math.toRadians(inputVal));
    }
}
