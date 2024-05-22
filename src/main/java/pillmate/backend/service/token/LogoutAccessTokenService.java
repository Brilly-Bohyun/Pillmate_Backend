package pillmate.backend.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pillmate.backend.entity.token.LogoutAccessToken;
import pillmate.backend.repository.token.LogoutAccessTokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutAccessTokenService {
    private final LogoutAccessTokenRepository logoutAccessTokenRepository;

    public void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken) {
        logoutAccessTokenRepository.save(logoutAccessToken);
    }

    public boolean existsLogoutAccessTokenById(String accessToken) {
        return logoutAccessTokenRepository.existsById(accessToken);
    }
}
