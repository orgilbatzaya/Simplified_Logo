package view.button;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ErrorAlert;
import view.Console;
import view.Function;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class SaveFunctions extends SLogoButton{
    private static final String RESOURCE_PATH = "data/images/";
    private static final String IMAGE_PATH = "/images/";
    private static final String TITLE = "Title";

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(TITLE);
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                List<Function> functionList = myConsole.getFunctions();
                for(int i = 0; i < functionList.size(); i++) {
                    properties.put(functionList.get(i).getFunctionName(), functionList.get(i).getFunctionDefinition());
                }
            } catch (Exception ex) {
                new ErrorAlert(ex);
            }
        }
    }
}
