package service.auth_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.auth_service.config.MyUsernamePasswordAuthenticationProvider;
import service.auth_service.config.token.JwtResponse;
import service.auth_service.config.token.TokenUtils;
import service.auth_service.dto.LoginRequest;
import service.auth_service.dto.MyUserDetails;
import service.auth_service.repository.UserRepository;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationService {
    @Autowired
    private MyUsernamePasswordAuthenticationProvider authenticationManager;
    @Autowired private TokenUtils tokenUtils;


    public JwtResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        MyUserDetails userDetails= (MyUserDetails) authentication.getPrincipal();
        return tokenUtils.createJwtResponse(userDetails);
    }

}
