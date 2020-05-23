package game;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class CardTest {

    private final Card underTest = new Card();

    @Test
    public void testGetRandomCard() {
        String testCard = underTest.getRandomCard();
        Assertions.assertTrue(testCard.matches("[AJQK234567891]0*[HDSC]"));

    }


}
