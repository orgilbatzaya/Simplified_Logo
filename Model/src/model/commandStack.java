package model;

import model.commands.MathOps.Argument;
import model.commands.MathOps.Variable;

import java.util.*;

public class commandStack {

    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));
    public static final String NUM_ARGS_PATH = "model/commands/NumArgsCommands";

    private List<String> out;
    private Factory myFactory;
    private Map<String,Integer> myNumArgsMap;
    private Stack<String> args;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;
    private Map<String,Double> myTurtleParameters;

    public commandStack(List<String> text, List<String> myTurtleActions, List<Double> myTurtleActionArgs, Map<String, Double> myTurtleParameters) {
        this.myTurtleActions = myTurtleActions;
        this.myTurtleActionsArgs = myTurtleActionArgs;
        this.myTurtleParameters = myTurtleParameters;
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        args = new Stack<>();
        myFactory = new Factory();
        out = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        for(String s : text) {
            if(isOperator(s)) {
                while(!stack.isEmpty() && isOperator(stack.peek())) {
                    out.add(stack.pop());
                }
            }
            else if (s.equals("[")) {
                stack.push(s);
            }
            else if (s.equals("]")) {
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            else {
                out.add(s);
            }
        }
        while(!stack.isEmpty()) {
            out.add(stack.pop());
        }

    }

    public void execute() {
        for(String s : out) {
            if(BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s)) {
                Command temp = createTempCommand(s);
                args.push("" + temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));
            }
            else if(TURTLE_COMMANDS.contains(s)) {
                Command temp = createTempCommand(s);
                temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters);
            }
            else {
                args.push(s);
            }
        }
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

    private Command createTempCommand(String s) {
        int numArgs = myNumArgsMap.get(s);
        LinkedList<String> tempArgs = new LinkedList<>();
        for(int i = 0; i < numArgs; i++) {
            tempArgs.add(args.pop());
        }
        return myFactory.makeCommand(s, tempArgs);
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
