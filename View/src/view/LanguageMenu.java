package view;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class LanguageMenu extends DropdownMenu{
    private static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_LANGUAGE_LIST = "resources/LanguageList";
    private static final String DEFAULT_LANGUAGE = "ENG";

    private ResourceBundle myLanguage;

    public LanguageMenu() {
        super(new ArrayList<>());
        ArrayList<String> languages = new ArrayList<>();
        ResourceBundle languageList = ResourceBundle.getBundle(DEFAULT_LANGUAGE_LIST);
        for(String key : languageList.keySet()) {
            languages.add(languageList.getString(key));
        }
        getChoiceBox().setItems(FXCollections.observableArrayList(languages));
        if(languageList.containsKey(DEFAULT_LANGUAGE)) {
            String defaultLang = languageList.getString(DEFAULT_LANGUAGE);
            getChoiceBox().setValue(defaultLang);
            myLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE+defaultLang);
        }
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
