package uz.sh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:34 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Column(length = 100)
    private String fullName;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 40)
    private String position;

    private Integer age;

}
