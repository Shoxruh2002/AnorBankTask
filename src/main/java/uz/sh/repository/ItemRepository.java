package uz.sh.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sh.entity.Item;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:27 AM
 **/
public interface ItemRepository extends BaseRepository<Item> {

    List<Item> findAllByComplex_Id(Long id);

    List<Item> findAllByRoom_Id(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update item set complex_id = :complexId where id = :itemId ;")
    void itemBindToComplex(@Param("itemId") Long itemId, @Param("complexId") Long complexId);
}
