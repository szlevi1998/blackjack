package game;

import java.util.Random;

public class Card {

    private String[] suit = {"H","D","S","C"};
    private String[] value = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    Random random = new Random();

    public String getRandomCard(){
        return value[random.nextInt(12)]  + suit[random.nextInt(3)] ;
    }


}
