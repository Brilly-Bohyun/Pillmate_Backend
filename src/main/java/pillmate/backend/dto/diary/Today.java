package pillmate.backend.dto.diary;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Today {
    private List<String> symptoms;
    private Integer score;
    private String comment;
    private String record;
}
