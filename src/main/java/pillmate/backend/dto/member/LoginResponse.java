package pillmate.backend.dto.member;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.member.Member;

@Builder
@Data
public class LoginResponse {
    private JwtTokenResponse tokenInfo;

    public static LoginResponse from(JwtTokenResponse tokenInfo, Member member) {
        return LoginResponse.builder()
                .tokenInfo(tokenInfo)
                .build();
    }
}
