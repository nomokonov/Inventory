package inventory.atb.su.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MolDaoImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<String> getAllMols() {
        List<String> result = new ArrayList<>();

        List<String> resultList = entityManager.createNativeQuery(
                "SELECT mol FROM public.fromexcel  GROUP BY mol ORDER BY mol;")
                .getResultList();

        return resultList;
    }
}
