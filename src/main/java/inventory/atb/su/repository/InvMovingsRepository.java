package inventory.atb.su.repository;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.InvMovings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvMovingsRepository extends JpaRepository<InvMovings, Long> {

    Optional<InvMovings> deleteInvMovingsById(Long id);
}
