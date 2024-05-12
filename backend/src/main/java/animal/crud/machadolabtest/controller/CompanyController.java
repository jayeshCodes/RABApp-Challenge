package animal.crud.machadolabtest.controller;

import animal.crud.machadolabtest.dto.CompanyDto;
import animal.crud.machadolabtest.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

}
