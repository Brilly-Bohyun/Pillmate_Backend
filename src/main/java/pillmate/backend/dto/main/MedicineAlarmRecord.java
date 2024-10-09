package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class MedicineAlarmRecord {
    private Long MedicineId;
    private String name;
    private LocalTime time;
    private Boolean isEaten;
}
