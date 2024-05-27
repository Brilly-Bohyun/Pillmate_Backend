package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModifyMedicineInfo {
    private String medicineName;
    private Integer amount;
    private Integer timesPerDay;
    private Integer month;
    private Integer day;
    private String timeOfDay;
}
