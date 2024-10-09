package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.alarm.AlarmInfo;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.MedicinePerMember;
import pillmate.backend.repository.AlarmRepository;
import pillmate.backend.repository.MedicinePerMemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MedicinePerMemberRepository medicinePerMemberRepository;

    public List<AlarmInfo> showAll(Long memberId) {
        List<Alarm> alarms = alarmRepository.findAllByMemberId(memberId);
        return alarms.stream()
                .flatMap(alarm -> {
                    MedicinePerMember medicineHistory = findByMemberIdAndMedicineId(memberId, alarm.getMedicinePerMember().getMedicine().getId());

                    // MedicineHistory에서 모든 timeSlots를 AlarmInfo로 변환
                    return medicineHistory.getTimeSlots().stream()
                            .map(timeSlot -> AlarmInfo.builder()
                                    .id(alarm.getId())
                                    .name(alarm.getMedicinePerMember().getMedicine().getName())
                                    .category(alarm.getMedicinePerMember().getMedicine().getCategory())
                                    .amount(medicineHistory.getAmount())
                                    .timesPerDay(medicineHistory.getTimes())
                                    .day(medicineHistory.getDay())
                                    .timeSlot(timeSlot) // 여러 timeSlot 중 하나씩 AlarmInfo에 추가
                                    .isAvailable(alarm.getIsAvailable())
                                    .build());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<String> updateAvailability(Long alarmId, Boolean available, Long memberId) {
        findByAlarmId(alarmId).updateAvailability(available);
        return ResponseEntity.ok("알람 on/off 설정이 변경되었습니다.");
    }

    @Transactional
    public void deleteAlarm(Long memberId, String medicineName) {
        List<Alarm> alarmList = findByMemberIdAndMedicineName(memberId, medicineName);
        alarmList.forEach(alarm -> alarmRepository.deleteById(alarm.getId()));
    }

    @Transactional
    public void resetAllIsEaten() {
        alarmRepository.updateAllIsEatenToFalse();
    }

    private MedicinePerMember findByMemberIdAndMedicineId(Long memberId, Long medicineId) {
        return medicinePerMemberRepository.findByMemberIdAndMedicineId(memberId, medicineId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE_MEMBER));
    }

    private Alarm findByAlarmId(Long alarmId) {
        return alarmRepository.findById(alarmId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ALARM));
    }

    private List<Alarm> findByMemberIdAndMedicineName(Long memberId, String medicineName) {
        return alarmRepository.findAllByMemberIdAndMedicineName(memberId, medicineName);
    }
}
