package model;

//Performs actions for each command

import view.TurtleDisplay;

import java.util.List;

public class Interpret {

    public double interpretCommand(String commandName, List<String> args, TurtleDisplay display){
        Factory fac = new Factory();
        Command com = fac.makeCommand(commandName,args);
        return com.execute(display);
    }
}
