package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Power extends Command {
    public Power(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        double base = getArgsDouble(0);
        double exp = getArgsDouble(1);
        return Math.pow(base,exp);
    }
}
