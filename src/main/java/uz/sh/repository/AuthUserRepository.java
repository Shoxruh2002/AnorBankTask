package uz.sh.repository;

import uz.sh.entity.AuthUser;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:28 AM
 **/
public interface AuthUserRepository extends BaseRepository<AuthUser> {

    Optional<AuthUser> findAuthUserByUsername(String username);
}
