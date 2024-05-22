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
import lombok.Getter;
import lombok.NoArgsConstructor;
import pillmate.backend.entity.member.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicinePerMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "medicine_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    // 투여횟수 회
    @Column(name = "times", nullable = false)
    private Integer times;

    // 투약일수 개월
    @Column(name = "month", nullable = false)
    private Integer month;

    // 투약일수 일
    @Column(name = "date", nullable = false)
    private Integer date;

    // 투여시간
    @Column(name = "time", nullable = false)
    private Integer time;
}
