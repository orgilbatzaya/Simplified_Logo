package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Palette extends Action {
    private static final int FIRST_INDEX = 0;

    public Palette(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {

        //first 3 values index, next 3 r, next 3 g, next 3 b
        double rawValue = getArgsDouble(FIRST_INDEX);
        double index = rawValue%1000;
        double r = (rawValue-index)%1000000/1000;
        double g = ((rawValue-index-r)%1000000000)/1000000;
        double b = (rawValue-index-r-g)/1000000000;
        turtleDisplay.makeNewColor((int) index,(int) r,(int) g,(int) b);
    }
}

