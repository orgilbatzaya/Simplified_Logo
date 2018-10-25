package model;

//Receives an input block of text and performs the corresponding commands

import model.commands.MathOps.Argument;
import model.commands.MathOps.Variable;
import model.commands.OtherCommands.DoTimes;

import java.util.*;


public class BackMain {

    public static final String WHITESPACE = "\\s+";
    public static final String NUM_ARGS_PATH = "model/commands/NumArgsCommands";
    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));

    private Boolean isCommand;
    private Map<String,Integer> myNumArgsMap;
    private ProgramParser myProgParser;
    private ResourceBundle myLanguage;
    private HashMap<String, Double> variables;
    private Map<String,Double> myTurtleParameters;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;



    public BackMain(ResourceBundle lang, Map<String,Double> turtleParams){
        isCommand = Boolean.TRUE;
        myProgParser = createProgramParser(lang);
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myTurtleParameters = turtleParams;
        variables = new HashMap<>();
        myTurtleActions = new ArrayList<String>();
        myTurtleActionsArgs = new ArrayList<Double>();
        myLanguage = lang;
    }


    //simple implementation
//    public void EvaluateCommands(String commands){
//        int i=0;
//        while(i<commands.split(WHITESPACE).length){
//            String input = commands.split(WHITESPACE)[i];
//            if (checkValidInput(input)){
//                if(isCommand){
//                    String command = myProgParser.getSymbol(input);
//                    int numArgs = myNumArgsMap.get(command);
//                    List<String> args = getArgs(commands.split(WHITESPACE),numArgs,i);
//                    var interpreter = new Interpret();
//                    double output = interpreter.interpretCommand(command,args,myTurtleParameters,myTurtleActions,myTurtleActionsArgs);
//                    i = i+numArgs+1;
//
//                }
//            }
//        }
//    }

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
            outMap.put(key, Integer.parseInt(value));
        }
        return outMap;
    }

    public void performCommands (String rawText) {
        String[] text = rawText.split(WHITESPACE);
        int type = 1; // 1 - control, 2 - turtle, 3 - math/bool
        String currentType;
        for(int i =0; i<text.length; i++){
            text[i] = myProgParser.getSymbol(text[i]);
        }

        Stack<Command> toDo = new Stack<>();
        Stack<Command> tempDone = new Stack<>();
        Stack<Command> tempArgs = new Stack<>();

        Factory fac = new Factory();
        for(String s : text) {
            if((BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s) || CONTROL_OPS.contains(s) || TURTLE_COMMANDS.contains(s))) {
                toDo.push(fac.makeCommand(s, new ArrayList<String>()));
            }
            else {
                if(isDouble(s)) {
                    toDo.push(new Argument(Double.parseDouble(s)));
                }
                else {
                    toDo.push(new Variable(s));
                }
            }
        }
        while(!toDo.isEmpty()){

            Command temp = toDo.peek();
            String[] split = temp.getClass().getName().split("\\.");
            String s = split[split.length - 1];
            // System.out.println(((Argument) temp).execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));

            if(BOOLEAN_OPS.contains(s) || MATH_OPS.contains(s)) {
                var interpreter = new Interpret();
                int numArgs = myNumArgsMap.get(s);
                ArrayList<String> curArgs = new ArrayList<>();
                for (int i = 0; i < numArgs; i++) {
                    curArgs.add(0, tempArgs.pop().execute(myTurtleActions, myTurtleActionsArgs,  myTurtleParameters) + "");
                }
                tempArgs.push(new Argument(interpreter.interpretCommand(s, curArgs, myTurtleParameters,myTurtleActions,myTurtleActionsArgs)));
                toDo.pop();
                tempDone.push(temp);
            }

            else if(CONTROL_OPS.contains(s)) {
                if(temp.execute(myTurtleActions, myTurtleActionsArgs,  myTurtleParameters) <= 0) {
                    ((DoTimes) temp).setValue(((DoTimes)temp).getOriginalValue());
                    toDo.pop();
                    tempDone.push(temp);
                }
                else {
                    while(!tempDone.isEmpty()) {
                        toDo.push(tempDone.pop());
                    }
                    temp.execute(myTurtleActions, myTurtleActionsArgs,  myTurtleParameters);
                }
            }
            else if(TURTLE_COMMANDS.contains(s)) {
                var interpreter = new Interpret();
                int numArgs = myNumArgsMap.get(s);
                ArrayList<String> curArgs = new ArrayList<>();
                for (int i = 0; i < numArgs; i++) {
                    curArgs.add(0, tempArgs.peek().execute(myTurtleActions, myTurtleActionsArgs,  myTurtleParameters) + "");
                    Command com = tempArgs.peek();
                    myTurtleActions = com.getMyTurtleActions();
                    myTurtleActionsArgs = com.getMyTurtleArgs();
                    tempArgs.pop();

                    for(int j=0; j<myTurtleActions.size(); i++){
                        System.out.print(myTurtleActions.get(j)+" ");
                    }
                    System.out.println();
                }
                toDo.pop();
                tempDone.push(temp);
            }
            else if(s.equals("Variable")) {
                if(!variables.containsKey(((Variable)temp).getValue())) {
                    variables.put(((Variable) temp).getValue(), tempArgs.pop().execute(myTurtleActions, myTurtleActionsArgs,  myTurtleParameters));
                }
                tempArgs.push(new Argument(variables.get(((Variable)temp).getValue())));
                toDo.pop();
                tempDone.push(temp);
            }

            else if(s.equals("Argument")) {
                tempArgs.push(temp);
                toDo.pop();
                tempDone.push(temp);
            }
        }
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }

    }

    public List<String> getMyTurtleActions(){
        return myTurtleActions;
    }

    public List<Double> getMyTurtleActionsArgs(){
        return myTurtleActionsArgs;
    }


}
