package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorstRecord {
    private String name;
    private Integer taken;
    private Integer scheduled;

    public static WorstRecord from(AdherenceRate adherenceRate) {
        return WorstRecord.builder()
                .name(adherenceRate.getMedicineName())
                .taken(adherenceRate.getTaken())
                .scheduled(adherenceRate.getScheduled())
                .build();
    }
}
