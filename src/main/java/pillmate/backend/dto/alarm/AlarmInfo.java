package pillmate.backend.dto.alarm;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class AlarmInfo {
    private LocalTime time;
    private String medicineName;
    private String category;
    private Integer amount;
    private Integer timesPerDay;
    private Integer month;
    private Integer day;
    private Boolean isAvailable;
    private String timeOfDay;
}
