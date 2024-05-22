package pillmate.backend.dto.member;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindPasswordResponse {
    private String tempPassword;
}
