package com.tapso.dispatchsection.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "units", schema = "public")
public class Unit {

    @Id
    @Column(name = "unit_id", length = 10, nullable = false)
    private String unitId;

    @Column(name = "name", length = 10, nullable = false)
    private String name;
}
