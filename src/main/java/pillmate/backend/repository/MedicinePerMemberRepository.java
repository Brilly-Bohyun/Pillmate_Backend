package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.MedicinePerMember;

import java.util.List;

public interface MedicinePerMemberRepository extends JpaRepository<MedicinePerMember, Long> {
    List<MedicinePerMember> findAllByMemberId(@Param("memberId") Long memberId);
}
