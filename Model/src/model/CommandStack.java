package model;

import java.util.*;

public class CommandStack {

    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));
    public static final String NUM_ARGS_PATH = "model/commands/NumArgsCommands";

    private Factory myFactory;
    private Map<String,Integer> myNumArgsMap;
    private List<String> myText;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;
    private Map<String,Double> myTurtleParameters;

    public CommandStack(List<String> text, List<String> myTurtleActions, List<Double> myTurtleActionArgs, Map<String, Double> myTurtleParameters) {
        this.myTurtleActions = myTurtleActions;
        this.myTurtleActionsArgs = myTurtleActionArgs;
        this.myTurtleParameters = myTurtleParameters;
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myFactory = new Factory();
        myText = text;
    }

    public String execute() {
        Stack<String> toDo = new Stack<>();
        Stack<String> args = new Stack<>();
        for(String temp : myText) {
            toDo.push(temp);
        }
        while(!toDo.isEmpty()) {
            String s = toDo.pop();
            if(BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s) || TURTLE_COMMANDS.contains(s)) {
                int numArgs = myNumArgsMap.get(s);
                LinkedList<String> tempArgs = new LinkedList<>();
                for(int i = 0; i < numArgs; i++) {
                    tempArgs.add(args.pop());
                }
                Command temp = myFactory.makeCommand(s, tempArgs);
                args.push("" + temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));
            }
            else {
                args.push(s);
            }
        }
        return args.pop();
    }

    private boolean isOperator(String s) {
        return (BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s) || CONTROL_OPS.contains(s) || TURTLE_COMMANDS.contains(s));
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    private Map<String,Integer> getNumArgsMap(String path){
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String,Integer>();
        for (String key : properties.keySet()) {
            String value = properties.getString(key);
            outMap.put(key, Integer.parseInt(value));
        }
        return outMap;
    }
}
