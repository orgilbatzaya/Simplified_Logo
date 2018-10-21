package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Tangent extends Command {
    public Tangent(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        double inputVal = getArgsDouble(0);
        return Math.tan(Math.toRadians(inputVal));
    }
}
