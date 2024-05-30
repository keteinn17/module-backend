package service.auth_service.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ket_ein17
 * @Date 5/27/2024
 */
@Table(name = "user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private Long avatarId;
    private String avatarPath;
    private Role role;
    private boolean enabled;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    @CreationTimestamp
    private Date createAt;
    @UpdateTimestamp
    private Date modifyAt;
    public String getUsername(){
        return username;
    }
}
