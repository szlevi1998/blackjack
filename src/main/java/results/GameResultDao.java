package results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Builder
@Entity
public class GameResultDao {

    @Id
    @GeneratedValue()
    private long id;

    private String player1;

    private int balance;

    private ZonedDateTime date;

    @PrePersist
    protected void onPersist(){
       date =  ZonedDateTime.now();
    }
}
