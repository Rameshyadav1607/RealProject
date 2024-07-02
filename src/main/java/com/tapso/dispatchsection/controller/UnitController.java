package com.tapso.dispatchsection.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tapso.dispatchsection.entity.Unit;
import com.tapso.dispatchsection.service.UnitService;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<Unit> getUnitById(@PathVariable String unitId) {
        Optional<Unit> unit = unitService.getUnitById(unitId);
        return unit.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
        Unit createdUnit = unitService.createUnit(unit);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<Unit> updateUnit(@PathVariable String unitId, @RequestBody Unit unitDetails) {
        Unit updatedUnit = unitService.updateUnit(unitId, unitDetails);
        return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<Void> deleteUnit(@PathVariable String unitId) {
        unitService.deleteUnit(unitId);
        return ResponseEntity.noContent().build();
    }
}
