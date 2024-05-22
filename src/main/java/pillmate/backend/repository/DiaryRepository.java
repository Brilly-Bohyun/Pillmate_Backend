package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
