package pillmate.backend.common.exception;

import pillmate.backend.common.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}