package game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the rules of the game.
 */

public class BlackJack {
    @Getter
    @Setter
    List<String> hostCardList = new ArrayList<>();

    @Getter
    @Setter
    List<String> playerCardList = new ArrayList<>();


    Card card = new Card();

    private int numberOfPlayerAces = 0;
    private int numberOfHostAces = 0;

    /**
     * Setting up the first two cards for the host and the player.
     */
    public void createCardList() {
        playerCardList = null;
        hostCardList = null;
        hostCardList = this.getFirstTwoCards();
        playerCardList = this.getFirstTwoCards();
        numberOfPlayerAces = 0;
        numberOfHostAces = 0;
    }

    private List<String> getFirstTwoCards() {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < 2) {
            String recentCard = card.getRandomCard();
            if (!result.contains(recentCard)) {
                result.add(recentCard);
                i++;
            }
        }
        return result;

    }

    /**
     * Adds a card to the player's hand and returns the added card.
     *
     * @return a String which contains the added card
     */
    public String addPlayerCard() {

        String recentCard = null;

        int i = 0;
        while (i == 0) {
            recentCard = card.getRandomCard();
            if (!playerCardList.contains(recentCard)) {
                playerCardList.add(recentCard);
                i++;
            }

        }
        return recentCard;
    }

    /**
     * Adds a card to the host's hand and returns the added card.
     *
     * @return a string which contains the added card
     */
    public String addHostCard() {
        String recentCard = null;
        int i = 0;
        while (i == 0) {
            recentCard = card.getRandomCard();
            if (!hostCardList.contains(recentCard)) {
                hostCardList.add(recentCard);
                i++;
            }
        }
        return recentCard;
    }

    private String clearLetter(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * Draws a card and increases the sum value of the player's hand.
     *
     * @return an integer variable which is the player's card value
     */
    public int getPlayerCardValue() {
        int cardNum = 0;
        String value = null;
        numberOfPlayerAces = 0;
        for (String str : playerCardList) {
            value = clearLetter(str);

            if (value.matches("A")) {
                numberOfPlayerAces++;
                cardNum += 11;
            } else if (value.matches("K|Q|J")) {
                cardNum += 10;
            } else {
                cardNum += Integer.parseInt(value);
            }

        }

        while (cardNum > 21 && numberOfPlayerAces > 0) {
            numberOfPlayerAces--;
            cardNum -= 10;
        }
        return cardNum;
    }

    /**
     * Draws a card and increases the sum value of the host's hand.
     *
     * @return an integer variable which is the host card value
     */
    public int getHostCardValue() {
        int cardNum = 0;
        String value = null;
        numberOfHostAces = 0;
        for (String str : hostCardList) {
            value = clearLetter(str);

            if (value.matches("A")) {
                numberOfHostAces++;
                cardNum += 11;
            } else if (value.matches("K|Q|J")) {
                cardNum += 10;
            } else {
                cardNum += Integer.parseInt(value);
            }

        }

        while (cardNum > 21 && numberOfHostAces > 0) {
            numberOfHostAces--;
            cardNum -= 10;
        }
        return cardNum;
    }

    /**
     * Checks the state of the game. If the value of the cards are 21, the player wins.
     * If the value is over 21, then the player lost.
     * If the value is under 21 then the round proceeds.
     *
     * @param value the sum of the cards in the player's or host's hand
     * @return {@code 1 } if the value equals 21, {@code -1} if the value is over 21, {@code 0} otherwise
     */
    public int checkStateOfGame(int value) {

        if (value == 21) {
            return 1;
        } else if (value > 21) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Compares the value between the host and the player .
     *
     * @return {@code true} if the players card are higher then the host, {@code false} otherwise.
     */
    public boolean compareValue() {
        return getPlayerCardValue() > getHostCardValue();
    }

}
