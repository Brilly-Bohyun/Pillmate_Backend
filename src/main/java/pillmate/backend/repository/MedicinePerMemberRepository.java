package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.MedicinePerMember;

import java.util.List;

public interface MedicinePerMemberRepository extends JpaRepository<MedicinePerMember, Long> {
    @Query("SELECT mpm FROM MedicinePerMember mpm WHERE mpm.member.id = :memberId AND mpm.medicine.id = :medicineId")
    MedicinePerMember findByMemberIdAndMedicineId(@Param("memberId") Long memberId, @Param("medicineId") Long medicineId);

    @Query("SELECT mpm FROM MedicinePerMember mpm WHERE mpm.member.id = :memberId")
    List<MedicinePerMember> findAllByMemberId(@Param("memberId") Long memberId);
}
