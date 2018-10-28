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
    private ResourceBundle myLanguage;
    private HashMap<String, String> variables;
    private Map<String, Double> myTurtleParameters;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;

    public BackMain(ResourceBundle lang, Map<String, Double> turtleParams) {
        isCommand = Boolean.TRUE;
        myProgParser = createProgramParser(lang);
        myNumArgsMap = getNumArgsMap(NUM_ARGS_PATH);
        myCommandTypeMap = getMyCommandTypeMap(COMMAND_TYPE_PATH);
        myTurtleParameters = turtleParams;
        variables = new HashMap<>();
        myTurtleActions = new ArrayList<>();
        myTurtleActionsArgs = new ArrayList<>();
        myLanguage = lang;
        variables = new HashMap<>();
    }

    public Boolean checkValidInput(String s) {
        try {
            String command = myProgParser.getSymbol(s);
            return Boolean.TRUE;
        } catch (RuntimeException e) {
        }
        try {
            double argument = Double.parseDouble(s);
            isCommand = Boolean.FALSE;
            return Boolean.TRUE;
        } catch (NumberFormatException e) {
        }
        throw new RuntimeException("Invalid Command");

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
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        newCommands.add(commandList.get(index));
                        index++;
                    }
                    brackets.push(commandList.get(index));
                    index++;
                    while(!brackets.isEmpty()) {
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
                        if(commandList.get(index).equals("]")) {
                            brackets.pop();
                        }
                        index++;
                    }
                }
            }
            else {
                newCommands.add(commandList.get(index));
                index++;
            }


        }
        if(newCommands.isEmpty()) {
            return;
        }
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


}
