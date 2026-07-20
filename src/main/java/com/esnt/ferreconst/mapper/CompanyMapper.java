package com.esnt.ferreconst.mapper;

import com.esnt.ferreconst.dto.response.company.CompanyResponseDto;
import com.esnt.ferreconst.dto.response.company.CompanyPageResponseDto;
import com.esnt.ferreconst.model.company.Company;
import com.esnt.ferreconst.model.company.CompanyPage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {

    public Company toCompany(CompanyResponseDto dto) {
        if (dto == null) return null;

        Company company = new Company();
        company.setId(dto.getId());
        company.setRuc(dto.getRuc());
        company.setRznsocial(dto.getRznsocial());
        company.setEmail(dto.getEmail());
        company.setLogo(dto.getLogo());
        company.setCode(dto.getCode());
        company.setDateRegister(dto.getDateRegister());
        company.setStatus(dto.getStatus());
        company.setIsParent(dto.getIsParent());
        return company;
    }

    public CompanyPage toCompanyPage(CompanyPageResponseDto dto) {
        if (dto == null) return null;

        List<Company> companies = new ArrayList<>();
        if (dto.getItems() != null) {
            for (CompanyResponseDto item : dto.getItems()) {
                companies.add(toCompany(item));
            }
        }

        CompanyPage page = new CompanyPage();
        page.setItems(companies);
        page.setPage(dto.getPage());
        page.setPageSize(dto.getPageSize());
        page.setTotalItems(dto.getTotalItems());
        page.setTotalPages(dto.getTotalPages());
        return page;
    }
}
