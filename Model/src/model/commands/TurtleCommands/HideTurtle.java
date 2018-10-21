package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class HideTurtle extends Command {
    public HideTurtle(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        turtle.getView().setFitWidth(0);
        turtle.getView().setFitHeight(0);
        turtle.setVisible(Boolean.FALSE);
        return 0;
    }
}
