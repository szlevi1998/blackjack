package game;

import lombok.Getter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
@Getter
public class BlackJack {
    @Getter
    List<String> hostCardList;
    @Getter
    List<String> playerCardList;

    Card card = new Card();

    public BlackJack(){
        hostCardList = this.getHostList();
        playerCardList = this.getPlayerList();
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

    public String addPlayerCard(){
        String result;

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

    public String addHostCard(){
        String result;

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



}
