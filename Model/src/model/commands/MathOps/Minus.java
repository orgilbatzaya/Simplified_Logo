package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Minus extends Command {
    public Minus(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        double firstVal = getArgsDouble(0);
        return firstVal*(-1);
    }
}