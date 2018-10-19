package View;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class LanguageMenu extends DropdownMenu{
    private static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";

    private ResourceBundle myLanguage;

    public LanguageMenu(ArrayList<String> items) {
        super(items);
    }

    @Override
    public void processChoice(String choice) {
        myLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE+choice);
        System.out.println(myLanguage.getString("Forward"));
    }

    public ResourceBundle getLanguage() {
        return myLanguage;
    }
}
