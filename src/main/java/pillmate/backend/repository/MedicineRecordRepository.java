package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.MedicineRecord;

import java.time.LocalDate;

public interface MedicineRecordRepository extends JpaRepository<MedicineRecord, Long> {
    @Query("SELECT COUNT(DISTINCT mr.date) FROM MedicineRecord mr " +
            "WHERE mr.member.id = :memberId " +
            "AND mr.date BETWEEN :startDate AND :endDate " +
            "AND mr.isEaten = TRUE " +
            "GROUP BY mr.date " +
            "HAVING COUNT(DISTINCT mr.medicine.id) = (SELECT COUNT(m.id) FROM Medicine m)")
    Integer countEatenDates(@Param("memberId") Long memberId,
                         @Param("startDate") LocalDate startDate,
                         @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(DISTINCT mr.date) " +
            "FROM MedicineRecord mr " +
            "WHERE mr.member.id = :memberId " +
            "AND mr.date BETWEEN :startDate AND :endDate " +
            "AND mr.isEaten = FALSE")
    Integer countUneatenDays(@Param("memberId") Long memberId,
                             @Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(mr) FROM MedicineRecord mr WHERE mr.member.id = :memberId AND mr.medicine.id = :medicineId AND mr.isEaten = true")
    Integer countByMemberIdAndMedicineIdAndIsEatenTrue(@Param("memberId") Long memberId, @Param("medicineId") Long medicineId);
}
