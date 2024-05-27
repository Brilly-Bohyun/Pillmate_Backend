package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicineInfo {
    private String picture;
    private String name;
    private String category;
    private Integer amount;
    private Integer timesPerDay;
    private Integer month;
    private Integer day;
    private String timeOfDay;
}
