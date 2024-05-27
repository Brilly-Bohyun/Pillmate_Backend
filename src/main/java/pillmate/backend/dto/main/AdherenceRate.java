package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

@Data
public class AdherenceRate {
    private String medicineName;
    private Integer taken;
    private Integer scheduled;
    private Double rate;

    @Builder
    public AdherenceRate(String medicineName, Integer taken, Integer scheduled, Double rate) {
        this.medicineName = medicineName;
        this.taken = taken;
        this.scheduled = scheduled;
        this.rate = rate;
    }
}
