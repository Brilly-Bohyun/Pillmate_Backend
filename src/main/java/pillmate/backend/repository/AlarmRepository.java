package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.MedicineRecord;

import java.time.LocalTime;
import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByMemberId(Long memberId);
    @Query("SELECT a FROM Alarm a WHERE a.time = :currentTime")
    List<Alarm> findByAlarmTime(@Param("currentTime") LocalTime currentTime);
}
