package game;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class BlackJackTest {

    private BlackJack underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BlackJack();
    }

    @Test
   public void testGetPlayerCardValue() {
       List<String> list = new ArrayList<>();
        list.add("AS");
        list.add("5H");

        underTest.setPlayerCardList(list);
        Assertions.assertEquals(16, underTest.getPlayerCardValue());
        list.add("8S");
        Assertions.assertEquals(14, underTest.getPlayerCardValue());
        list.add("QH");
        Assertions.assertEquals(24, underTest.getPlayerCardValue());

    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }
}
