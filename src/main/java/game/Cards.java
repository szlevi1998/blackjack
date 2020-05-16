package game;

    enum Value{
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private String NumberOfValue;

    Value(String NumberOfValue) {
        this.NumberOfValue = NumberOfValue;
    }
    public String GetShortValue(){
        return NumberOfValue;
    }
    public void setNumberOfValue(String NumberOfValue){
        this.NumberOfValue = NumberOfValue;
    }
}
    enum Suit{
    HEARTS("H"),
    DIAMONDS("D"),
    SPADES("S"),
    CLUBS("C");

    private  String ShortSuit;

        Suit(String ShortSuit) {
            this.ShortSuit = ShortSuit;
        }
     public String GetShortSuit(){
            return ShortSuit;
     }
     public void SetShortSuit(String ShortSuit){
            this.ShortSuit = ShortSuit;
     }
    }
public class Cards {

    private Suit suit;
    private Value value;

    public Cards(Value name, Suit suit) {
        this.value = name;
        this.suit = suit;
    }


    public Suit getSuit() {
        return suit;
    }

    public String toString() {

        return value.GetShortValue() + suit.GetShortSuit();
    }

    public void getValue() {
        int value = 0;


    }

}
