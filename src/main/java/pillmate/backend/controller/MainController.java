package pillmate.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.main.MainResponse;
import pillmate.backend.service.MainService;

import java.time.LocalTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
public class MainController {
    private final MainService mainService;

    @GetMapping
    public MainResponse show(@LoggedInMember Long memberId, @RequestParam("time") LocalTime currentTime) {
        return mainService.show(memberId, currentTime);
    }
}
