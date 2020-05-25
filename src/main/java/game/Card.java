package game;

import java.util.Random;

/**
 * Class representing the card creation.
 */
public class Card {

    private String[] suit = {"H", "D", "S", "C"};
    private String[] value = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private Random random = new Random();

    /**
     * A random card is being created. A card has a random suit and a random value.
     *
     * @return Returns a string pair of value and suit.
     */
    public String getRandomCard() {
        return value[random.nextInt(12)] + suit[random.nextInt(3)];
    }


}
