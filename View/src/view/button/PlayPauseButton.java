package view.button;

import view.TurtleDisplay;

public class PlayPauseButton extends SLogoButton{
    private TurtleDisplay myDisplay;

    public PlayPauseButton(String label, TurtleDisplay display) {
        super(label);
        myDisplay = display;
    }

    @Override
    public void processCommand() {
        if(getDisplay().getText().equals("Play")) {
            myDisplay.getCurrentAnimation().play();
            getDisplay().setText("Pause");
        } else {
            myDisplay.getCurrentAnimation().pause();
            getDisplay().setText("Play");
        }
    }
}
