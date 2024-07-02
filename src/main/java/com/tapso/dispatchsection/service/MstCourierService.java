package com.tapso.dispatchsection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.tapso.dispatchsection.dto.MstCourierDTO;
import com.tapso.dispatchsection.dto.MstDepartmentDTO;
import com.tapso.dispatchsection.entity.MstCourier;
import com.tapso.dispatchsection.repository.MstCourierRepository;

@Service
public class MstCourierService {

	private MstCourierRepository repository;
	private ModelMapper mapper;
   

	public MstCourierService(MstCourierRepository repository, ModelMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	public MstCourierDTO saveCourier(MstCourierDTO mstCourierDTO) {
		// TODO Auto-generated method stub
		ModelMapper mapper=new ModelMapper();
		 MstCourier         mstCourier=mapper.map(mstCourierDTO, MstCourier.class);
		      MstCourier         mstCourierSaved=repository.save(mstCourier);
		      
		return mapper.map(mstCourierSaved,MstCourierDTO.class);
	}

	 public List<MstCourierDTO> getAllCouriers() {
	        List<MstCourier> couriers = repository.findAll();
	        return couriers.stream()
	                       .map(courier -> mapper.map(courier, MstCourierDTO.class))
	                       .collect(Collectors.toList());
	    }

	 public MstCourierDTO updateCourier(String courierCode, MstCourierDTO courierDTO) {
	        MstCourier existingCourier = repository.findById(courierCode).orElseThrow(() -> new RuntimeException("Courier not found"));
	        existingCourier.setCourierName(courierDTO.getCourierName());
	        MstCourier updatedCourier = repository.save(existingCourier);
	        return mapper.map(updatedCourier, MstCourierDTO.class);
	    }
	
	 public void deleteCourier(String courierCode) {
	        repository.deleteById(courierCode);
	    }
	
	  
	

}
