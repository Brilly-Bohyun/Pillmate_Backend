package pillmate.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pillmate.backend.entity.member.Member;

import java.util.ArrayList;
import java.util.List;

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

    // 투여시간대
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public void addTimeSlot(TimeSlot timeSlot) {
        if (timeSlot != null) {
            timeSlots.add(timeSlot);
        }
    }

    @Builder
    public MedicinePerMember(Long id, Member member, Medicine medicine, Integer amount, Integer times, Integer day) {
        this.id = id;
        this.member = member;
        this.medicine = medicine;
        this.amount = amount;
        this.times = times;
        this.day = day;
    }

    public void update(final Integer amount, final Integer times, final Integer day, final List<TimeSlot> timeSlots) {
        updateAmount(amount);
        updateTimes(times);
        updateDay(day);
        updateTimeSlots(timeSlots);
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

    private void updateDay(Integer day) {
        if (day != null) {
            this.day = day;
        }
    }

    private void updateTimeSlots(List<TimeSlot> timeSlots) {
        if (timeSlots != null) {
            this.timeSlots.clear();
            this.timeSlots.addAll(timeSlots);
        }
    }
}
