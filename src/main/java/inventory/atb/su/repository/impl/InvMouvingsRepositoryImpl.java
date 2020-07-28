package inventory.atb.su.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class InvMouvingsRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public int deleteById(Long id) {

        return entityManager.createNativeQuery(
                "DELETE  FROM public.movings  WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
