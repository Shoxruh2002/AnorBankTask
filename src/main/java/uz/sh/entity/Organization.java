package uz.sh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:33 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization extends Auditable {

    private String name;

    private String address;

}
