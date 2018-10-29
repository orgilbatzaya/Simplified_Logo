package view.button;

import model.ErrorAlert;
import view.Console;

import java.io.FileOutputStream;
import java.util.*;

public class SaveFunctions extends SLogoButton{
    private Map<String, List<String>> myFunctionMap;
    private String fileName;
    private Console myConsole;

    public SaveFunctions(String label,Map<String, List<String>> myFuncs, String file, Console cons) {
        super(label);
        myFunctionMap = myFuncs;
        fileName = file;
        myConsole = cons;
    }

    @Override
    public void processCommand() {
        Properties properties = new Properties();
        Set set = myFunctionMap.keySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            String key = (String) itr.next();
            List<String> valueList = myFunctionMap.get(key);
            String value = createStringValueList(valueList);
            properties.setProperty(key, value);
        }
        try {
            properties.store(new FileOutputStream(fileName), "\"save functions\"");
        } catch (Exception e) {
            new ErrorAlert(e);
        }
    }

    private String createStringValueList(List<String> l) {
        String outputString = "";
        for (int i = 0; i < l.size(); i++) {
            outputString = outputString + l.get(i);
            if (i != l.size() - 1) {
                outputString = outputString + ",";
            }
        }
        return outputString;


    }
        

}
