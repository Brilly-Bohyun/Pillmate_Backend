package pillmate.backend.dto.oauth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pillmate.backend.dto.member.JwtTokenResponse;
import pillmate.backend.entity.member.MemberType;

@Data
@Builder
public class OAuth2AuthenticateResponse {
    private boolean exist;
    private JwtTokenResponse jwtTokenResponse;
    private UserInfo userInfo;

    @Getter
    @Setter
    @Builder
    public static class UserInfo {
        Long providerId;
        String email;
        MemberType type;
    }
}