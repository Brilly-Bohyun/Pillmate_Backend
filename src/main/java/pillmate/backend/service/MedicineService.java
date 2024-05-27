package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.dto.medicine.UpcomingAlarm;
import pillmate.backend.entity.Alarm;
import pillmate.backend.repository.MedicineRepository;

import java.time.LocalTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public UpcomingAlarm getUpcomingAlarm(Long memberId) {
        Alarm alarm = medicineRepository.findUpcomingAlarmsByMemberId(memberId, LocalTime.now()).stream().findFirst().orElse(null);
        return UpcomingAlarm.builder().medicineName(alarm.getMedicine().getName()).time(alarm.getTime()).build();
    }
}
