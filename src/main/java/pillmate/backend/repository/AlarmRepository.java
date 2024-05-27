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
    @Query("SELECT a FROM Alarm a WHERE a.member.id = :memberId AND a.time > :currentTime AND a.isAvailable = true ORDER BY a.time ASC")
    List<Alarm> findUpcomingAlarmsByMemberId(@Param("memberId") Long memberId, @Param("currentTime") LocalTime currentTime);

}
