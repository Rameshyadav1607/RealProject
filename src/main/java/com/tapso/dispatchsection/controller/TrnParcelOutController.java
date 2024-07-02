package com.tapso.dispatchsection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tapso.dispatchsection.dto.TrnParcelInDTO;
import com.tapso.dispatchsection.dto.TrnParcelInResponseDTO;
import com.tapso.dispatchsection.dto.TrnParcelOutDTO;
import com.tapso.dispatchsection.dto.TrnParcelOutResponseDTO;
import com.tapso.dispatchsection.entity.TrnParcelOutId;
import com.tapso.dispatchsection.exception.DuplicateConsignmentNumberException;
import com.tapso.dispatchsection.service.TrnParcelOutService;

@RestController
@RequestMapping("/api/parcels/out")
public class TrnParcelOutController {
    @Autowired
    private TrnParcelOutService trnParcelOutService;

    @PostMapping("/save")
    public ResponseEntity<?> saveParcelIn(@RequestBody TrnParcelOutDTO trnParceloutDTO) {
        try {
            TrnParcelOutResponseDTO trnParceloutResponseDTO = trnParcelOutService.saveParcelOut(trnParceloutDTO);
            return new ResponseEntity<>(trnParceloutResponseDTO, HttpStatus.CREATED);
        } catch (DuplicateConsignmentNumberException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<TrnParcelOutResponseDTO>> getAllParcelsOut() {
        List<TrnParcelOutResponseDTO> parcels = trnParcelOutService.getAllParcelsOutSortedByOutTrackingIdDesc();
        return new ResponseEntity<>(parcels, HttpStatus.OK);
    }

    @GetMapping("/{senderLocCode}/{outTrackingId}")
    public ResponseEntity<TrnParcelOutResponseDTO> getParcelOutById(@PathVariable String senderLocCode, @PathVariable Long outTrackingId) {
        TrnParcelOutId id = new TrnParcelOutId(senderLocCode, outTrackingId);
        TrnParcelOutResponseDTO parcel = trnParcelOutService.getParcelOutById(id);
        return parcel != null ? new ResponseEntity<>(parcel, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{senderLocCode}/{outTrackingId}")
    public ResponseEntity<TrnParcelOutResponseDTO> updateParcelOut(@PathVariable String senderLocCode, @PathVariable Long outTrackingId, @RequestBody TrnParcelOutDTO trnParcelOutDTO) {
        TrnParcelOutId id = new TrnParcelOutId(senderLocCode, outTrackingId);
        TrnParcelOutResponseDTO updatedParcelOut = trnParcelOutService.updateParcelOut(id, trnParcelOutDTO);
        return updatedParcelOut != null ? new ResponseEntity<>(updatedParcelOut, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{senderLocCode}/{outTrackingId}")
    public ResponseEntity<Void> deleteParcelOut(@PathVariable String senderLocCode, @PathVariable Long outTrackingId) {
        TrnParcelOutId id = new TrnParcelOutId(senderLocCode, outTrackingId);
        trnParcelOutService.deleteParcelOut(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
