package model.commands.TurtleCommands;

import model.Command;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class ShowTurtle extends Command {
    public ShowTurtle(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        TurtleView turtle = display.getMyTurtle();
        turtle.getView().setFitWidth(turtle.getDefaultTurtleWidth());
        turtle.getView().setFitHeight(turtle.getDefaultTurtleHeight());
        turtle.setVisible(Boolean.TRUE);
        return 0;
    }
}
