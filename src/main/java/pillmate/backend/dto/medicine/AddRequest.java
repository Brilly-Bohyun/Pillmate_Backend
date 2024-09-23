package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.MedicinePerMember;
import pillmate.backend.entity.TimeSlot;
import pillmate.backend.entity.member.Member;

import java.util.List;

@Builder
@Data
public class AddRequest {
    private String medicineName;
    private String disease;
    private Integer amount;
    private Integer timesPerDay;
    private Integer day;
    private List<TimeSlot> timeSlotList;

    public MedicinePerMember toEntity(Member member, Medicine medicine) {
        MedicinePerMember medicinePerMember = MedicinePerMember.builder()
                .member(member)
                .medicine(medicine)
                .amount(amount)
                .times(timesPerDay)
                .day(day)
                .build();

        timeSlotList.stream().map(
                t -> TimeSlot.builder()
                        .spinnerTime(t.getSpinnerTime())
                        .pickerTime(t.getPickerTime())
                        .build()
        ).forEach(medicinePerMember::addTimeSlot);

        return medicinePerMember;
    }
}
