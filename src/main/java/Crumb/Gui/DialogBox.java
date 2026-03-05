package Crumb.Gui;

import java.io.IOException;
import java.util.ArrayList;

import Crumb.Task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView crumbAvatar;

    @FXML
    private VBox responseBody;

    @FXML
    private VBox taskList;

    // For user dialog
    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatWindow.class.getResource("/views/UserDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    // For system dialog
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatWindow.class.getResource("/views/SystemDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        crumbAvatar.setImage(img);
    }

    // For system dialog that displays tasks
    private DialogBox(String text, Image img, ArrayList<Pair<Integer, Task>> tl) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatWindow.class.getResource("/views/SystemDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        crumbAvatar.setImage(img);
        for (int i = 0; i < tl.size(); i++) {
            taskList.getChildren().addAll(
                    TaskItem.getTaskItem(tl.get(i).getValue(), tl.get(i).getKey())
            );
        }
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text);
    }

    public static DialogBox getSystemDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getSystemDialog(String text, Image img, ArrayList<Pair<Integer, Task>> taskList) {
        return new DialogBox(text, img, taskList);
    }
}
