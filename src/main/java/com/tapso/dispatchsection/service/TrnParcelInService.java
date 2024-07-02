package com.tapso.dispatchsection.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapso.dispatchsection.dto.TrnParcelInDTO;
import com.tapso.dispatchsection.dto.TrnParcelInResponseDTO;
import com.tapso.dispatchsection.entity.TrnParcelIn;
import com.tapso.dispatchsection.entity.TrnParcelInId;
import com.tapso.dispatchsection.exception.DuplicateConsignmentNumberException;
import com.tapso.dispatchsection.repository.TrnParcelInRepository;

@Service
public class TrnParcelInService {
	@Autowired
	private TrnParcelInRepository trnParcelInRepository;
	@Autowired
	private ModelMapper modelMapper;


    public TrnParcelInResponseDTO saveParcelIn(TrnParcelInDTO trnParcelInDTO) {
        // Convert DTO to Entity
        TrnParcelIn trnParcel = modelMapper.map(trnParcelInDTO, TrnParcelIn.class);
        trnParcel.setRecipientLocCode("4401");
        trnParcel.setCreatedBy("sai");

        // Check if consignment_number already exists
        if (trnParcelInRepository.existsByConsignmentNumber(trnParcel.getConsignmentNumber())) {
            throw new DuplicateConsignmentNumberException("Consignment number already exists");
        }

        // Save the entity
        TrnParcelIn savedTrnParcelIn = trnParcelInRepository.save(trnParcel);

        // Fetch the next tracking ID
        Long nextId = trnParcelInRepository.fetchNextId();

        // Convert Entity to Response DTO
        TrnParcelInResponseDTO trnParcelInResponse = modelMapper.map(savedTrnParcelIn, TrnParcelInResponseDTO.class);
        trnParcelInResponse.setInTrackingId(nextId);

        return trnParcelInResponse;
    }
	 public List<TrnParcelInResponseDTO> getAllParcels() {
	        List<TrnParcelIn> parcels = trnParcelInRepository.findAllByOrderByInTrackingIdDesc();
	        return parcels.stream()
	                      .map(parcel -> modelMapper.map(parcel, TrnParcelInResponseDTO.class))
	                      .toList();
	    }
	 public TrnParcelInResponseDTO getParcelById(TrnParcelInId id) {
	        Optional<TrnParcelIn> parcelOpt = trnParcelInRepository.findById(id);
	        return parcelOpt.map(parcel -> modelMapper.map(parcel, TrnParcelInResponseDTO.class))
	                        .orElse(null);
	    }


	 public TrnParcelInResponseDTO updateParcel(TrnParcelInId id, TrnParcelInDTO trnParcelInDTO) {
	        Optional<TrnParcelIn> parcelOpt = trnParcelInRepository.findById(id);
	        if (parcelOpt.isPresent()) {
	            TrnParcelIn trnParcel = parcelOpt.get();
	            modelMapper.map(trnParcelInDTO, trnParcel);// Update existing parcel with new values
	            trnParcel.setCreatedBy("sai");
	            trnParcel.setLastUpdatedDate(LocalDateTime.now()); // Update the last updated date
	            TrnParcelIn updatedParcel = trnParcelInRepository.save(trnParcel);
	            return modelMapper.map(updatedParcel, TrnParcelInResponseDTO.class);
	        }
	        return null;
	    }
	 public void deleteParcel(TrnParcelInId id) {
	        Optional<TrnParcelIn> parcelOpt = trnParcelInRepository.findById(id);
	        if (parcelOpt.isPresent()) {
	            TrnParcelIn trnParcel = parcelOpt.get();
	            trnParcel.setRecordStatus("D");
	            trnParcelInRepository.save(trnParcel);
	        }
	    }

}
