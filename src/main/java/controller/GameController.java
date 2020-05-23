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

/**
 * Controller class of the game screen.
 */

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

    /**
     * The player enters a name and with {@code userName} and the method saves the name.
     *
     * @param userName the name of the player
     */
    public void initializeData(String userName) {
        this.userName = userName;
        currentUser.setText("Current user: " + this.userName);
    }

    private void drawBackground() {
        background.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));
    }

    /**
     * Initializes the game fxml file.
     */
    @FXML
    public void initialize() {

        blackJack = new BlackJack();
        blackJackResultDao = BlackJackResultDao.getInstance();
        drawBackground();
        hit.setDisable(true);
        stand.setDisable(true);

    }

    /**
     *When the player clicks on the start button, the first two cards appears to the player and the host.
     *If the player has a blackjack, then the player automatically wins.
     */
    public void setUpFirstRound() {

        resetGame();
        drawPreRoundState();
        setUpButtonsForCurrentRound();
        currentScore.setText("Current score: " + blackJack.getPlayerCardValue());

        if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == 1) {
            playerScenario();
            setUpButtonsForNextRound();
        }

    }

    private void resetGame() {
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
        log.info("Cards have been resetted.");
    }

    private void drawPreRoundState() {
        blackJack.createCardList();
        List<String> hostCardList = blackJack.getHostCardList();
        List<String> playerCardList = blackJack.getPlayerCardList();
        player1.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(0) + ".png").toExternalForm()));
        player2.setImage(new Image(getClass().getResource("/images/" + playerCardList.get(1) + ".png").toExternalForm()));
        backCard.setImage(new Image(getClass().getResource("/images/backCard.png").toExternalForm()));
        host2.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(1) + ".png").toExternalForm()));
        log.info("Created pre table for the next round of the game");
    }

    private void setUpButtonsForCurrentRound() {
        start.setDisable(true);
        hit.setDisable(false);
        stand.setDisable(false);
    }

    private void setUpButtonsForNextRound() {
        start.setDisable(false);
        hit.setDisable(true);
        stand.setDisable(true);
    }

    private void playerScenario() {
        state.setText("You have won!");
        numberOfWins++;
        log.info("Player has won.");
    }

    private void hostScenario() {
        state.setText("You have lost!");
        numberOfLosses++;
        log.info("Host has won.");
    }

    /**If a player presses the hit button it gives a random card to the player's hand.
     * If the player's cards sum is 21, then the players wins.
     *If the sum goes over 21, then the host wins.
     *
     */
    public void addNewPlayerCard() {
        ImageView view = (ImageView) player.getChildren().get(playerIndex);
        view.setImage(new Image(getClass().getResource("/images/" + blackJack.addPlayerCard() + ".png").toExternalForm()));
        ++playerIndex;
        currentScore.setText("Current score: " + blackJack.getPlayerCardValue());
        if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == 1) {
            playerScenario();
            setUpButtonsForNextRound();

        } else if (blackJack.checkStateOfGame(blackJack.getPlayerCardValue()) == -1) {
            hostScenario();
            setUpButtonsForNextRound();
        }
    }

    /**
     *When the player hits the stand button, the rounds end and decides who is the winner.
     *
     */
    public void endRound() {
        List<String> hostCardList = blackJack.getHostCardList();
        backCard.setImage(new Image(getClass().getResource("/images/" + hostCardList.get(0) + ".png").toExternalForm()));
        while (blackJack.getHostCardValue() < 17) {
            addHostCard();
        }
        hostScore.setText("Host Score: " + blackJack.getHostCardValue());
        if (blackJack.checkStateOfGame(blackJack.getHostCardValue()) == 1) {
            hostScenario();
        } else if (blackJack.checkStateOfGame(blackJack.getHostCardValue()) == -1) {
            playerScenario();
        } else {
            if (blackJack.compareValue()) {
                playerScenario();
            } else {
                hostScenario();
            }

        }
        setUpButtonsForNextRound();
    }

    private void addHostCard() {
        ImageView view = (ImageView) host.getChildren().get(hostIndex);
        view.setImage(new Image(getClass().getResource("/images/" + blackJack.addHostCard() + ".png").toExternalForm()));
        ++hostIndex;
        log.info("Card has been added");
    }


    private BlackJackResult getResult() {

        log.info("Game result has been created");

        BlackJackResult result = BlackJackResult.builder()
                .name(userName)
                .wins(numberOfWins)
                .losses(numberOfLosses)
                .build();
        return result;
    }


    /**
     * Loads the top scores when the player clicks on the exit button.
     *
     * @param actionEvent a click by the player
     * @throws IOException if {@code fxmlLoader} can't load fxml file
     */
    public void finishGame(ActionEvent actionEvent) throws IOException {
        blackJackResultDao.persist(getResult());

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topscores.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - 350);
        stage.setY(0);
        stage.show();
        log.info("Finished game, loading to top scores.");
    }

}
