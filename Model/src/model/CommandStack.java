package model;

import java.util.*;

public class CommandStack {

    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));
    public static final String NUM_ARGS_PATH = "model/commands/NumArgsCommands";

    private Factory myFactory;
    private Map<String, Integer> myNumArgsMap;
    private List<String> myText;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;
    private Map<String, Double> myTurtleParameters;
    private Stack<String> toDo;
    private Stack<String> args;
    private Stack<String> done;
    private HashMap<Integer, Integer> times;
    private HashMap<Integer, Integer> originalTimes;
    private int doCounter;
    private HashMap<String, String> variables;

    public CommandStack(List<String> text, List<String> myTurtleActions, List<Double> myTurtleActionArgs, Map<String, Double> myTurtleParameters) {
        this.myTurtleActions = myTurtleActions;
        this.myTurtleActionsArgs = myTurtleActionArgs;
        this.myTurtleParameters = myTurtleParameters;
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myFactory = new Factory();
        myText = text;
        doCounter = 1;
        variables = new HashMap<>();
    }

    public String execute() {
        toDo = new Stack<>();
        args = new Stack<>();
        done = new Stack<>();
        times = new HashMap<>();
        originalTimes = new HashMap<>();
        int loopCounter = 1;
        for (String temp : myText) {
            toDo.push(temp);
        }
        while (!toDo.isEmpty()) {
            String s = toDo.pop();
            System.out.println(s);
            if (BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s) || TURTLE_COMMANDS.contains(s)) {
                int numArgs = myNumArgsMap.get(s);
                LinkedList<String> tempArgs = new LinkedList<>();
                for (int i = 0; i < numArgs; i++) {
                    tempArgs.add(args.pop());
                }
                Command temp = myFactory.makeCommand(s, tempArgs);
                args.push("" + temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));
                done.push(s);
            } else if (s.matches("DoTimes\\d*")) {
                doTimes(s);
            } else if (s.matches("For")) {
                forLoop();
            } else if (isDouble(s)) {
                args.push(s);
                done.push(s);
            } else if (s.equals("[") || s.equals("]")){
                done.push(s);
            } else {
                if(!variables.containsKey(s) && !toDo.peek().equals("FOR")) {
                    variables.put(s, args.peek());
                    done.push(s);
                }
            }
        }
        return args.pop();
    }

    private void doTimes(String s) {
        if (s.equals("DoTimes")) {
            int time = Integer.parseInt(args.pop());
            if (time == 1) {
                done.push(s);
                return;
            }
            toDo.push("DoTimes" + doCounter);
            times.put(doCounter, time - 1);
            originalTimes.put(doCounter, time);
            doCounter++;
        } else {
            int loopId = Integer.parseInt(s.substring(7));
            if (times.get(loopId) == 1) {
                done.push(s);
                times.put(loopId, originalTimes.get(loopId));
                return;
            }
            toDo.push(s);
            times.put(loopId, times.get(loopId) - 1);
        }
        Stack<String> brackets = new Stack<String>();
        String tempBracket;
        while (true) {
            tempBracket = done.pop();
            if (tempBracket.equals("[")) {
                brackets.push("[");
                toDo.push("[");
                break;
            }
        }
        while (!brackets.isEmpty()) {
            tempBracket = done.pop();
            if (tempBracket.equals("[")) {
                brackets.push(tempBracket);
            }
            if (tempBracket.equals("]")) {
                brackets.pop();
            }
            toDo.push(tempBracket);
        }
    }

    private void forLoop() {

        Stack<String> brackets = new Stack<String>();
        brackets.push("[");
        done.pop();
        String start = args.pop();
        String variable = done.pop();
        int end = Integer.parseInt(args.pop());
        int increment = Integer.parseInt(args.pop());
        if(!variables.containsKey(variable)) {
            variables.put(variable, "" + (Integer.parseInt(start) + increment));
        }
        int curNum = Integer.parseInt(variables.get(variable));
        System.out.println("CurNum = " + curNum);
        if(curNum + increment > end) {
            variables.put(variable, start);
        } else {
            variables.put(variable, "" + (curNum + increment));
            toDo.push("For");
            toDo.push("[");
            toDo.push(variable);
            while (!brackets.isEmpty()) {
                String tempBracket = done.pop();
                if (tempBracket.equals("]")) {
                    brackets.pop();
                }
                toDo.push(tempBracket);
            }
            brackets.push(done.pop());
            toDo.push("[");
            while (!brackets.isEmpty()) {
                String tempBracket = done.pop();
                if (tempBracket.equals("]")) {
                    brackets.pop();
                }
                toDo.push(tempBracket);
            }
        }
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Map<String, Integer> getNumArgsMap(String path) {
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String, Integer>();
        for (String key : properties.keySet()) {
            String value = properties.getString(key);
            outMap.put(key, Integer.parseInt(value));
        }
        return outMap;
    }
}
