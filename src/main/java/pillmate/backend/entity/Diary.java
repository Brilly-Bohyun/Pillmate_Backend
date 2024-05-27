package pillmate.backend.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pillmate.backend.entity.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "diary_symptoms", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "symptom", nullable = false)
    private List<String> symptom = new ArrayList<>();

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "record", nullable = true)
    private String record;

    @Builder
    public Diary(Long id, Member member, LocalDate date, List<String> symptom, Integer score, String record) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.symptom = symptom;
        this.score = score;
        this.record = record;
    }

    public void update(final List<String> symptom, final Integer score, final String record) {
        updateSymptom(symptom);
        updateScore(score);
        updateRecord(record);
    }

    private void updateSymptom(List<String> symptom) {
        if (symptom != null) {
            this.symptom = symptom;
        }
    }

    private void updateScore(Integer score) {
        if (score != null) {
            this.score = score;
        }
    }

    private void updateRecord(String record) {
        if (record != null) {
            this.record = record;
        }
    }
}
