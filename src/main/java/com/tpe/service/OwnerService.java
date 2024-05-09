package com.tpe.service;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.exceptions.ResourceNotFound;
import com.tpe.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository repository;

    public void saveOwner(OwnerDTO ownerDto) {
        boolean isExist = repository.existsByEmail(ownerDto.getEmail());
        if(isExist){
            throw new ConflictException("This email is already exist!");
        }
        Owner newOwner=new Owner(ownerDto);//id:auto generated
        //generated param. const. with OwnerDTO
//        newOwner.setName(ownerDto.getName());
//        newOwner.setLastName(ownerDto.getLastName());
//        newOwner.setPhoneNumber(ownerDto.getPhoneNumber());
//        newOwner.setEmail(ownerDto.getEmail());

        repository.save(newOwner);

    }

    public List<Owner> getAll() {
        List<Owner> owners = repository.findAll();
        if(owners.isEmpty()){
            throw new ResourceNotFound("Resource not found");
        }
        return owners;
    }

    public OwnerDTO getOwnerDTOById(Long id) {
        Owner owner = getOwnerById(id);
        OwnerDTO ownerDTO = new OwnerDTO(owner);
//        ownerDTO.setName(owner.getName());
//        ownerDTO.setLastName(owner.getLastName());
//        ownerDTO.setPhoneNumber(owner.getPhoneNumber());
//        ownerDTO.setEmail(owner.getEmail());
        return ownerDTO;


    }

    public Owner getOwnerById(Long id){
        Owner owner = repository.findById(id).orElseThrow(()->new ResourceNotFound("Resource not found"));
        return owner;
    }
}
