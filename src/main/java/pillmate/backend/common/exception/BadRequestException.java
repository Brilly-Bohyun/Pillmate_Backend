package pillmate.backend.common.exception;


import pillmate.backend.common.exception.errorcode.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
