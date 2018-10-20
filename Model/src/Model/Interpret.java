package Model;

//Performs actions for each command

import java.util.List;

public class Interpret {

    public double interpretCommand(String commandName, List<String> args){
        Factory fac = new Factory();
        Command com = fac.makeCommand(commandName,args);
        return com.execute();
    }
}
