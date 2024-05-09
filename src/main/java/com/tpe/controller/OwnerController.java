package com.tpe.controller;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService service;

    // Save an Owner
    // http://localhost:8080/owners/save + JSON + POST
    @PostMapping("/save")
    public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerDTO ownerDto){
        service.saveOwner(ownerDto);
        return new ResponseEntity<>("Member was saved successfully", HttpStatus.OK);
    }

    // Find All Owners
    // http://localhost:8080/owners
    @GetMapping()
    public ResponseEntity<List<Owner>> getAllOwners(){
        List<Owner> ownerList = service.getAll();
        return ResponseEntity.ok(ownerList); //200
    }

    // Find an Owner By ID
    // http://localhost:8080/owners/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable("id") Long id){
        OwnerDTO ownerDto = service.getOwnerDTOById(id);
        return ResponseEntity.ok(ownerDto);
    }

    //HOMEWORK : delete and update owner
}
