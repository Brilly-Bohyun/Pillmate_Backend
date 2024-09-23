package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.alarm.AlarmInfo;
import pillmate.backend.dto.alarm.AlarmRequest;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.MedicinePerMember;
import pillmate.backend.entity.member.Member;
import pillmate.backend.repository.AlarmRepository;
import pillmate.backend.repository.MedicinePerMemberRepository;
import pillmate.backend.repository.MedicineRepository;
import pillmate.backend.repository.MemberRepository;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final MedicinePerMemberRepository medicinePerMemberRepository;
    private final MedicineRepository medicineRepository;

    @Transactional
    public ResponseEntity<String> createAlarm(final Long memberId, final List<AlarmRequest> alarmRequest) {
        alarmRequest.stream().map(
                a -> Alarm.builder()
                        .member(findByMemberId(memberId))
                        .medicine(findByMedicineName(a.getMedicineName()))
                        .timeZone(a.getTimeZone())
                        .time(a.getTime())
                        .build()
        ).forEach(alarmRepository::save);

        return ResponseEntity.ok("알람이 생성되었습니다.");
    }

    public List<AlarmInfo> showAll(Long memberId) {
        List<Alarm> alarms = alarmRepository.findAllByMemberId(memberId);
        return alarms.stream()
                .sorted(Comparator.comparing(Alarm::getTime))
                .map(alarm -> {
                    MedicinePerMember medicineHistory = findByMemberIdAndMedicineId(memberId, alarm.getMedicine().getId());
                    return AlarmInfo.builder()
                            .id(alarm.getId())
                            .name(alarm.getMedicine().getName())
                            .category(alarm.getMedicine().getCategory())
                            .amount(medicineHistory.getAmount())
                            .timesPerDay(medicineHistory.getTimes())
                            .day(medicineHistory.getDay())
                            .timeSlotList(medicineHistory.getTimeSlots())
                            .isAvailable(alarm.getIsAvailable())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> updateAvailability(Long alarmId, Boolean available, Long memberId) {
        findByAlarmId(alarmId).updateAvailability(available);
        return ResponseEntity.ok("알람 on/off 설정이 변경되었습니다.");
    }

    private List<Alarm> getAllDueAlarms() {
        LocalTime currentTime = LocalTime.now();
        return alarmRepository.findAllByTime(currentTime);
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Medicine findByMedicineName(String name) {
        return medicineRepository.findByName(name).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE));
    }

    private MedicinePerMember findByMemberIdAndMedicineId(Long memberId, Long medicineId) {
        return medicinePerMemberRepository.findByMemberIdAndMedicineId(memberId, medicineId);
    }

    private Alarm findByAlarmId(Long alarmId) {
        return alarmRepository.findById(alarmId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ALARM));
    }
}
