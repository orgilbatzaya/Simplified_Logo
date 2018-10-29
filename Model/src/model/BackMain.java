package model;

//Receives an input block of text and performs the corresponding commands

import java.util.*;


public class BackMain {

    public static final String NUM_ARGS_PATH = "model/commands/NumArgsCommands";
    public static final String COMMAND_TYPE_PATH = "model/CommandType";


    private Boolean isCommand;
    private Map<String, Integer> myNumArgsMap;
    private Map<String, Set<String>> myCommandTypeMap;
    private ProgramParser myProgParser;
    private ProgramParser mySyntaxParser;
    private HashMap<String, String> variables;
    private HashMap<String, List<String>> functionMap;
    private HashMap<String, List<String>> functionParams;
    private Map<String, Double> myTurtleParameters;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;


    public BackMain(ResourceBundle lang, Map<String, Double> turtleParams, HashMap<String,String> vars, HashMap<String, List<String>> functionMap, HashMap<String, List<String>> functionParams) {
        isCommand = Boolean.TRUE;
        myProgParser = createProgramParser(lang);
        mySyntaxParser = createProgramParser(ResourceBundle.getBundle("model/Syntax"));
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myCommandTypeMap = getMyCommandTypeMap(COMMAND_TYPE_PATH);
        myTurtleParameters = turtleParams;
        myTurtleActions = new ArrayList<>();
        myTurtleActionsArgs = new ArrayList<>();
        variables = vars;
        this.functionMap = functionMap;
        this.functionParams = functionParams;
    }




    public ProgramParser createProgramParser(ResourceBundle lang) {
        var language = new ProgramParser();
        language.addPatterns(lang);
        return language;
    }

    public void performCommands(String rawText) {
        String[] text = rawText.split("\\s+");
        for (int i = 0; i < text.length; i++) {
            text[i] = myProgParser.getSymbol(text[i]);
        }
        ArrayList<String> commandList = new ArrayList<>(Arrays.asList(text));
        ArrayList<String> newCommands = new ArrayList<>();

        int index = 0;
        while (index < commandList.size()) {
            String s = commandList.get(index);
            if(s.equals("If")) {
                index++;
                ArrayList<String> ifExp = new ArrayList<>();
                while(!commandList.get(index).equals("[")) {
                    ifExp.add(commandList.get(index));
                    index++;
                }
                CommandStack ifExpEval = new CommandStack(ifExp, myTurtleActions, myTurtleActionsArgs, myTurtleParameters, myNumArgsMap, myCommandTypeMap);
                if(Double.parseDouble(ifExpEval.execute(variables)) != 1) {
                    Stack<String> brackets = new Stack<>();
                    brackets.push(commandList.get(index));
                    index++;
                    while(!brackets.isEmpty()) {
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        index++;
                    }
                }
            }
            else if(s.equals("IfElse")) {
                index++;
                ArrayList<String> ifExp = new ArrayList<>();
                while(!commandList.get(index).equals("[")) {
                    ifExp.add(commandList.get(index));
                    index++;
                }
                CommandStack ifExpEval = new CommandStack(ifExp, myTurtleActions, myTurtleActionsArgs, myTurtleParameters, myNumArgsMap, myCommandTypeMap);
                if(Double.parseDouble(ifExpEval.execute(variables)) == 1) {
                    newCommands.add(commandList.get(index));
                    Stack<String> brackets = new Stack<>();
                    brackets.push(commandList.get(index));
                    index++;
                    while(!brackets.isEmpty()) {
                        if(commandList.get(index).equals("[")) {
                            brackets.push(commandList.get(index));
                        }
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        newCommands.add(commandList.get(index));
                        index++;
                    }
                    brackets.push(commandList.get(index));
                    index++;
                    while(!brackets.isEmpty()) {
                        if(commandList.get(index).equals("[")) {
                            brackets.push(commandList.get(index));
                        }
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        index++;
                    }
                }
                else {
                    Stack<String> brackets = new Stack<>();
                    brackets.push(commandList.get(index));
                    index++;
                    while(!brackets.isEmpty()) {
                        if(commandList.get(index).equals("[")) {
                            brackets.push(commandList.get(index));
                        }
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        index++;
                    }
                }
            }
            else if (s.equals("MakeUserInstruction")) {
                index++;
                String functionName = commandList.get(index);
                List<String> params = new ArrayList<>();
                index++;
                Stack<String> brackets = new Stack<>();
                brackets.push(commandList.get(index));
                index++;
                while(!brackets.isEmpty()) {
                    String tempBracket = commandList.get(index);
                    if(commandList.get(index).equals("[")) {
                        brackets.push(commandList.get(index));
                    }
                    else if(tempBracket.equals("]")) {
                        brackets.pop();
                    }
                    else {
                        params.add(tempBracket);
                    }
                    index++;
                }
                functionParams.put(functionName, params);
                List<String> functionCommands = new ArrayList<>();
                brackets.push(commandList.get(index));
                index++;
                while(!brackets.isEmpty()) {
                    String tempBracket = commandList.get(index);
                    if(commandList.get(index).equals("[")) {
                        brackets.push(commandList.get(index));
                    }
                    else if(tempBracket.equals("]")) {
                        brackets.pop();
                    }
                    else {
                        functionCommands.add(tempBracket);
                    }
                    index++;
                }
                functionMap.put(functionName, functionCommands);
            }
            else if (functionMap.containsKey(s)) {
                index+=2;
                for(String tempCommand : functionMap.get(s)) {
                    newCommands.add(tempCommand);
                }
                for(String tempParam : functionParams.get(s)) {
                    newCommands.add("Set");
                    newCommands.add(tempParam);
                    newCommands.add(commandList.get(index));
                    index++;
                }
                index++;
            }
            else {
                newCommands.add(commandList.get(index));
                index++;
            }
        }
        if(newCommands.isEmpty()) {
            return;
        }
        /*
        for(String temp : newCommands) {
            System.out.print(temp + " ");
        }
        System.out.println();
        */

        CommandStack result = new CommandStack(newCommands, myTurtleActions, myTurtleActionsArgs, myTurtleParameters, myNumArgsMap, myCommandTypeMap);
        result.execute(variables);

    }

    public Map<String, Set<String>> getMyCommandTypeMap(String path) {
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String, Set<String>>();
        for (String key : properties.keySet()) {
            Set<String> mySet = new HashSet<String>(Arrays.asList(properties.getString(key).split(",")));
            outMap.put(key, mySet);
        }
        return outMap;
    }

    public Map<String, Integer> getNumArgsMap(String path) {
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String, Integer>();
        for (String key : properties.keySet()) {
            String value = properties.getString(key);
            outMap.put(key, Integer.parseInt(value));
        }
        return outMap;
    }



    public List<String> getMyTurtleActions() {
        return myTurtleActions;
    }

    public List<Double> getMyTurtleActionsArgs() {
        return myTurtleActionsArgs;
    }

    public HashMap<String,String> getVariables(){ return variables;}

    public HashMap<String, List<String>> getFunctions(){ return functionMap;}

    public HashMap<String, List<String>> getFunctionsParms() { return functionParams; }

}
