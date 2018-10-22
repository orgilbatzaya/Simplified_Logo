package view;

import model.BackMain;
import java.util.List;

public class CommandProcessor {

    public CommandProcessor(GUISetup gui) {
        processAndAnimateCommand(gui);
    }

    public void processAndAnimateCommand(GUISetup gui) {
        BackMain back = new BackMain(gui.getLanguage(), gui.getTurtleParams());
        back.performCommands(gui.getConsole().getNextCommand());
        List<String> actionList = back.getMyTurtleActions();
        List<Double> actionArgs = back.getMyTurtleActionsArgs();
        ActionRunner actInterpret = new ActionRunner();
        actInterpret.performActions(actionList,actionArgs,gui.getTurtleDisplay());
    }
}
