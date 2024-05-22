package pillmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pillmate.backend.entity.member.Member;
import pillmate.backend.entity.member.MemberType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndType(String email, MemberType type);
    Optional<Member> findByProviderIdAndType(Long providerId, MemberType type);
    boolean existsByProviderIdAndType(Long providerId, MemberType type);
    boolean existsByEmailAndType(String email, MemberType type);
}
