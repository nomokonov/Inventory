package inventory.atb.su.repository;

import inventory.atb.su.models.FromExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FromExcelDataRepository  extends JpaRepository<FromExcelData, Long>, JpaSpecificationExecutor<FromExcelData> {

//    List<FromExcelData> getAllByMOL(String mol);
}
