package com.tapso.dispatchsection.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

}

