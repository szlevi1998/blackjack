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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BlackJackResult {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private int wins;

    private int losses;

    private ZonedDateTime date;

    @PrePersist
    protected void onPersist() {
        date = ZonedDateTime.now();
    }
}
