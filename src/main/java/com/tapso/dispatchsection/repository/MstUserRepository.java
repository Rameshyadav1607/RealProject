package com.tapso.dispatchsection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.MstUser;
import com.tapso.dispatchsection.entity.MstUserPK;

@Repository
public interface MstUserRepository extends JpaRepository<MstUser,MstUserPK>{

	List<MstUser> findByDeptCode(String deptcode);

     MstUser findByLocCodeAndUserId(String userId, String userId2);

     Optional<MstUser> findByUserId(String userId);



}