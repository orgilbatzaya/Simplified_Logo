package view;

import model.BackMain;
import java.util.List;

public class CommandProcessor {

    public CommandProcessor(GUISetup gui) {
        processAndAnimateCommand(gui);
    }

    public void processAndAnimateCommand(GUISetup gui) {
        BackMain back = new BackMain(gui.getButtonManager().getLanguageFromUserOptions(), gui.getTurtleParams());
        System.out.println(1);
        back.performCommands(gui.getConsole().getNextCommand());
        System.out.println(2);
        List<String> actionList = back.getMyTurtleActions();
        System.out.println(3);
        List<Double> actionArgs = back.getMyTurtleActionsArgs();
        ActionRunner actInterpret = new ActionRunner();
        actInterpret.performActions(actionList,actionArgs,gui.getButtonManager().getTurtleDisplay());
    }
}
