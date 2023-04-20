package uz.sh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:34 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complex extends Auditable {

    private String complexName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room building;

    @OneToOne(cascade = CascadeType.ALL)
    private AuthUser authUser;

    private Long userId;

}
