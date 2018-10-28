package view;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public interface FrontExternal {
    String HEADING_KEY = "heading";
    String X_KEY = "xPos";
    String Y_KEY = "yPos";
    String DISTANCE_MOVED_KEY = "distanceMoved";
    String PEN_KEY = "pen";
    String VISIBLE_KEY = "visible";
    String[] keyElements = {HEADING_KEY,X_KEY,Y_KEY,PEN_KEY,VISIBLE_KEY,DISTANCE_MOVED_KEY};
}
