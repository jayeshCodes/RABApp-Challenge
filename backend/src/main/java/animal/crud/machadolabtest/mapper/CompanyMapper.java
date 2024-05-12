package animal.crud.machadolabtest.mapper;

import animal.crud.machadolabtest.dto.CompanyDto;
import animal.crud.machadolabtest.entity.Company;

public class CompanyMapper {

    public static CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(
            company.getCompanyId(),
            company.getCompanyName()
        );
    }

    public static Company mapToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setCompanyId(companyDto.getCompanyId());
        company.setCompanyName(companyDto.getCompanyName());
        return company;
    }
}
