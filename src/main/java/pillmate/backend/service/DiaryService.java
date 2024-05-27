package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.diary.CreateDiaryRequest;
import pillmate.backend.dto.diary.CreateDiaryResponse;
import pillmate.backend.dto.diary.EditDiaryRequest;
import pillmate.backend.dto.diary.ShowDiaryResponse;
import pillmate.backend.entity.Diary;
import pillmate.backend.entity.member.Member;
import pillmate.backend.repository.DiaryRepository;
import pillmate.backend.repository.MemberRepository;

import java.time.LocalDate;

import static pillmate.backend.common.exception.errorcode.ErrorCode.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateDiaryResponse create(Long memberId, CreateDiaryRequest createDiaryRequest) {
        Diary diary = diaryRepository.save(createDiaryRequest.toEntity(findByMemberId(memberId)));
        return CreateDiaryResponse.builder().diaryId(diary.getId()).build();
    }

    @Transactional
    public void edit(Long diaryId, EditDiaryRequest editDiaryRequest) {
        Diary diary = findById(diaryId);
        diary.update(editDiaryRequest.getSymptom(), editDiaryRequest.getScore(), editDiaryRequest.getRecord());
    }

    public ShowDiaryResponse show(Long memberId, LocalDate date) {
        Diary diary = diaryRepository.findByMemberIdAndAndDate(memberId, date);
        return ShowDiaryResponse.builder().symptoms(diary.getSymptom()).record(diary.getRecord()).build();
    }

    private Diary findById(Long diaryId) {
        return diaryRepository.findById(diaryId).orElseThrow(() -> new NotFoundException(NOT_FOUND_DIARY));
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));
    }
}
