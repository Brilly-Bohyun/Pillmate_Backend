package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class WeekRateInfo {
    private LocalDate date;
    private Integer rate;
}
