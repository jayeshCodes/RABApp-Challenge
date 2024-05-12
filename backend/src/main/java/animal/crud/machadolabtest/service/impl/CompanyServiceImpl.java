package animal.crud.machadolabtest.service.impl;

import animal.crud.machadolabtest.dto.CompanyDto;
import animal.crud.machadolabtest.entity.Company;
import animal.crud.machadolabtest.mapper.CompanyMapper;
import animal.crud.machadolabtest.repository.CompanyRepository;
import animal.crud.machadolabtest.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(CompanyMapper::mapToCompanyDto)
                .collect(Collectors.toList());
    }
}
