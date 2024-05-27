package pillmate.backend.dto.diary;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.Diary;
import pillmate.backend.entity.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateDiaryRequest {
    private LocalDate date;

    private List<String> symptom = new ArrayList<>();

    private Integer score;

    private String record;

    public Diary toEntity(Member member) {
        return Diary.builder()
                .date(date)
                .member(member)
                .symptom(symptom)
                .score(score)
                .record(record)
                .build();
    }
}
