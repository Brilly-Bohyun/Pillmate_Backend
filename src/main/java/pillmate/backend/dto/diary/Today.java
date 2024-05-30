package pillmate.backend.dto.diary;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Today {
    private List<String> symptoms;
    private String record;
}
