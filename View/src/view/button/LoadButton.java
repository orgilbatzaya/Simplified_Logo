package view.button;

import view.Console;

public class LoadButton extends SLogoButton{
    private Console myConsole;

    public LoadButton(String label, Console console) {
        super(label);
        myConsole = console;
    }

    @Override
    public void processCommand() {

    }
}
