package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BestRecord {
    private String name;
    private Integer taken;
    private Integer scheduled;

    public static BestRecord from(AdherenceRate adherenceRate) {
        return BestRecord.builder()
                .name(adherenceRate.getMedicineName())
                .taken(adherenceRate.getTaken())
                .scheduled(adherenceRate.getScheduled())
                .build();
    }
}
