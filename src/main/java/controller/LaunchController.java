package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Controller class of the launch screen.
 */

@Slf4j
public class LaunchController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label errorLabel;

    /**
     * When the player clicks on the start button, the game starts.
     *
     * @param actionEvent a mouse click by a player
     * @throws IOException if{@code fxmlLoader} can't load the fxml file.
     */

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (userNameTextField.getText().isEmpty()) {
            errorLabel.setText("Username is empty, please type your Name!");
            log.error("UserNameTextField is empty");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().initializeData(userNameTextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            log.info("Username has been set to {}", userNameTextField.getText());
        }

    }

}
