package pillmate.backend.dto.member;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class FindPasswordRequest {
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
}
