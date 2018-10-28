package view.dropdown;

import javafx.collections.FXCollections;
import view.ViewResourceBundles;
import view.dropdown.DropdownMenu;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class allows users to switch the language of the commands entered.
 * The constructor also generates the list of choices for languages, which are specified in the LanguageList.properties file in the resources package.
 * @author Austin Kao
 */

public class LanguageMenu extends DropdownMenu implements ViewResourceBundles {
    private static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_LANGUAGE = "ENG";

    private ResourceBundle language;

    public LanguageMenu(String label) {
        super(new ArrayList<>(), label);
        ArrayList<String> languages = new ArrayList<>();
        for(String key : myLanguages.keySet()) {
            languages.add(myLanguages.getString(key));
        }
        getChoiceBox().setItems(FXCollections.observableArrayList(languages));
        if(myLanguages.containsKey(DEFAULT_LANGUAGE)) {
            String defaultLang = myLanguages.getString(DEFAULT_LANGUAGE);
            getChoiceBox().setValue(defaultLang);
            language = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE+defaultLang);
        }
    }

    @Override
    public void processChoice(String choice) {
        language = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE+choice);    }

    public ResourceBundle getLanguage() {
        return language;
    }
}
