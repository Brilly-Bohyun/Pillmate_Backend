package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
