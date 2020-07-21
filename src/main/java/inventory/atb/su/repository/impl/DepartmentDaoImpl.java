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
                        " GROUP BY codedepartment, namedepartment " +
                        " ORDER BY namedepartment;"
                , Tuple.class)
                .getResultList();
        for (Tuple tuple : tupleDepartmentDTO) {
            String codeDepartment = tuple.get("codedepartment") == null ? "" : (String) tuple.get("codedepartment");
            String nameDepartment = tuple.get("namedepartment") == null ? "" : (String) tuple.get("namedepartment");
            result.add(new DepartmentDTO( codeDepartment, nameDepartment));
        }
        return result;
    }

    public DepartmentDTO getDepartmentByCode( String codeDepartmentP){
        List<Tuple> resultList = entityManager.createNativeQuery(
                "SELECT codedepartment, namedepartment " +
                        "FROM public.fromexcel " +
                        " WHERE codedepartment='" + codeDepartmentP + "'" +
                        " GROUP BY codedepartment, namedepartment;"
                , Tuple.class)
                .getResultList();
    if (resultList.isEmpty()){
        return null;
    }
        String codeDepartment = resultList.get(0).get("codedepartment") == null ? "" : (String) resultList.get(0).get("codedepartment");
        String nameDepartment = resultList.get(0).get("namedepartment") == null ? "" : (String) resultList.get(0).get("namedepartment");
        return new DepartmentDTO(codeDepartment,nameDepartment);
    }
}
