package model;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Factory {
    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "ShowTurtle"));
    private final Set<String> TURTLE_QUERIES = new HashSet<>(Arrays.asList("Heading","IsPenDown","IsShowing","XCoordinate","YCoordinate"));
    public Command makeCommand(String commandName, List<String> args){
        try{
            String commandType = getCommandType(commandName);
            String name = "model.commands."+commandType+"."+commandName;
            Class cls = Class.forName(name);
            var constructor = cls.getConstructor(List.class);
            Object newCommand = constructor.newInstance(args);
            return (Command) newCommand;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String getCommandType(String commandName){
        if(BOOLEAN_OPS.contains(commandName)){
            return "BooleanOps";
        }
        if(MATH_OPS.contains(commandName)){
            return "MathOps";
        }
        if(TURTLE_COMMANDS.contains(commandName)){
            return "TurtleCommands";
        }
        if(CONTROL_OPS.contains(commandName)){
            return "OtherCommands";
        }
        if(TURTLE_QUERIES.contains(commandName)){
            return "TurtleQueries";
        }
        return null;

    }
}
