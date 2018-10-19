package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Power extends Command {
    public Power(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        double base = getArgsDouble(0);
        double exp = getArgsDouble(1);
        return Math.pow(base,exp);
    }
}

