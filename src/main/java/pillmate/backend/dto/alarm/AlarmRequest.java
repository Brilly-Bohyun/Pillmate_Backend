package pillmate.backend.dto.alarm;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class AlarmRequest {
    private String medicineName;
    private LocalTime time;
}
