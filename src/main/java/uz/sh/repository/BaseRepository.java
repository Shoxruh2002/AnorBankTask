package uz.sh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 10:52 AM
 **/
@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long> {
}
