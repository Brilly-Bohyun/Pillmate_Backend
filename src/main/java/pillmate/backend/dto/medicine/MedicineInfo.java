package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.TimeSlot;

import java.util.List;

@Builder
@Data
public class MedicineInfo {
    private Long id;
    private String picture;
    private String name;
    private String category;
    private Integer amount;
    private Integer timesPerDay;
    private Integer day;
    private List<TimeSlot> timeSlotList;
}
