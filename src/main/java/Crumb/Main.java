package Crumb;

import java.io.IOException;

import Crumb.Gui.ChatWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Crumb using FXML.
 */
public class Main extends Application {

    private Crumb crumb = new Crumb("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/ChatWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<ChatWindow>getController().setCrumb(crumb);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}