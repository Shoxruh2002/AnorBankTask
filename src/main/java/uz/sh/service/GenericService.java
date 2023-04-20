package uz.sh.service;

import uz.sh.dto.BaseDTO;
import uz.sh.dto.GenericDTO;

import java.io.Serializable;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 10:50 AM
 **/
public interface GenericService<CD extends BaseDTO, UD extends GenericDTO, D extends GenericDTO, I extends Serializable> {

    I create(CD createDTO);

    I delete(I id);

    I update(UD updateDTO);

    D getById(I id);
}
