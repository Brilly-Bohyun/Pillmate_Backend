package pillmate.backend.dto.member;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogoutResponse {
    Long memberId;
}
