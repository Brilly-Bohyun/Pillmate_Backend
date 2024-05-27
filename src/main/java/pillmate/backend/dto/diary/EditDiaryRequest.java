package pillmate.backend.dto.diary;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class EditDiaryRequest {
    private LocalDate date;

    private List<String> symptom = new ArrayList<>();

    private Integer score;

    private String record;
}
