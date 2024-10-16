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
            "JOIN mpm.timeSlots ts " +
            "WHERE m.id = :memberId " +
            "AND (ts.pickerTime > :currentTime OR ts.pickerTime <= :currentTime) " +
            "AND a.isAvailable = TRUE " +
            "AND a.isEaten = FALSE " +
            "ORDER BY CASE WHEN ts.pickerTime > :currentTime THEN 0 ELSE 1 END, ts.pickerTime ASC")
    List<Alarm> findNextUpcomingAlarmsByMember(@Param("memberId") Long memberId,
                                               @Param("currentTime") LocalTime currentTime);

    @Query("SELECT a FROM Alarm a " +
            "JOIN a.medicinePerMember mpm " +
            "JOIN mpm.member m " +
            "JOIN mpm.medicine med " +
            "WHERE m.id = :memberId " +
            "AND med.name = :medicineName")
    List<Alarm> findAllByMemberIdAndMedicineName(@Param("memberId") Long memberId,
                                                 @Param("medicineName") String medicineName);

    @Query("SELECT a FROM Alarm a " +
            "JOIN a.medicinePerMember mp " +
            "JOIN mp.timeSlots ts " +
            "WHERE mp.medicine.id = :medicineId " +
            "AND ts.pickerTime = :time")
    Optional<Alarm> findByMedicineIdAndTime(@Param("medicineId") Long medicineId, @Param("time") LocalTime time);

    @Modifying
    @Query("UPDATE Alarm a SET a.isEaten = false")
    void updateAllIsEatenToFalse();
}
