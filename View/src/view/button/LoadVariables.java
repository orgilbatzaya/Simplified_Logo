package view.button;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ErrorAlert;
import view.Console;
import view.environmentdisplays.VariableDisplay;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoadVariables extends SLogoButton{

    private static final String RESOURCE_PATH = "data/SavedVariables/";
    private static final String SAVED_PATH = "/SavedVariables/";
    private VariableDisplay myVariableDisplay;
    private Console myConsole;

    public LoadVariables(String label, VariableDisplay varDisp, Console cons) {
        super(label);
        myVariableDisplay = varDisp;
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
                ResourceBundle myVars = ResourceBundle.getBundle(SAVED_PATH+file.getName());
                var varMap = new HashMap<String, String>();
                for (String key : myVars.keySet()) {
                    varMap.put(key, myVars.getString(key));
                }
                myVariableDisplay.update(varMap);

            } catch(Exception e){
                new ErrorAlert(e);
            }
        }
    }
}
