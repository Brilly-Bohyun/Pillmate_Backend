package pillmate.backend.repository.token;

import org.springframework.data.repository.CrudRepository;
import pillmate.backend.entity.token.LogoutAccessToken;

public interface LogoutAccessTokenRepository extends CrudRepository<LogoutAccessToken, String> {
}
