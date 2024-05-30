package service.auth_service.config.token;

import io.jsonwebtoken.Claims;
import service.auth_service.dto.MyUserDetails;


/**
 * @author ket_ein17
 */
public interface TokenUtils {
    Claims getClaimsFromJwtToken(String jwtToken);

    String generateJwtToken(Claims claims);

    JwtResponse createJwtResponse(MyUserDetails user);

    Claims checkValidAccessToken(String accessToken);
}
