package Model;

import java.util.List;

public abstract class Command {
    private String myCommand;
    private List<String> myArgs;
    public Command(List<String> args) {
        myArgs = args;

    }
    public Double getArgsDouble(int index){
        return Double.parseDouble(myArgs.get(index));
    }

    public abstract double execute();

}
