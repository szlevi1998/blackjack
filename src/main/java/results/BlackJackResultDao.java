package results;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO class for the {@link BlackJackResult} entity.
 */
public class BlackJackResultDao extends GenericJpaDao<BlackJackResult> {

    private static BlackJackResultDao instance;

    private BlackJackResultDao() {
        super(BlackJackResult.class);
    }

    public static BlackJackResultDao getInstance() {
        if (instance == null) {
            instance = new BlackJackResultDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("jpa-persistence-unit-1").createEntityManager());
        }
        return instance;
    }

    /**
     * Returns the list of {@code n} best results with respect to the time
     * spent for solving the puzzle.
     *
     * @param n the maximum number of results to be returned
     * @return the list of {@code n} best results with respect to the time
     * spent for solving the puzzle
     */
    public List<BlackJackResult> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM BlackJackResult r ORDER BY r.date ASC", BlackJackResult.class)
                .setMaxResults(n)
                .getResultList();
    }

}


