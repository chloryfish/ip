package Crumb.Gui;

import Crumb.Crumb;
import Crumb.UiString;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI.
 */

public class ChatWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Crumb crumb;

    private Image crumbImage = new Image(this.getClass().getResourceAsStream("/images/Crumb-line.png"));

    /**
     * Initialises the main chat window and adds System's welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSystemDialog(UiString.getWelcome(), crumbImage)
        );
    }

    /** Injects the instance */
    public void setCrumb(Crumb c) {
        crumb = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing System's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = crumb.getResponse(input);
        //System.out.println(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getSystemDialog(response, crumbImage)
        );
        userInput.clear();
    }
}
