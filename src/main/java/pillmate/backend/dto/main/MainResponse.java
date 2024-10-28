package pillmate.backend.dto.main;

import lombok.Builder;
import lombok.Data;
import pillmate.backend.dto.medicine.UpcomingAlarm;

import java.util.List;

@Builder
@Data
public class MainResponse {
    private UpcomingAlarm upcomingAlarm;
    private List<MissedAlarm> missedAlarms;
    private List<MedicineAlarmRecord> medicineAlarmRecords;
    private List<RemainingMedicine> remainingMedicine;
    private BestRecord bestRecord;
    private WorstRecord worstRecord;
}
