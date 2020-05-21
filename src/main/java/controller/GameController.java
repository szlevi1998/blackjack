package controller;

import game.BlackJack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import results.BlackJackResult;
import results.BlackJackResultDao;

import java.io.IOException;

import java.util.List;

@Slf4j
public class GameController {

    private int playerIndex = 2;
    private int hostIndex = 2;
    private BlackJack blackJack;
    private String userName;
    private int numberOfWins;
    private int numberOfLosses;

    private BlackJackResultDao blackJackResultDao;

    @FXML
    private Label currentUser;

    @FXML
    private Label currentScore;

    @FXML
    private Label hostScore;

    @FXML
    private Label state;

    @FXML
    private Button start;

    @FXML
    private Button hit;

    @FXML
    private Button stand;
    @FXML
    private Pane player;

    @FXML
    private Pane host;

    @FXML
    private ImageView background;
    @FXML
    private ImageView backCard;

    @FXML
    private ImageView player1;

    @FXML
    private ImageView player2;


    @FXML
    private ImageView host2;


    public void initializeData(String userName) {
        this.userName = userName;
        currentUser.setText("Current user: " + this.userName);
    }

    public void drawGame() {
        background.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));
    }

    public void resetGame(){
        ImageView view;
        for (int i = 0; i < player.getChildren().size(); i++) {
            view = (ImageView) player.getChildren().get(i);
            view.setImage(null);
            view = (ImageView) host.getChildren().get(i);
            view.setImage(null);
        }
        hostIndex = 2;
        playerIndex = 2;
        state.setText(null);
        hostScore.setText(null);

    }


    public void drawCards() {
        blackJack.createCardList();
        List<String> hostCardList = blackJack.getHostCardList();
        List<String> playerCardList = blackJack.getPlayerCardList();

        resetGame();

        player1.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(0) + ".png").toExternalForm()));
        player2.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(1) + ".png").toExternalForm()));
        backCard.setImage(new Image(getClass().getResource("/images/backCard.png").toExternalForm()));
        host2.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(1) + ".png").toExternalForm()));
        start.setDisable(true);
        hit.setDisable(false);
        stand.setDisable(false);
        currentScore.setText("Current score: " + blackJack.getPlayerCardValue());
        if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == 1) {
            state.setText("You have won!");
            numberOfWins++;
            start.setDisable(false);
            hit.setDisable(true);
            stand.setDisable(true);
        } else if ((blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == -1)) {
            state.setText("You have lost!");
            numberOfLosses++;
            start.setDisable(false);
            hit.setDisable(true);
            stand.setDisable(true);
        }

    }

    public void addNewPlayerCard() {
        ImageView view = (ImageView) player.getChildren().get(playerIndex);
        view.setImage(new Image(getClass().getResource("/images/" + blackJack.addPlayerCard() + ".png").toExternalForm()));
        ++playerIndex;
        currentScore.setText("Current score: " + blackJack.getPlayerCardValue());
        if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == 1) {
            state.setText("You have won!");
            numberOfWins++;
            start.setDisable(false);
            hit.setDisable(true);
            stand.setDisable(true);
        } else if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == -1) {
            state.setText("You have lost!");
            numberOfLosses++;
            start.setDisable(false);
            hit.setDisable(true);
            stand.setDisable(true);
        }
    }

    public void addHostCard() {
        ImageView view = (ImageView) host.getChildren().get(hostIndex);
        view.setImage(new Image(getClass().getResource("/images/" + blackJack.addHostCard() + ".png").toExternalForm()));
        ++hostIndex;

    }

    public void endTurn() {
        List<String> hostCardList = blackJack.getHostCardList();
        backCard.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(0) + ".png").toExternalForm()));
        while (blackJack.getHostCardValue() < 17) {
            addHostCard();
        }
        hostScore.setText("Host Score: " + blackJack.getHostCardValue());
        if (blackJack.checkStateOfGame(blackJack.getHostCardValue()) == 1) {
            state.setText("You have lost!");
            numberOfLosses++;
        } else if (blackJack.checkStateOfGame(blackJack.getHostCardValue()) == -1) {
            state.setText("You have won!");
            numberOfWins++;
        } else {
            if (blackJack.compareValue()) {
                state.setText("You have won!");
                numberOfWins++;
            } else {
                state.setText("You have lost!");
                numberOfLosses++;
            }

        }
        start.setDisable(false);
        hit.setDisable(true);
        stand.setDisable(true);
    }

    @FXML
    public void initialize() {

        blackJack = new BlackJack();
        blackJackResultDao = BlackJackResultDao.getInstance();
        drawGame();
        hit.setDisable(true);
        stand.setDisable(true);

    }

    private BlackJackResult getResult() {

        BlackJackResult result = BlackJackResult.builder()
                .name(userName)
                .wins(numberOfWins)
                .losses(numberOfLosses)
                .build();
        return result;
    }


    public void finishGame(ActionEvent actionEvent) throws IOException {
        blackJackResultDao.persist(getResult());

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topscores.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - 350);
        stage.setY(0);
        stage.show();
    }


}
