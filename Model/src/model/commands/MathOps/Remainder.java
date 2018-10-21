package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Remainder extends Command {
    public Remainder(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        double firstVal = getArgsDouble(0);
        double secondVal = getArgsDouble(1);
        return firstVal%secondVal;
    }
}
