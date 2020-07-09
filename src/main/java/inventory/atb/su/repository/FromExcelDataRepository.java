package inventory.atb.su.repository;

import inventory.atb.su.models.FromExcelData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FromExcelDataRepository  extends JpaRepository<FromExcelData, Long> {
}
