package service.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.auth_service.entity.UserRefreshToken;


import java.util.UUID;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken,String> {
    UserRefreshToken findByAti(UUID ati);
    boolean existsByAti(String ati);
    UserRefreshToken findByEmail(String email);
}
