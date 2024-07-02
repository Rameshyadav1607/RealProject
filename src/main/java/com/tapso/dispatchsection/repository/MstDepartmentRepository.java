package com.tapso.dispatchsection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.MstDepartment;
import com.tapso.dispatchsection.entity.MstDepartmentPK;
@Repository
public interface MstDepartmentRepository extends JpaRepository<MstDepartment,MstDepartmentPK> {

	MstDepartment findByDeptCode(String deptCode);

	List<MstDepartment> findByLocCode(String locCode);

	

}
