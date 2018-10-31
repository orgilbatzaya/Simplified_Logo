package view.button;

import view.GUISetup;
import view.TurtleDisplay;

/**
 * This class controls if the current animation on screen will be played or paused.
 * @author Austin Kao
 */
public class PlayPauseButton extends SLogoButton{
    private static final String PLAY = "Play";
    private static final String PAUSE = "Pause";
    private GUISetup parentGUI;

    public PlayPauseButton(String label, GUISetup gui) {
        super(label);
        parentGUI = gui;
    }

    @Override
    public void processCommand() {
        if(getDisplay().getText().equals(PLAY)) {
            parentGUI.getCurrentDisplay().getCurrentAnimation().play();
            getDisplay().setText(PAUSE);
        } else {
            parentGUI.getCurrentDisplay().getCurrentAnimation().pause();
            getDisplay().setText(PLAY);
        }
    }
}
