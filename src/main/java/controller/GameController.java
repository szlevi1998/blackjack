package controller;

import game.Cards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    private Cards cards;

    @FXML
    private ImageView background;

    @FXML
    private Pane field;

    public void drawGame(){
        background.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));
    }
    @FXML
    public void initialize(){
        drawGame();
    }
    public void finishGame(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topscores.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setX((Screen.getPrimary().getBounds().getWidth()/2)-350);
        stage.setY(0);
        stage.show();
    }
}
