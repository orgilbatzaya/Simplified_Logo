package view.button;

import view.Console;

public class SaveButton extends SLogoButton{
    private Console myConsole;

    public SaveButton(String label, Console console) {
        super(label);
        myConsole = console;
    }

    @Override
    public void processCommand() {
        
    }
}
