package service.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.auth_service.entity.Role;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonIgnore
    private Long userId;
    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String dateOfBirth;


    private String password;

    private String confirmPassword;

    private Role role;
}
