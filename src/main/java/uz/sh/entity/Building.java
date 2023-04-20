package uz.sh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:33 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Building extends Auditable {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Organization organization;


}
