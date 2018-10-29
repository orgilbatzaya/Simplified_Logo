package model;

import java.util.*;

public class CommandStack {

    private Factory myFactory;
    private Map<String,Integer> myNumArgsMap;
    private List<String> myText;
    private List<String> myTurtleActions;
    private List<Double> myTurtleActionsArgs;
    private Map<String, Double> myTurtleParameters;
    private Map<String, Set<String>> myCommandTypeMap;
    private Stack<String> toDo;
    private Stack<String> args;
    private Stack<String> done;
    private HashMap<Integer, Integer> times;
    private HashMap<Integer, Integer> originalTimes;
    private int doCounter;
    private String myCommandType;
    private LinkedList<String> all;
    private boolean commandFinished;


    public CommandStack(List<String> text, List<String> myTurtleActions, List<Double> myTurtleActionArgs, Map<String, Double> myTurtleParameters, Map<String,Integer> numArgs, Map<String,Set<String>> commandTypeMap) {
        this.myTurtleActions = myTurtleActions;
        this.myTurtleActionsArgs = myTurtleActionArgs;
        this.myTurtleParameters = myTurtleParameters;
        myCommandTypeMap = commandTypeMap;
        myNumArgsMap = numArgs;
        myFactory = new Factory();
        myText = text;
        doCounter = 1;
        commandFinished = Boolean.FALSE;
    }

    public String execute(HashMap<String, String> variables){
        toDo = new Stack<>();
        args = new Stack<>();
        done = new Stack<>();
        all = new LinkedList<>();
        times = new HashMap<>();
        originalTimes = new HashMap<>();
        for (String temp : myText) {
            all.add(temp);
        }

        while (!all.isEmpty()) {
//            for(int i = 0; i<all.size(); i++){
//                System.out.println(all.get(i));
//            }
            while (!commandFinished) {
                try{
                    String temp = all.removeFirst();
                    if(myCommandTypeMap.get("OtherCommands").contains(temp)){
                        toDo.push(temp);
                        fillStack(toDo,all);
                        commandFinished = Boolean.TRUE;
                    }
                    else{
                        toDo.push(temp);
                        commandFinished = isCommandFinished(toDo);
                    }


                } catch(Exception e){
                    commandFinished = Boolean.TRUE;
                }
            }



            commandFinished = Boolean.FALSE;


            while (!toDo.isEmpty()) {
                String s = toDo.pop();
                myCommandType = getCommandType(s);
                if (myCommandTypeMap.get("BooleanOps").contains(s) || myCommandTypeMap.get("TurtleCommands").contains(s) || myCommandTypeMap.get("TurtleQueries").contains(s) ||
                        myCommandTypeMap.get("DisplayCommands").contains(s) || myCommandTypeMap.get("MathOps").contains(s)) {
                    int numArgs = myNumArgsMap.get(s);
                    LinkedList<String> tempArgs = new LinkedList<>();
                    for (int i = 0; i < numArgs; i++) {
                        tempArgs.add(args.pop());
                    }
                    Command temp = myFactory.makeCommand(s, tempArgs, myCommandType);
                    args.push("" + temp.execute(myTurtleActions, myTurtleActionsArgs, myTurtleParameters));
                    //System.out.println(s + " " + args.peek());
                    done.push(s);
                } else if (s.matches("DoTimes\\d*")) {
                    doTimes(s, variables);
                } else if (s.matches("For")) {
                    forLoop(variables);
                } else if (isDouble(s)) {
                    args.push(s);
                    done.push(s);
                } else if (s.equals("[") || s.equals("]")) {
                    done.push(s);
                } else if (s.equals("MakeVariable")) {
                    variables.put(done.peek().substring(1), args.peek());
                    done.push(s);
                } else if (s.matches(":[a-zA-Z]+")) {
                    String temp = s.substring(1);
                    if (!variables.containsKey(temp) && toDo.peek().equals("DoTimes")) {
                        variables.put(temp, args.peek());
                    } else if (variables.containsKey(temp)) {
                        args.push(variables.get(temp));
                    }
                    done.push(s);
                }
            }
        }

        return args.pop();
    }

    private void doTimes(String s, HashMap<String, String> variables) {
        if (s.equals("DoTimes")) {
            int time = Integer.parseInt(args.pop());
            variables.put(done.peek().substring(1), "" + time);
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

    private void forLoop(HashMap<String, String> variables) {
        Stack<String> brackets = new Stack<String>();
        brackets.push("[");
        done.pop();
        String start = args.pop();
        String variable = done.pop();
        int end = Integer.parseInt(args.pop());
        int increment = Integer.parseInt(args.pop());
        System.out.println("Start" + start + " " + "end " + end + " " + increment);

        if(!variables.containsKey(variable.substring(1))) {
            variables.put(variable.substring(1), "" + (Integer.parseInt(start) + increment));
        }
        int curNum = Integer.parseInt(variables.get(variable.substring(1)));
        if(curNum + increment > end) {
            variables.put(variable.substring(1), start);
        } else {
            variables.put(variable.substring(1), "" + (curNum + increment));
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

    private String getCommandType(String commandName){
        for (String key : myCommandTypeMap.keySet()) {
            if(myCommandTypeMap.get(key).contains(commandName)){
                return key;
            }
        }
        return null;
    }

    private Boolean isCommandFinished(Stack<String> sta){

        List<String> list = new ArrayList(sta);
//        for(int i = 0; i<list.size(); i++){
//            System.out.print(list.get(i));
//        }
//        System.out.println();

        int i = 0;
        while(i<list.size()){
            String s = list.get(i);
//            System.out.println(s);
            if(getCommandType((s))!=null){
                int numArgs = myNumArgsMap.get(s);
                int num = 0;
                for(int j = i+1; j<=(i+numArgs);j++){
                    try{
                        Double.parseDouble(list.get(j));
                        num++;
                    }catch (Exception e){
                    }
                }
                if(num==numArgs){
                    return Boolean.TRUE;
                }
            }
            i++;
        }
        return Boolean.FALSE;
    }

    public void fillStack(Stack<String> stack,LinkedList<String> list ){
        for(String s: list){
            stack.push(s);
            System.out.println(s);
        }
    }

}
