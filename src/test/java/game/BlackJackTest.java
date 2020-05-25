package game;


import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

public class BlackJackTest {

    private BlackJack underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BlackJack();
    }

    @Test
    public void testCreateCardList() {

        underTest.createCardList();
        Assertions.assertEquals(2, underTest.getHostCardList().size());
        Assertions.assertEquals(2, underTest.getPlayerCardList().size());
        for (String str : underTest.getHostCardList()
        ) {
            Assertions.assertTrue(str.matches("[AJQK234567891]0*[HDSC]"));
        }
        for (String str : underTest.getPlayerCardList()
        ) {
            Assertions.assertTrue(str.matches("[AJQK234567891]0*[HDSC]"));
        }

    }


    @Test
    public void testAddPlayerCard() {
        String addCard = underTest.addPlayerCard();
        Assertions.assertTrue(addCard.matches("[AJQK234567891]0*[HDSC]"));
        Assertions.assertTrue(underTest.getPlayerCardList().contains(addCard));
    }

    @Test
    public void testAddHostCard() {
        String addCard = underTest.addHostCard();
        Assertions.assertTrue(addCard.matches("[AJQK234567891]0*[HDSC]"));
        Assertions.assertTrue(underTest.getHostCardList().contains(addCard));
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

    @Test
    public void testGetHostCardValue() {
        List<String> list = new ArrayList<>();
        list.add("AH");
        list.add("9C");
        underTest.setHostCardList(list);
        Assertions.assertEquals(20, underTest.getHostCardValue());
        list.add("AH");
        Assertions.assertEquals(21, underTest.getHostCardValue());
        list.add("7S");
        Assertions.assertEquals(18, underTest.getHostCardValue());
        list.add("10C");
        Assertions.assertEquals(28, underTest.getHostCardValue());
    }

    @Test
    public void testCheckStateOfGame() {
        Assertions.assertEquals(1, underTest.checkStateOfGame(21));
        Assertions.assertEquals(-1, underTest.checkStateOfGame(23));
        Assertions.assertEquals(0, underTest.checkStateOfGame(18));

    }


    @Test
    public void testCompareValue() {
        List<String> player = new ArrayList<>();
        player.add("KH");
        player.add("9C");

        List<String> host = new ArrayList<>();
        host.add("6H");
        host.add("4C");

        underTest.setPlayerCardList(player);
        underTest.setHostCardList(host);

        Assertions.assertTrue(underTest.compareValue());
        host.add("AD");
        underTest.setHostCardList(host);
        Assertions.assertFalse(underTest.compareValue());

    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }
}
