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

    // 투약일수 일
    @Column(name = "day", nullable = false)
    private Integer day;

    // 투여시간
    @Column(name = "time", nullable = false)
    private String time;

    @Builder
    public MedicinePerMember(Long id, Member member, Medicine medicine, Integer amount, Integer times, Integer day, String time) {
        this.id = id;
        this.member = member;
        this.medicine = medicine;
        this.amount = amount;
        this.times = times;
        this.day = day;
        this.time = time;
    }

    public void update(final Integer amount, final Integer times, final Integer month, final Integer date, final String time) {
        updateAmount(amount);
        updateTimes(times);
        updateDate(date);
        updateTime(time);
    }

    private void updateAmount(Integer amount) {
        if (amount != null) {
            this.amount = amount;
        }
    }

    private void updateTimes(Integer times) {
        if (times != null) {
            this.times = times;
        }
    }

    private void updateDate(Integer date) {
        if (date != null) {
            this.day = date;
        }
    }

    private void updateTime(String time) {
        if (time != null) {
            this.time = time;
        }
    }
}
