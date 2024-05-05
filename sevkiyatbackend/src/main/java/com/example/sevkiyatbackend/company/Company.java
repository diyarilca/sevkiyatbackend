package com.example.sevkiyatbackend.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name",nullable = false)
    private String companyName;

    @Column(name = "tel_number",nullable = false)
    private String telNumber;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "tax_administration",nullable = false)
    private String taxAdministration;

    @Column(name = "tax_no",nullable = false,unique = true)
    private String taxNo;

    @Column(name = "address",nullable = false)
    private String address;
}
