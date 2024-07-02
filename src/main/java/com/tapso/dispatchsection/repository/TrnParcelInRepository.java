package com.tapso.dispatchsection.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapso.dispatchsection.entity.TrnParcelIn;
import com.tapso.dispatchsection.entity.TrnParcelInId;
@Repository
public interface TrnParcelInRepository extends JpaRepository<TrnParcelIn, TrnParcelInId> {

	
	@Query("select max(tpi.inTrackingId) from TrnParcelIn tpi")
	Long fetchNextId();

	List<TrnParcelIn> findAllByOrderByInTrackingIdDesc();

	Long countByCreatedDate(LocalDate today);

	List<?> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDate startDate, LocalDate endDate);

	boolean existsByConsignmentNumber(String consignmentNumber);
	
}
