package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Palette extends Action {
    private static final int FIRST_INDEX = 0;
    private static final int THOUSAND = 1000;
    private static final int BIG = 1000000;
    private static final int BIGGER = 1000000000;
    public Palette(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {

        //first 3 values index, next 3 r, next 3 g, next 3 b
        double rawValue = getArgsDouble(FIRST_INDEX);
        double index = rawValue%THOUSAND;
        double r = (rawValue-index)%BIG/THOUSAND;
        double g = ((rawValue-index-r)%BIGGER)/BIG;
        double b = (rawValue-index-r-g)/BIGGER;
        turtleDisplay.makeNewColor((int) index,(int) r,(int) g,(int) b);
    }
}

