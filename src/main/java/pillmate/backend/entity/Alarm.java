package pillmate.backend.entity;

import jakarta.persistence.Column;
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

import java.time.LocalTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "medicinePerMember_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Builder.Default
    @Column(name = "isEaten", nullable = false)
    private Boolean isEaten = FALSE;

    @Builder.Default
    @Column(name = "isAvailable", nullable = false)
    private Boolean isAvailable = TRUE;

    @Builder
    public Alarm(Long id, Member member, Medicine medicine, LocalTime time, Boolean isEaten, Boolean isAvailable) {
        this.id = id;
        this.member = member;
        this.medicine = medicine;
        this.time = time;
        this.isEaten = isEaten;
        this.isAvailable = isAvailable;
    }
}
