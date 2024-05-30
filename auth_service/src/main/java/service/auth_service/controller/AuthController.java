package service.auth_service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.auth_service.dto.LoginRequest;
import service.auth_service.dto.UserDto;
import service.auth_service.service.AuthenticationService;
import service.auth_service.service.UserServiceImpl;
import service.auth_service.utils.Constants;
import service.auth_service.utils.exception.InvalidRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    @Autowired private AuthenticationService authenticationService;
    @PostMapping("/registry")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) throws InvalidRequestException {
        log.info(Constants.SEPARATE);
        if(Objects.isNull(userDto.getDateOfBirth()) || userDto.getDateOfBirth().equals("")){
            userDto.setDateOfBirth(null);
        }
        UserDto dto = userServiceImpl.createNewUser(userDto);

        log.info("Registry success");
        log.info(Constants.SEPARATE);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws InvalidRequestException {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    private String getJwtToken(HttpServletRequest request) {
        if (request.getHeader(Constants.AUTHORIZATION_HEADER) != null
                && request.getHeader(Constants.AUTHORIZATION_HEADER).startsWith(Constants.TOKEN_PREFIX)) {
            return request.getHeader(Constants.AUTHORIZATION_HEADER).replace(Constants.TOKEN_PREFIX, "");
        }
        return null;
    }

}
