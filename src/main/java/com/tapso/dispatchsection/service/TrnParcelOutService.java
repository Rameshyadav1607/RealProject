package com.tapso.dispatchsection.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapso.dispatchsection.dto.TrnParcelOutDTO;
import com.tapso.dispatchsection.dto.TrnParcelOutResponseDTO;
import com.tapso.dispatchsection.entity.TrnParcelOut;
import com.tapso.dispatchsection.entity.TrnParcelOutId;
import com.tapso.dispatchsection.exception.DuplicateConsignmentNumberException;
import com.tapso.dispatchsection.repository.TrnParcelOutRepository;

@Service
public class TrnParcelOutService {
    @Autowired
    private TrnParcelOutRepository trnParcelOutRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TrnParcelOutResponseDTO saveParcelOut(TrnParcelOutDTO trnParcelOutDTO) {
        TrnParcelOut trnParcelOut = modelMapper.map(trnParcelOutDTO, TrnParcelOut.class);
        trnParcelOut.setSenderLocCode("4400");
        trnParcelOut.setCreatedBy("ramesh");
        // Check if consignment_number already exists
        if (trnParcelOutRepository.existsByConsignmentNumber(trnParcelOut.getConsignmentNumber())) {
            throw new DuplicateConsignmentNumberException("Consignment number already exists");
        }
        TrnParcelOut savedTrnParcelOut = trnParcelOutRepository.save(trnParcelOut);
        Long nextId = trnParcelOutRepository.fetchNextId();
        savedTrnParcelOut.setOutTrackingId(nextId);
        return modelMapper.map(savedTrnParcelOut, TrnParcelOutResponseDTO.class);
    }

    public List<TrnParcelOutResponseDTO> getAllParcelsOutSortedByOutTrackingIdDesc() {
        List<TrnParcelOut> parcels = trnParcelOutRepository.findAllByOrderByOutTrackingIdDesc();
        return parcels.stream()
                      .map(parcel -> modelMapper.map(parcel, TrnParcelOutResponseDTO.class))
                      .collect(Collectors.toList());
    }

    public TrnParcelOutResponseDTO getParcelOutById(TrnParcelOutId id) {
        Optional<TrnParcelOut> parcelOpt = trnParcelOutRepository.findById(id);
        return parcelOpt.map(parcel -> modelMapper.map(parcel, TrnParcelOutResponseDTO.class))
                        .orElse(null);
    }

    public TrnParcelOutResponseDTO updateParcelOut(TrnParcelOutId id, TrnParcelOutDTO trnParcelOutDTO) {
        Optional<TrnParcelOut> parcelOpt = trnParcelOutRepository.findById(id);
        if (parcelOpt.isPresent()) {
            TrnParcelOut trnParcelOut = parcelOpt.get();
            modelMapper.map(trnParcelOutDTO, trnParcelOut);
            trnParcelOut.setLastUpdatedDate(LocalDateTime.now());
            TrnParcelOut updatedParcelOut = trnParcelOutRepository.save(trnParcelOut);
            return modelMapper.map(updatedParcelOut, TrnParcelOutResponseDTO.class);
        }
        return null;
    }

    public void deleteParcelOut(TrnParcelOutId id) {
        Optional<TrnParcelOut> parcelOpt = trnParcelOutRepository.findById(id);
        if (parcelOpt.isPresent()) {
            TrnParcelOut trnParcelOut = parcelOpt.get();
            trnParcelOut.setRecordStatus("D");
            trnParcelOutRepository.save(trnParcelOut);
        }
    }
}
