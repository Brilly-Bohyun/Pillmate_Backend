package pillmate.backend.dto.oauth;

import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getInstance(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId.toLowerCase()) {
            case "kakao" -> new KakaoUserInfo(attributes);
            default -> throw new NotFoundException(ErrorCode.NOT_FOUND_SOCIAL_INFO);
        };
    }
}
