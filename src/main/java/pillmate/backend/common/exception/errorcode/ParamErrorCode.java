package pillmate.backend.common.exception.errorcode;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ParamErrorCode {
    INVALID_PAGE("page", "페이지 값이 잘못되었습니다.");

    private static final Map<String, String> ENUM_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ParamErrorCode::getParamName, ParamErrorCode::name))
    );
    private final String paramName;
    private final String message;

    ParamErrorCode(String paramName, String message) {
        this.paramName = paramName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getParamName() {
        return paramName;
    }

    public static ParamErrorCode of(final String paramName) {
        return ParamErrorCode.valueOf(ENUM_MAP.get(paramName));
    }
}
