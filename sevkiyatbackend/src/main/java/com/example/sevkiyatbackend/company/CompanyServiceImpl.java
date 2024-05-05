package com.example.sevkiyatbackend.company;

import com.example.sevkiyatbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {

        Company company = CompanyMapper.mapToCompany(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return CompanyMapper.mapToCompanyDto(savedCompany);
    }

    @Override
    public CompanyDTO getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Girilen id numaralı firma bulunamadı: " + companyId));
        return CompanyMapper.mapToCompanyDto(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map((company) -> CompanyMapper.mapToCompanyDto(company))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO updateCompany(Long companyId, CompanyDTO updatedCompany) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException("Girilen id numaralı firma bulunamadı: " + companyId));

        company.setCompanyName(updatedCompany.getCompanyName());
        company.setTelNumber(updatedCompany.getTelNumber());
        company.setEmail(updatedCompany.getEmail());
        company.setTaxAdministration(updatedCompany.getTaxAdministration());
        company.setTaxNo(updatedCompany.getTaxNo());
        company.setAddress(updatedCompany.getAddress());

        Company updatedCompanyObject = companyRepository.save(company);

        return CompanyMapper.mapToCompanyDto(updatedCompanyObject);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException("Girilen id numaralı firma bulunamadı: " + companyId));

        companyRepository.deleteById(companyId);
    }
}
