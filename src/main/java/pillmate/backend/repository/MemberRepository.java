package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
