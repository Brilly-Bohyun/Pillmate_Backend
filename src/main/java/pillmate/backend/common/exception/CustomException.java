package pillmate.backend.common.exception;

import lombok.Getter;
import pillmate.backend.common.exception.errorcode.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
