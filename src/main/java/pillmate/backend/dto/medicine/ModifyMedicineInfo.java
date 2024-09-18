package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.TimeSlot;

import java.util.List;

@Builder
@Data
public class ModifyMedicineInfo {
    private String medicineName;
    private Integer amount;
    private Integer timesPerDay;
    private Integer day;
    private List<TimeSlot> timeSlotList;
}
