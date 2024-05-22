package pillmate.backend.common.exception;

import pillmate.backend.common.exception.errorcode.ErrorCode;

public class IntervalServerException extends CustomException {
    public IntervalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
