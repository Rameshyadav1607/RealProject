package com.tapso.dispatchsection.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_user")
@Setter
@Getter
@IdClass(MstUserPK.class)

public class MstUser {


	@Id
    @Column(name = "loc_code", length = 6)
    private String locCode;

    @Id
    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "role_id", length = 10)
    private String roleId;

    @Column(name = "status", length = 1)
    private String status = "A";

    @Column(name = "created_by", length = 10)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDate createdDate=LocalDate.now();

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @Column(name="dept_code")
    private String deptCode;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "dept_code", insertable = false, updatable = false)
    @JoinColumn(name = "loc_code", insertable = false, updatable = false)
    private MstDepartment mstdepartment;

//    @ManyToOne
//    @JoinColumn(name = "loc_code", insertable = false, updatable = false)
//    private MstLocation mstLocation;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private MstRole mstRole;


}