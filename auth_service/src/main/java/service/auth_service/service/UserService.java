package service.auth_service.service;


import service.auth_service.dto.UserDto;
import service.auth_service.dto.UserProfile;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
public interface UserService {
    UserDto createNewUser(UserDto userDto) throws Exception;

    UserProfile getUserById(Long id);

}