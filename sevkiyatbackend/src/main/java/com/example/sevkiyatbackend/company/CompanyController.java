package com.example.sevkiyatbackend.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CompanyController {
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO){

        CompanyDTO savedCompany = companyService.createCompany(companyDTO);

        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") Long companyId){

        CompanyDTO companyDTO = companyService.getCompanyById(companyId);

        return ResponseEntity.ok(companyDTO);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(){

        List<CompanyDTO> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(companies);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long companyId,@RequestBody CompanyDTO updatedCompany){

        CompanyDTO companyDTO = companyService.updateCompany(companyId,updatedCompany);

        return ResponseEntity.ok(companyDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId){

        companyService.deleteCompany(companyId);

        return ResponseEntity.ok("Firma başarıyla silindi.");
    }
}
