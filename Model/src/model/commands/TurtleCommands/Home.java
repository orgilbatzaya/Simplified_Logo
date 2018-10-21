package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Home extends Command {
    public Home(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        turtle.setXTurtle(turtle.getDefaultX());
        turtle.setYTurtle(turtle.getDefaultY());
        return turtle.getMyDistanceTraveled();
    }
}
