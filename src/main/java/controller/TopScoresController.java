package controller;

import game.BlackJack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import results.BlackJackResult;
import results.BlackJackResultDao;


import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller Class representing the TopScores list.
 */
@Slf4j
public class TopScoresController {

    @FXML
    private TableView<BlackJackResult> topScoresTable;
    @FXML
    private TableColumn<BlackJackResult, String> name;

    @FXML
    private TableColumn<BlackJackResult, Integer> wins;

    @FXML
    private TableColumn<BlackJackResult, Integer> losses;

    @FXML
    private TableColumn<BlackJackResult, ZonedDateTime> date;

    private BlackJackResultDao blackJackResultDao;

    /**
     * Loads back the to the start of the program when a player clicks on the main menu .
     *
     * @param actionEvent a click by a player
     * @throws IOException if{@code FXMLLoader} can't load fxml file
     */
    public void back(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - 300);
        stage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - 250);
        stage.show();
        log.info("Loading launch scene.");
    }

    /**
     * Initiates the topScores fxml file.
     */
    @FXML
    public void initialize() {
        blackJackResultDao = BlackJackResultDao.getInstance();

        List<BlackJackResult> topScores = blackJackResultDao.findBest(10);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        date.setCellFactory(column -> {
            TableCell<BlackJackResult, ZonedDateTime> cell = new TableCell<BlackJackResult, ZonedDateTime>() {
                private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                @Override
                protected void updateItem(ZonedDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item.format(formatter));
                    }
                }
            };

            return cell;
        });
        ObservableList<BlackJackResult> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(topScores);

        topScoresTable.setItems(observableResult);

    }

}

