package view.button;

import view.Console;

import java.io.FileOutputStream;
import java.util.*;

public class SaveVariables extends SLogoButton{
    private Map<String, String> myVariableMap;
    private String fileName;
    private Console myConsole;


    public SaveVariables(String label,Map<String, String> myVars, String file, Console cons) {
        super(label);
        myVariableMap = myVars;
        fileName = file;
        myConsole = cons;
    }

    @Override
    public void processCommand() {
        Properties properties = new Properties();
        Set set = myVariableMap.keySet();
        Iterator itr = set.iterator();
        while(itr.hasNext()) {
            String key = (String) itr.next();
            String value = myVariableMap.get(key);
            properties.setProperty(key, value);
        }
        try{
            properties.store(new FileOutputStream(fileName),"\"save Variables\"");
        } catch (Exception e){
        }

    }
}
