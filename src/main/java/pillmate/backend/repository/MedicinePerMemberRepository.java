package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.MedicinePerMember;

public interface MedicinePerMemberRepository extends JpaRepository<MedicinePerMember, Long> {
}
