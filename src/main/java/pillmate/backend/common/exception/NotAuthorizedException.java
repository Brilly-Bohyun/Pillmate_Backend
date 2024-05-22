package pillmate.backend.common.exception;

import pillmate.backend.common.exception.errorcode.ErrorCode;

public class NotAuthorizedException extends CustomException {
    public NotAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
