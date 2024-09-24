package pillmate.backend.dto.alarm;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.entity.TimeSlot;

@Builder
@Data
public class AlarmInfo {
    private Long id;
    private String name;
    private String category;
    private Integer amount;
    private Integer timesPerDay;
    private Integer day;
    private TimeSlot timeSlot;
    private Boolean isAvailable;
}
