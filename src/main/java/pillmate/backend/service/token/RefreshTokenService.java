package pillmate.backend.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.common.util.JwtTokenConst;
import pillmate.backend.entity.token.RefreshToken;
import pillmate.backend.repository.token.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken save(Long memberId, String refreshToken) {
        return refreshTokenRepository.save(
                RefreshToken.from(
                        memberId,
                        refreshToken,
                        JwtTokenConst.REFRESH_TOKEN_EXPIRE_TIME
                )
        );
    }

    public RefreshToken findByMemberId(Long memberId) {
        return refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_TOKEN));
    }

    public void deleteByMemberId(Long memberId) {
        refreshTokenRepository.deleteById(memberId);
    }
}
