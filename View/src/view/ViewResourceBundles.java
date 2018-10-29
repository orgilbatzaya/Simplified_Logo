package view;

import model.ErrorAlert;

import java.util.ResourceBundle;

/**
 * Interface that defines resource bundles used by different View classes. DO NOT CHANGE Strings that specify paths unless the path also changes.
 * @author Austin Kao
 */
public interface ViewResourceBundles {
    ResourceBundle myImages = ResourceBundle.getBundle("/resources/TurtleImage");
    ResourceBundle myColors = ResourceBundle.getBundle("/resources/Color");
    ResourceBundle myLanguages = ResourceBundle.getBundle("/resources/LanguageList");
    ResourceBundle myDefaults = ResourceBundle.getBundle("/resources/ViewDefaults");
    ResourceBundle myArgs = ResourceBundle.getBundle("/resources/NumArgsActions");
    ResourceBundle myDirections = ResourceBundle.getBundle("/resources/Directions");

    default String getDefault(String key) {
        return myDefaults.getString(key);
    }
    default double getDefaultDouble(String key) {
        try {
            return Double.parseDouble(myDefaults.getString(key));
        } catch (Exception e) {
            new ErrorAlert(e);
            return 0;
        }
    }
}
