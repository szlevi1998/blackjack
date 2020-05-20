package game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BlackJack {
    @Getter
    List<String> hostCardList;
    @Getter
    List<String> playerCardList;

    Card card = new Card();

    private int numberOfPlayerAces = 0;
    private int numberOfHostAces = 0;

    public void createCardList() {
        playerCardList = null;
        hostCardList = null;
        hostCardList = this.getHostList();
        playerCardList = this.getPlayerList();
        numberOfPlayerAces = 0;
        numberOfHostAces = 0;
    }

    public List<String> getHostList() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            String recentCard = card.getRandomCard();
            if (!result.contains(recentCard)) {
                result.add(recentCard);
            } else {
                i--;
            }
        }
        return result;
    }

    public List<String> getPlayerList() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            String recentCard = card.getRandomCard();
            if (!result.contains(recentCard)) {
                result.add(recentCard);
            } else {
                i--;
            }
        }
        return result;
    }

    public String addPlayerCard() {

        String recentCard = null;

        for (int i = 0; i < 1; i++) {
            recentCard = card.getRandomCard();
            if (!playerCardList.contains(recentCard)) {
                playerCardList.add(recentCard);
            } else {
                i--;
            }

        }
        return recentCard;
    }

    public String addHostCard() {
        String recentCard = null;

        for (int i = 0; i < 1; i++) {
            recentCard = card.getRandomCard();
            if (!hostCardList.contains(recentCard)) {
                hostCardList.add(recentCard);
            } else {
                i--;
            }

        }
        return recentCard;
    }

    public String clearLetter(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        System.out.println(blackJack.clearLetter("AD"));
    }

    public int getPlayerCardValue() {
        int cardNum = 0;
        String value = null;
        numberOfPlayerAces = 0;
        for (String str : playerCardList) {
            value = clearLetter(str);

            if (value.matches("A")) {
                numberOfPlayerAces++;
                cardNum += 11;
                System.out.println("before" + numberOfPlayerAces);
            } else if (value.matches("K|Q|J")) {
                cardNum += 10;
            } else {
                cardNum += Integer.parseInt(value);
            }

        }

        while (cardNum > 21 && numberOfPlayerAces > 0) {
            numberOfPlayerAces--;
            cardNum -= 10;
            System.out.println("after" + numberOfPlayerAces);
        }
        return cardNum;
    }

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

}
