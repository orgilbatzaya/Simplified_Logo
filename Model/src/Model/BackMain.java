package Model;
import java.util.Scanner;

//Receives an input block of text and performs the corresponding commands

public class BackMain {

    public String myCommands;
    public String myLanguage;

    public BackMain (String commands, String language){
        myCommands = commands;
        myLanguage = language;
    }
    private String readFileToString (String filename) {
        final var END_OF_FILE = "\\z";
        var input = new Scanner(getClass().getResourceAsStream(filename));
        input.useDelimiter(END_OF_FILE);
        var result = input.next();
        input.close();
        return result;
    }

    // given some text, prints results of parsing it using the given language
    private void parseText (ProgramParser lang, String[] text) {
        for (var s : text) {
            if (s.trim().length() > 0) {
                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            }
        }
        System.out.println();
    }

}
