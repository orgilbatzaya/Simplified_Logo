package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Home extends Action {

    public Home(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        double defaultXPos = turtleDisplay.getMyTurtle().DEFAULT_STARTING_POS;
        double defaultYPos = turtleDisplay.getMyTurtle().DEFAULT_STARTING_POS;
        turtleDisplay.getMyTurtle().setXTurtle(defaultXPos);
        turtleDisplay.getMyTurtle().setYTurtle(defaultYPos);



    }
}
