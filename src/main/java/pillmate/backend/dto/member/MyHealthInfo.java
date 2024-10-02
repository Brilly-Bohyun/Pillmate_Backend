package pillmate.backend.dto.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.member.Disease;

import java.util.List;

@Builder
@Data
public class MyHealthInfo {
    private List<Disease> diseases;
    private List<String> symptoms;
}
