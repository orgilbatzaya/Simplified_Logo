package model;

//Receives an input block of text and performs the corresponding commands

import java.util.*;


public class BackMain {

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
        myTurtleActions = new ArrayList<>();
        myTurtleActionsArgs = new ArrayList<>();
        myLanguage = lang;
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
            outMap.put(key, Integer.parseInt(value));
        }
        return outMap;
    }

    public void performCommands (String rawText) {
        String[] text = rawText.split("\\s+");
        for(int i =0; i<text.length; i++){
            text[i] = myProgParser.getSymbol(text[i]);
        }

        ArrayList<String> commandList = new ArrayList<>(Arrays.asList(text));
        ArrayList<String> newCommands = new ArrayList<>();

        for(int i = 0; i < commandList.size(); i++) {
            String s = commandList.get(i);
            if(CONTROL_OPS.contains(s)) {
                
            }
        }
        //Russell testing commands
        Factory fac = new Factory();
        ArrayList<String> commandArgs = new ArrayList<String>();
        commandArgs.add(text[1]);
        commandArgs.add(text[2]);
        var com = fac.makeCommand(text[0],commandArgs);
        com.execute(myTurtleActions,myTurtleActionsArgs,myTurtleParameters);
        for(int i = 0; i<myTurtleActions.size(); i++){
            System.out.println(myTurtleActions.get(i));
        }
        for(int i = 0; i<myTurtleActionsArgs.size(); i++){
            System.out.println(myTurtleActionsArgs.get(i));
        }
        for (Map.Entry entry : myTurtleParameters.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
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
