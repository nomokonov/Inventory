package inventory.atb.su.repository.impl;

import inventory.atb.su.models.dto.DepartmentDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDaoImpl {

    @PersistenceContext
    EntityManager entityManager;

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentDTO> result = new ArrayList<>();

        List<Tuple> tupleDepartmentDTO = entityManager.createNativeQuery(
                "SELECT codedepartment, namedepartment " +
                        "FROM public.fromexcel " +
                        "" +
                        "GROUP BY codedepartment, namedepartment;"

//                "SELECT u.user_id AS id, u.first_name AS firstName, u.last_name AS lastName FROM user u " +
//                        "LEFT JOIN permissions p ON p.user_id = u.user_id " +
//                        "LEFT JOIN role r ON p.role_id = r.id " +
//                        "WHERE r.role_name = 'MENTOR';"
                , Tuple.class)
                .getResultList();
        for (Tuple tuple : tupleDepartmentDTO) {
            String codeDepartment = tuple.get("codedepartment") == null ? "" : (String) tuple.get("codedepartment");
            String nameDepartment = tuple.get("namedepartment") == null ? "" : (String) tuple.get("namedepartment");
            result.add(new DepartmentDTO( codeDepartment, nameDepartment));
        }
        return result;
    }
}
