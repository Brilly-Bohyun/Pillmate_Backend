package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRecord extends JpaRepository<pillmate.backend.entity.MedicineRecord, Long> {
}
