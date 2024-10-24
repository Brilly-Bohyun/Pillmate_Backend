package pillmate.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.medicine.UpcomingAlarm;
import pillmate.backend.dto.member.CheckEmailRequest;
import pillmate.backend.dto.member.CheckPasswordRequest;
import pillmate.backend.dto.member.FindPasswordRequest;
import pillmate.backend.dto.member.FindPasswordResponse;
import pillmate.backend.dto.member.JwtTokenResponse;
import pillmate.backend.dto.member.LoginRequest;
import pillmate.backend.dto.member.LoginResponse;
import pillmate.backend.dto.member.LogoutResponse;
import pillmate.backend.dto.member.ModifyPasswordRequest;
import pillmate.backend.dto.member.MyHealthInfo;
import pillmate.backend.dto.member.SignUpRequest;
import pillmate.backend.service.AlarmService;
import pillmate.backend.service.MemberService;

import java.time.LocalTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    private static final String REFRESH_TOKEN = "refresh_token";
    private final AlarmService alarmService;

    @PostMapping("/signup")
    public JwtTokenResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return memberService.register(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return memberService.issueToken(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public LogoutResponse logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return memberService.expireToken(accessToken);
    }

    @PostMapping("/reissue")
    public LoginResponse reissue(@LoggedInMember Long memberId, @CookieValue(REFRESH_TOKEN) String refreshToken) {
        return memberService.reissueToken(memberId, refreshToken);
    }

    @PostMapping("/check/email")
    public ResponseEntity<String> checkEmail(@LoggedInMember Long memberId, @RequestBody CheckEmailRequest checkEmailRequest) {
        Boolean isDuplicate = memberService.checkEmail(memberId, checkEmailRequest);
        if (isDuplicate) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복되는 이메일 입니다");
        } else {
            return ResponseEntity.ok("가능한 이메일입니다.");
        }
    }

    @PostMapping("/check/password")
    public Boolean checkPassword(@LoggedInMember Long memberId, @RequestBody CheckPasswordRequest checkPasswordRequest) {
        return memberService.checkPassword(memberId, checkPasswordRequest);
    }

    @PostMapping("/password")
    public FindPasswordResponse findPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        return memberService.issueTemporaryPassword(findPasswordRequest);
    }

    @PatchMapping("/password")
    public void modifyPassword(@LoggedInMember Long memberId, @RequestBody ModifyPasswordRequest modifyPasswordRequest) {
        memberService.modifyPassword(memberId, modifyPasswordRequest);
    }

    @GetMapping("/healthinfo")
    public MyHealthInfo healthInfo(@LoggedInMember Long memberId) {
        return memberService.getHealthInfo(memberId);
    }

    @PatchMapping("/healthinfo")
    public ResponseEntity<String> updateHealthInfo(@LoggedInMember Long memberId, @RequestBody MyHealthInfo modifyHealthInfo) {
        return memberService.modifyHealthInfo(memberId, modifyHealthInfo);
    }

    @GetMapping
    public UpcomingAlarm getUpcomingAlarm(@LoggedInMember Long memberId, @RequestParam("time") LocalTime currentTime) {
        return alarmService.getUpcomingAlarm(memberId, currentTime);
    }
}
