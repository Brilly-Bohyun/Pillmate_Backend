package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.Alarm;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    @Query("SELECT a FROM Alarm a " +
            "JOIN a.medicinePerMember mpm " +
            "JOIN mpm.member m " +
            "WHERE m.id = :memberId")
    List<Alarm> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT a FROM Alarm a " +
            "JOIN a.medicinePerMember mpm " +
            "JOIN mpm.member m " +
            "JOIN mpm.medicine med " +
            "WHERE m.id = :memberId " +
            "AND med.name = :medicineName")
    List<Alarm> findAllByMemberIdAndMedicineName(@Param("memberId") Long memberId,
                                                 @Param("medicineName") String medicineName);

    @Query("SELECT a FROM Alarm a " +
            "WHERE a.medicinePerMember.member.id = :memberId " +
            "AND a.medicinePerMember.medicine.id = :medicineId " +
            "AND a.timeSlot.pickerTime = :time")
    Optional<Alarm> findByMemberIdAndMedicineIdAndTime(@Param("memberId") Long memberId,
                                                       @Param("medicineId") Long medicineId,
                                                       @Param("time") LocalTime time);

    @Query("SELECT a FROM Alarm a WHERE a.medicinePerMember.member.id = :memberId AND a.timeSlot.pickerTime < :currentTime AND a.isAvailable = true AND a.isEaten = false")
    List<Alarm> findMissedAlarms(@Param("memberId") Long memberId, @Param("currentTime") LocalTime currentTime);

    @Modifying
    @Query("UPDATE Alarm a SET a.isEaten = false")
    void updateAllIsEatenToFalse();
}
