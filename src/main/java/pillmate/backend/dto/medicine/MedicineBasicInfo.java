package pillmate.backend.dto.medicine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicineBasicInfo {
    private String name;
    private String photo;
    private String category;
}
