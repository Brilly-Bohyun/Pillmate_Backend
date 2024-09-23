package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pillmate.backend.entity.Alarm;

import java.time.LocalTime;
import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findAllByMemberId(Long memberId);
    List<Alarm> findAllByTime(LocalTime currentTime);
    @Query("SELECT a FROM Alarm a " +
            "WHERE a.member.id = :memberId " +
            "AND a.isAvailable = true " +
            "ORDER BY CASE WHEN a.time > :currentTime THEN 0 ELSE 1 END, a.time ASC")
    List<Alarm> findUpcomingAlarmsByMemberId(@Param("memberId") Long memberId, @Param("currentTime") LocalTime currentTime);

}
