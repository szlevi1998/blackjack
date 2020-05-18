package controller;

import game.BlackJack;
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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GameController {

    private int playerIndex = 2;
    private int hostIndex = 2;
    private BlackJack blackJack;

    private String userName;

    @FXML
    private Pane field;

    @FXML
    private ImageView background;
    @FXML
    private ImageView backCard;

    @FXML
    private ImageView player1;

    @FXML
    private ImageView player2;

    @FXML
    private ImageView player3;

    @FXML
    private ImageView player4;

    @FXML
    private ImageView player5;

    @FXML
    private ImageView player6;

    @FXML
    private ImageView host2;

    @FXML
    private ImageView host3;

    @FXML
    private ImageView host4;

    @FXML
    private ImageView host5;

    @FXML
    private ImageView host6;



    public void initializeData(String userName) {
        this.userName = userName;

    }

    public void drawGame() {
        background.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));
    }


    public void drawCards() {
        List<String> hostCardList = blackJack.getHostCardList();
        List<String> playerCardList = blackJack.getPlayerCardList();
        player1.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(0) + ".png").toExternalForm()));
        player2.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(1) + ".png").toExternalForm()));
        backCard.setImage(new Image(getClass().getResource("/images/backCard.png").toExternalForm()));
        host2.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(1) + ".png").toExternalForm()));
    }

    public void addNewPlayerCard(){
        ImageView view = (ImageView) field.getChildren().get(playerIndex);
        view.setImage(new Image(getClass().getResource("/images/"+ blackJack.addPlayerCard()+ ".png").toExternalForm()));
        playerIndex++;
    }
    public void addHostCard(){
        ImageView view = (ImageView) field.getChildren().get(hostIndex);
        view.setImage(new Image(getClass().getResource("/images/"+ blackJack.addHostCard()+ ".png").toExternalForm()));
        hostIndex++;
    }

    public void endTurn(){
        List<String> hostCardList = blackJack.getHostCardList();
        backCard.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(0) + ".png").toExternalForm()));
    }

    @FXML
    public void initialize() {

        blackJack = new BlackJack();
        drawGame();
        drawCards();
    }


    public void finishGame(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topscores.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - 350);
        stage.setY(0);
        stage.show();
    }



}
