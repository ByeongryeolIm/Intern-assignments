package com.vtw.dna.employee;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Gender gender;
    private Date birthDate;

    public Employee update(Employee newOne) {
        this.name = newOne.name;
        this.gender = newOne.gender;
        this.birthDate = newOne.birthDate;
        return this;
    }
}
