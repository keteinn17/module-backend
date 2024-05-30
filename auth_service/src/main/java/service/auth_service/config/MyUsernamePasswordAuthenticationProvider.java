package service.auth_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import service.auth_service.dto.MyUserDetails;
import service.auth_service.service.MyUserDetailService;

/**
 * @author ket_ein17
 * @Date 5/29/2024
 */
@Component
@Slf4j
public class MyUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final MyUserDetailService myUserDetailService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MyUsernamePasswordAuthenticationProvider(MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) usernamePasswordAuthenticationToken.getPrincipal();
        MyUserDetails userDetails = (MyUserDetails) myUserDetailService.loadUserByUsername(username); // username is email

        if (userDetails == null) {
            log.error("Invalid username/password");
            throw new BadCredentialsException("Invalid username/password");
        }
        String password = (String) usernamePasswordAuthenticationToken.getCredentials();
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
