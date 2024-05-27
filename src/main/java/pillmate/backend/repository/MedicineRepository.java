package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.Medicine;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);
    @Query("SELECT a FROM Alarm a WHERE a.member.id = :memberId AND a.time > :currentTime AND a.isAvailable = true ORDER BY a.time ASC")
    List<Alarm> findUpcomingAlarmsByMemberId(@Param("memberId") Long memberId, @Param("currentTime") LocalTime currentTime);
}
