package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService {
    @Autowired
    private final AlarmRepository alarmRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MedicineRepository medicineRepository;

    public ResponseEntity<String> createAlarm(final Long memberId, final AlarmRequest alarmRequest) {
        Alarm alarm = Alarm.builder()
                .member(findByMemberId(memberId))
                .medicine(findByMedicineName(alarmRequest.getMedicineName()))
                .time(alarmRequest.getTime())
                .build();
        alarmRepository.save(alarm);
        return ResponseEntity.ok("알람이 생성되었습니다.");
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Medicine findByMedicineName(String name) {
        return medicineRepository.findByName(name).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE));
    }
}
