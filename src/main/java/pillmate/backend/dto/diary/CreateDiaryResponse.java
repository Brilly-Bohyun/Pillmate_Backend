package pillmate.backend.dto.diary;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateDiaryResponse {
    private Long diaryId;
}
