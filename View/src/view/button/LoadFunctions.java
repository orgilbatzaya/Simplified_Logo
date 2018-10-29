package view.button;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ErrorAlert;
import view.Console;
import view.environmentdisplays.FunctionDisplay;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class LoadFunctions extends SLogoButton{

    private static final String RESOURCE_PATH = "data/SavedFunctions/";
    private static final String SAVED_PATH = "/SavedFunctions/";
    private FunctionDisplay myFunctionDisplay;
    private Console myConsole;


    public LoadFunctions(String label, FunctionDisplay funcDisp, Console cons) {
        super(label);
        myFunctionDisplay = funcDisp;
        myConsole = cons;
    }

    @Override
    public void processCommand() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                ResourceBundle myFuncs = ResourceBundle.getBundle(SAVED_PATH+file.getName());
                var funcMap = new HashMap<String, List<String>>();
                for (String key : myFuncs.keySet()) {
                    List<String> a = Arrays.asList(myFuncs.getString(key).split(","));
                    funcMap.put(key, a);
                }
                myFunctionDisplay.update(funcMap);

            } catch(Exception e){
                new ErrorAlert(e);
            }
        }
    }
}
