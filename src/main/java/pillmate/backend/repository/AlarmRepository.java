package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
