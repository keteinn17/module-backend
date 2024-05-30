package service.auth_service.controller.mapper;

import org.springframework.stereotype.Component;
import service.auth_service.dto.UserDto;
import service.auth_service.dto.UserProfile;
import service.auth_service.entity.User;
;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public UserProfile showProfile(User user){
        UserProfile userProfile = UserProfile.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth().toString())
                .avatarId(user.getAvatarId())
                .build();
        return userProfile;
    }
}
