package pillmate.backend.dto.alarm;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.TimeSlot;

import java.util.List;

@Builder
@Data
public class AlarmInfo {
    private Long id;
    private String name;
    private String category;
    private Integer amount;
    private Integer timesPerDay;
    private Integer day;
    private List<TimeSlot> timeSlotList;
    private Boolean isAvailable;
}
