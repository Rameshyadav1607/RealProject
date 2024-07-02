package com.tapso.dispatchsection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.MstLocation;
@Repository
public interface MstLocationRepository extends JpaRepository<MstLocation, String> {

	Optional<MstLocation> findByLocCode(String locCode);
}