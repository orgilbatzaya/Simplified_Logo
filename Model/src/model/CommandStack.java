package model;

import java.util.*;

public class CommandStack {

    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));
    private final Set<String> DISPLAY_COMMANDS = new HashSet<>(Arrays.asList("GetPenColor","GetPenShape","SetBackground","SetPalette","SetPenColor","SetPenSize","SetShape"));
    private Factory myFactory;
    private Map<String,Set<String>> myCommandTypeMap;
    private Map<String,Integer> myNumArgsMap;
    private List<String> myText;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;
    private Map<String,Double> myTurtleParameters;

    public CommandStack(List<String> text, List<String> myTurtleActions, List<Double> myTurtleActionArgs, Map<String, Double> myTurtleParameters, Map<String,Integer> numArgs, Map<String,Set<String>> commandType) {
        this.myTurtleActions = myTurtleActions;
        this.myTurtleActionsArgs = myTurtleActionArgs;
        this.myTurtleParameters = myTurtleParameters;
        myNumArgsMap = numArgs;
        myCommandTypeMap = commandType;
        myFactory = new Factory(myCommandTypeMap);
        myText = text;
    }

    public String execute() {
        Stack<String> toDo = new Stack<>();
        Stack<String> args = new Stack<>();
        Stack<String> done = new Stack<>();
        HashMap<Integer, Integer> times = new HashMap<>();
        HashMap<Integer, Integer> originalTimes = new HashMap<>();
        int loopCounter = 1;
        for(String temp : myText) {
            toDo.push(temp);
        }
        while(!toDo.isEmpty()) {
            String s = toDo.pop();
            if(BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s) || TURTLE_COMMANDS.contains(s) || DISPLAY_COMMANDS.contains(s)) {
                int numArgs = myNumArgsMap.get(s);
                LinkedList<String> tempArgs = new LinkedList<>();
                for(int i = 0; i < numArgs; i++) {
                    tempArgs.add(args.pop());
                }
                Command temp = myFactory.makeCommand(s, tempArgs);
                args.push("" + temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));
                done.push(s);
            }
            else if (CONTROL_OPS.contains(s)) {
                if(s.equals("DoTimes")) {
                    int time = Integer.parseInt(args.pop());
                    if(time == 1) {
                        done.push(s);
                        continue;
                    }
                    toDo.push("DoTimes" + loopCounter);
                    times.put(loopCounter, time - 1);
                    originalTimes.put(loopCounter, time);
                    loopCounter++;
                    Stack<String> brackets = new Stack<String>();
                    String tempBracket;
                    stackProcessing(toDo, done, brackets);
                    StackProcessing2(toDo, done, brackets);
                }
            }
            else if(s.matches("DoTimes\\d+")) {
                int loopId = Integer.parseInt(s.substring(7));
                if(times.get(loopId) == 1) {
                    done.push(s);
                    times.put(loopId, originalTimes.get(loopId));
                    continue;
                }
                toDo.push(s);
                times.put(loopId, times.get(loopId) - 1);

                Stack<String> brackets = new Stack<String>();
                String tempBracket;
                stackProcessing(toDo, done, brackets);
                StackProcessing2(toDo, done, brackets);
            }
            else if(isDouble(s) || s.equals("[") || s.equals("]")){
                args.push(s);
                done.push(s);
            }
        }
        return args.pop();
    }

    private void StackProcessing2(Stack<String> toDo, Stack<String> done, Stack<String> brackets) {
        String tempBracket;
        while(!brackets.isEmpty()) {
            tempBracket = done.pop();
            if(tempBracket.equals("[")) {
                brackets.push(tempBracket);
            }
            if(tempBracket.equals("]")) {
                brackets.pop();
            }
            toDo.push(tempBracket);
        }
    }

    private void stackProcessing(Stack<String> toDo, Stack<String> done, Stack<String> brackets) {
        String tempBracket;
        while(true) {
            tempBracket = done.pop();
            if(tempBracket.equals("[")) {
                brackets.push("[");
                toDo.push("[");
                break;
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
