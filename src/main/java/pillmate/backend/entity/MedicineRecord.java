package pillmate.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pillmate.backend.entity.member.Member;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MedicineRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "medicine_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

    @Column(name = "date", nullable = false)
    @CreatedDate
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "isEaten", nullable = false)
    private Boolean isEaten;

    @Builder
    public MedicineRecord(Long id, Member member, Medicine medicine, LocalDate date, LocalTime time, Boolean isEaten) {
        this.id = id;
        this.member = member;
        this.medicine = medicine;
        this.date = date;
        this.time = time;
        this.isEaten = isEaten;
    }
}
