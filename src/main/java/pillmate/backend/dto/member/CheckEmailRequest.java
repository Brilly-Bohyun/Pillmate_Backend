package pillmate.backend.dto.member;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckEmailRequest {
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
}
