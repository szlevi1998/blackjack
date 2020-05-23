package results;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.ZonedDateTime;

/**
 * Class representing the result of a game by the player.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BlackJackResult {

    @Id
    @GeneratedValue()
    private Long id;

    /**
     * The name that the user sets.
     */

    private String name;

    /**
     *The amount of wins you had in one round of game.
     */

    private int wins;

    /**
     *The amount of losses you had in one round of game.
     */

    private int losses;
    /**
     * The exact timestamp when the result has been saved.
     */

    private ZonedDateTime date;

    @PrePersist
    protected void onPersist() {
        date = ZonedDateTime.now();
    }
}
