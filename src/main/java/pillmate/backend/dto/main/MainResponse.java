package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MainResponse {
    private List<WeekRateInfo> weekRateInfoList;
    private List<MedicineAlarmRecord> medicineAlarmRecords;
    private String grade;
    private Integer takenDay;
    private Integer month;
    private Integer rate;
    private BestRecord bestRecord;
    private WorstRecord worstRecord;
}
