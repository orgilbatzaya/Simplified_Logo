package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class ArcTangent extends Command {
    public ArcTangent(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        double inputVal = getArgsDouble(0);
        return Math.atan(Math.toRadians(inputVal));
    }
}
