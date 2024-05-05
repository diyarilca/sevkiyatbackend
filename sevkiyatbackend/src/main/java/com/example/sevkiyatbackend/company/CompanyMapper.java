package com.example.sevkiyatbackend.company;

public class CompanyMapper {

    public static CompanyDTO mapToCompanyDto(Company company){
        return new CompanyDTO(
                company.getId(),
                company.getCompanyName(),
                company.getTelNumber(),
                company.getEmail(),
                company.getTaxAdministration(),
                company.getTaxNo(),
                company.getAddress()
        );
    }

    public static Company mapToCompany(CompanyDTO companyDTO){
        return new Company(
                companyDTO.getId(),
                companyDTO.getCompanyName(),
                companyDTO.getTelNumber(),
                companyDTO.getEmail(),
                companyDTO.getTaxAdministration(),
                companyDTO.getTaxNo(),
                companyDTO.getAddress()
        );
    }
}
