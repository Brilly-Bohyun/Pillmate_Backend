package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class MissedAlarm {
    private String name;
    private LocalTime time;
}
