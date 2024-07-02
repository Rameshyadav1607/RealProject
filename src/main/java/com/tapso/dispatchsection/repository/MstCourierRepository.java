package com.tapso.dispatchsection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.MstCourier;
@Repository
public interface MstCourierRepository extends JpaRepository<MstCourier,String> {

}
