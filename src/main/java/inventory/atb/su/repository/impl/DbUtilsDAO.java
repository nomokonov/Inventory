package inventory.atb.su.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DbUtilsDAO {
    @PersistenceContext
    EntityManager entityManager;

    public void sequencesRestart(){
        entityManager.createNativeQuery("ALTER SEQUENCE fromexcel_seq_id RESTART").executeUpdate();
        entityManager.createNativeQuery("ALTER SEQUENCE movings_seq_id RESTART").executeUpdate();
    }
}
