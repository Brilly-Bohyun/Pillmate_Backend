package pillmate.backend.dto.oauth;

import lombok.Data;

@Data
public class OAuth2TokenResponse {
    private String access_token;
    private String refresh_token;
    private Long expires_in;
}
