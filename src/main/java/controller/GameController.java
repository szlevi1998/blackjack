package controller;

import game.Cards;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameController {

    private Cards cards;

    @FXML
    private ImageView background;

    @FXML
    private Pane table;

    public void drawGame(){
        background.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));
    }

}
