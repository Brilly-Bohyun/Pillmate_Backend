package pillmate.backend.entity.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import pillmate.backend.common.exception.BadRequestException;

import static pillmate.backend.common.exception.errorcode.ErrorCode.INVALID_MEMBER_TYPE;

public enum MemberType {
    DEFAULT, KAKAO;

    public static MemberType handle(MemberType type) {
        return type == null ? MemberType.DEFAULT : type;
    }

    @JsonCreator
    public static MemberType handle(String value) {
        if (value == null) {
            return DEFAULT;
        }

        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(INVALID_MEMBER_TYPE);
        }
    }
}
