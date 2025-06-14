package com.embrakx.companyms.company.service.impl;

import com.embrakx.companyms.company.clients.ReviewClient;
import com.embrakx.companyms.company.dto.ReviewMessage;
import com.embrakx.companyms.company.entity.Company;
import com.embrakx.companyms.company.repository.CompanyRepository;
import com.embrakx.companyms.company.service.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany=companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company updateCompany = optionalCompany.get();
            updateCompany.setName(company.getName());
            updateCompany.setDescription(company.getDescription());
            return companyRepository.save(updateCompany);
        }else {
            return null;
        }

    }

    @Override
    public Company createCompany(Company company) {
        Company saveCompany = new Company();
        saveCompany.setName(company.getName());
        saveCompany.setDescription(company.getDescription());
        return companyRepository.save(saveCompany);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }



    @Override
    public Company getCompanyById(Long id) {
       return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
//        System.out.println(reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found"+reviewMessage.getCompanyId()));

        Double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());

        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
