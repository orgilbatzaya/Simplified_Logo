package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class ClearScreen extends Command {
    public ClearScreen(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        turtle.setXTurtle(turtle.getDefaultX());
        turtle.setYTurtle(turtle.getDefaultY());
        //to do erase pen lines

        return 0;
    }
}
