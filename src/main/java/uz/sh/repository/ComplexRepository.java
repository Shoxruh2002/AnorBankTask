package uz.sh.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sh.entity.Complex;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:26 AM
 **/
public interface ComplexRepository extends BaseRepository<Complex> {

    List<Complex> findAllByAuthUser_Id(Long id);

    List<Complex> findAllByRoom_Id(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update complex  set auth_user_id = :userId where id = :complexId ;")
    void bindComplexToUser(@Param("complexId") Long complexId, @Param("userId") Long userId);

}
