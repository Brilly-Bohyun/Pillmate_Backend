package pillmate.backend.repository.token;

import org.springframework.data.repository.CrudRepository;
import pillmate.backend.entity.token.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
