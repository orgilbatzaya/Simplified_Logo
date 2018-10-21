package model;

//Receives an input block of text and performs the corresponding commands

import view.TurtleDisplay;

import java.util.*;


public class BackMain {

    public static final String WHITESPACE = "\\s+";
    public static final String NUM_ARGS_PATH = "commands/NumberArgs";
    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));

    private Boolean isCommand;
    private Map<String,Integer> myNumArgsMap;
    private ProgramParser myProgParser;
    private TurtleDisplay myTurtleDisplay;
    private String myLanguage;



    public BackMain(ResourceBundle lang, TurtleDisplay display){
        isCommand = Boolean.TRUE;
        myProgParser = createProgramParser(lang);
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myTurtleDisplay = display;
    }

    //simple implementation
    public void EvaluateCommands(String commands){
        int i=0;
        while(i<commands.split(WHITESPACE).length){
            String input = commands.split(WHITESPACE)[i];
            if (checkValidInput(input)){
                if(isCommand){
                    String command = myProgParser.getSymbol(input);
                    int numArgs = myNumArgsMap.get(command);
                    List<String> args = getArgs(commands.split(WHITESPACE),numArgs,i);
                    i = i+numArgs+1;
                    var interpreter = new Interpret();
                    interpreter.interpretCommand(command,args,myTurtleDisplay);
                }
            }
        }
    }

    public Boolean checkValidInput(String s){
        try {
            String command = myProgParser.getSymbol(s);
            return Boolean.TRUE;
        }
        catch (RuntimeException e){
        }
        try {
            double argument = Double.parseDouble(s);
            isCommand=Boolean.FALSE;
            return Boolean.TRUE;
        }
        catch (NumberFormatException e){
        }
        throw new RuntimeException("Invalid Command");

    }

    public List<String> getArgs(String[] inputs, int numArgs, int index){
        var args = new ArrayList<String>();
        for(int i =index+1; i<index+numArgs+1; i++){
            if(checkValidInput(inputs[i])){
                args.add(inputs[i]);
            }
        }
        return args;
    }

    public ProgramParser createProgramParser(ResourceBundle lang){
        var language = new ProgramParser();
        language.addPatterns(lang);
        return language;
    }

    public Map<String,Integer> getNumArgsMap(String path){
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String,Integer>();
        for (String key : properties.keySet()) {
            String value = properties.getString(key);
            outMap.put(key, Integer.valueOf(value));
        }
        return outMap;
    }

    public void performActions (ProgramParser lang, String[] text) {
        int type = 1; // 1 - control, 2 - turtle, 3 - math/bool
        String currentType;

        if(!myLanguage.equals("English")) {
            for(String s : text) {
                s = lang.getSymbol(s);
            }
        }
        /*
        for(String s : text) {
            if((BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s))) {
                if(type == 3) {
                    continue;
                }
                else if (type == 2) {
                    type = 3;
                    continue;
                }
                else {
                    break; //throw error here
                }
            }
            else if(TURTLE_COMMANDS.contains(s)) {
                if(type == 2) {
                    continue;
                }
                else {
                    type = 2;
                    continue;
                }

            }
            else if(CONTROL_OPS.contains(s)) {
                currentType = "Control";
            }
        }
        */

        Stack<String> toDo = new Stack<>();
        Stack<String> tempDone = new Stack<>();
        Stack<String> tempArgs = new Stack<>();
        Stack<Integer> loopTimes = new Stack<>();
        for(String s : text) {
            toDo.push(s);
        }
        while(!toDo.isEmpty()){
            String temp = toDo.pop();
            tempDone.push(temp);
            if(!(BOOLEAN_OPS.contains(temp) || MATH_OPS.contains(temp) || CONTROL_OPS.contains(temp) || TURTLE_COMMANDS.contains(temp))) {
                tempArgs.add(temp);
            }
            else if(BOOLEAN_OPS.contains(temp) || MATH_OPS.contains(temp)) {
                var interpreter = new Interpret();
                int numArgs = myNumArgsMap.get(temp);
                ArrayList<String> curArgs = new ArrayList<>();
                for (int i = 0; i < numArgs; i++) {
                    curArgs.add(tempArgs.pop());
                }
                tempArgs.push(interpreter.interpretCommand(temp, curArgs, myTurtleDisplay) + "");
            }
            else if(CONTROL_OPS.contains(temp)) {
                var interpreter = new Interpret();
                int tempTimes = loopTimes.pop();
                if(tempTimes >= 0) {
                    while(!tempDone.isEmpty()) {
                        toDo.push(tempDone.pop());
                    }
                    loopTimes.push(tempTimes - 1);
                }
                else {
                    toDo.pop();
                }
            }

        }

    }

}
