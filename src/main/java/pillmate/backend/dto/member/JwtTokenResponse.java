package pillmate.backend.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pillmate.backend.common.util.JwtTokenConst;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class JwtTokenResponse {
    private String grantType;
    private String accessToken;

    public static JwtTokenResponse from(String accessToken) {
        return JwtTokenResponse.builder()
                .grantType(JwtTokenConst.TOKEN_PREFIX)
                .accessToken(accessToken)
                .build();
    }
}
