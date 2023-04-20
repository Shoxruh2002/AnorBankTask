package uz.sh.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.sh.mapper.BaseMapper;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 10:31 AM
 **/
@AllArgsConstructor
public abstract class AbstractService<R extends JpaRepository,M extends BaseMapper> {

    protected R repository;

    protected M mapper;
}
