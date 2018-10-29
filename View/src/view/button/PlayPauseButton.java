package view.button;

import view.TurtleDisplay;

/**
 * This class controls if the current animation on screen will be played or paused.
 * @author Austin Kao
 */
public class PlayPauseButton extends SLogoButton{
    private static final String PLAY = "Play";
    private static final String PAUSE = "Pause";
    private TurtleDisplay myDisplay;

    public PlayPauseButton(String label, TurtleDisplay display) {
        super(label);
        myDisplay = display;
    }

    @Override
    public void processCommand() {
        if(getDisplay().getText().equals(PLAY)) {
            myDisplay.getCurrentAnimation().play();
            getDisplay().setText(PAUSE);
        } else {
            myDisplay.getCurrentAnimation().pause();
            getDisplay().setText(PLAY);
        }
    }
}
