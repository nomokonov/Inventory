package inventory.atb.su.repository;

import inventory.atb.su.models.FromExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FromExcelDataRepository extends JpaRepository<FromExcelData, Long>, JpaSpecificationExecutor<FromExcelData> {

    Optional<FromExcelData> findByInvNumber(String invnumber);
}

