package pillmate.backend.dto.member;

import lombok.Data;

@Data
public class ModifyPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
