package com.tpe.dto;

import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO {

    @NotBlank(message = "Geçerli bir isim giriniz!")
    private String name;

    @NotBlank(message = "Geçerli bir soyad giriniz!")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Geçerli bir email giriniz!")
    private String email;

    public OwnerDTO(Owner owner){
        this.name= owner.getName();
        this.lastName= owner.getLastName();
        this.phoneNumber = owner.getPhoneNumber();
        this.email= owner.getEmail();
    }

}
