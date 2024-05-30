package service.auth_service.service;


import service.auth_service.entity.UserRefreshToken;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
public interface UserRefreshTokenService {
    UserRefreshToken saveRefreshToken(@NotNull UserRefreshToken userRefreshToken);
    boolean checkValidAccessToken(String tokenId);

    UserRefreshToken getRefreshToken(UUID ati);
    UserRefreshToken getRefreshToken(String email);
}
