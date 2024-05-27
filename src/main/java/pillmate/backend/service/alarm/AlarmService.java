package pillmate.backend.service.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.alarm.AlarmRequest;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.member.Member;
import pillmate.backend.repository.AlarmRepository;
import pillmate.backend.repository.MedicineRepository;
import pillmate.backend.repository.MemberRepository;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final MedicineRepository medicineRepository;
    private final FCMService fcmService;

    @Transactional
    public ResponseEntity<String> createAlarm(final Long memberId, final AlarmRequest alarmRequest) {
        Alarm alarm = Alarm.builder()
                .member(findByMemberId(memberId))
                .medicine(findByMedicineName(alarmRequest.getMedicineName()))
                .time(alarmRequest.getTime())
                .build();
        alarmRepository.save(alarm);
        return ResponseEntity.ok("알람이 생성되었습니다.");
    }

    @Scheduled(fixedRate = 60000) // 매 60초마다 실행
    public void checkAlarms() {
        List<Alarm> dueAlarms = getAllDueAlarms();
        for (Alarm alarm : dueAlarms) {
            triggerAlarm(alarm);
        }
    }

    private List<Alarm> getAllDueAlarms() {
        LocalTime currentTime = LocalTime.now();
        return alarmRepository.findByAlarmTime(currentTime);
    }

    private void triggerAlarm(Alarm alarm) {
        Medicine medicine = alarm.getMedicine();
        if (medicine != null) {
            fcmService.sendNotification(alarm.getMember().getId(), medicine);
        }
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Medicine findByMedicineName(String name) {
        return medicineRepository.findByName(name).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE));
    }

}
