package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.Medicine;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);
}
