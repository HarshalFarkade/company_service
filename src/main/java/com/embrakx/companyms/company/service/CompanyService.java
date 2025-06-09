package com.embrakx.companyms.company.service;

import com.embrakx.companyms.company.dto.ReviewMessage;
import com.embrakx.companyms.company.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<Company>  getAllCompany();

    Company updateCompany(Long id,Company company);

    Company createCompany(Company company);

    boolean deleteCompany(Long id);

    Company getCompanyById(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
