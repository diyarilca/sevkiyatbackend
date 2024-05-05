package com.example.sevkiyatbackend.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String telNumber;
    private String email;
    private String taxAdministration;
    private String taxNo;
    private String address;
}
