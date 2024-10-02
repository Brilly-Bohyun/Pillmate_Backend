package pillmate.backend.dto.member;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.member.Disease;
import pillmate.backend.entity.member.Symptom;

import java.util.List;

@Builder
@Data
public class MyHealthInfo {
    private List<Disease> diseases;
    private List<Symptom> symptoms;
}
