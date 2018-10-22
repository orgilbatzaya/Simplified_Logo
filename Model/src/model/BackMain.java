package model;

//Receives an input block of text and performs the corresponding commands


import java.util.*;


public class BackMain {

    public static final String WHITESPACE = "\\s+";
    public static final String NUM_ARGS_PATH = "commands/NumberArgs";

    private Boolean isCommand;
    private Map<String,Integer> myNumArgsMap;
    private ProgramParser myProgParser;
    private Map<String,Double> myTurtleParameters;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;

    public BackMain(ResourceBundle lang, Map<String,Double> turtleParams){
        isCommand = Boolean.TRUE;        myProgParser = createProgramParser(lang);
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myTurtleParameters = turtleParams;
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
                    var interpreter = new Interpret();
                    double output = interpreter.interpretCommand(command,args,myTurtleParameters,myTurtleActions,myTurtleActionsArgs);
                    i = i+numArgs+1;

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
}
