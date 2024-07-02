package com.tapso.dispatchsection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tapso.dispatchsection.entity.RefSequence;
public interface RefSequenceRepository extends JpaRepository<RefSequence, String> {
}
