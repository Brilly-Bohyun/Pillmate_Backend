package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.MedicinePerMember;
import pillmate.backend.entity.member.Member;

@Builder
@Data
public class AddDirectlyRequest {
    private String medicineName;
    private Integer amount;
    private Integer timesPerDay;
    private Integer month;
    private Integer day;
    private String timeOfDay;

    public MedicinePerMember toEntity(Member member, Medicine medicine) {
        return MedicinePerMember.builder()
                .member(member)
                .medicine(medicine)
                .amount(amount)
                .times(timesPerDay)
                .month(month)
                .date(day)
                .time(timeOfDay)
                .build();
    }
}
