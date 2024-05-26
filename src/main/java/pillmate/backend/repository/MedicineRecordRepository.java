package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.MedicineRecord;

public interface MedicineRecordRepository extends JpaRepository<MedicineRecord, Long> {
}
