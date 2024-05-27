package pillmate.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.main.MainResponse;
import pillmate.backend.service.MainService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
public class MainController {
    @Autowired
    private final MainService mainService;

    @GetMapping
    public MainResponse show(@LoggedInMember Long memberId) {
        return mainService.show(memberId);
    }
}
