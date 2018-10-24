package view.button;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.ErrorAlert;
import view.TurtleView;

import java.io.File;

public class ImageChooseButton extends SLogoButton{
    private static final String RESOURCE_PATH = "data/images/";
    private static final String IMAGE_PATH = "/images/";

    private TurtleView myImage;

    public ImageChooseButton(String label, TurtleView view) {
        super(label);
        myImage = view;
    }

    @Override
    public void processCommand() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File defaultFile = new File(RESOURCE_PATH);
        fileChooser.setInitialDirectory(defaultFile);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                myImage.setView(IMAGE_PATH + file.getName());
            } catch (Exception ex) {
                new ErrorAlert(ex);
            }
        }
    }
}
