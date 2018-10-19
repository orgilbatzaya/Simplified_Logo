package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Sum extends Command {
    public Sum(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        double firstVal = getArgsDouble(0);
        double secondVal = getArgsDouble(1);
        return firstVal+secondVal;
    }
}
