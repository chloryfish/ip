package Crumb.Gui;

import java.io.IOException;

import Crumb.Task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a task item
 * Child of DialogBox
 */
public class TaskItem extends HBox {

    @FXML
    private HBox leftBar;

    @FXML
    private Label indexLabel;

    @FXML
    private Text taskName;

    @FXML
    private Label taskDetails;

    private TaskItem(Integer index, String tName, String tDetails, Boolean isDone) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/views/TaskItem.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        indexLabel.setText(String.valueOf(index));
        taskName.setText(tName);
        taskDetails.setText(tDetails);

        if (isDone) {
            leftBar.getStyleClass().add("leftBar-done");
            indexLabel.getStyleClass().add("indexLabel-done");
            taskName.getStyleClass().add("taskNameText-done");
            taskName.setStrikethrough(true);
            taskDetails.getStyleClass().add("taskSubtext-done");
            this.getStyleClass().add("grey-border");
        }
    }

    public static TaskItem getTaskItem(Task t, Integer index) {
        return new TaskItem(index, t.getDescription(), t.getDetails(), t.getIsDone());
    }
}