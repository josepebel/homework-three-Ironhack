package com.homework3.homework3overriders.service.impl;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Lead;
import com.homework3.homework3overriders.model.Opportunity;
import com.homework3.homework3overriders.model.SalesRep;
import com.homework3.homework3overriders.repository.LeadRepository;
import com.homework3.homework3overriders.repository.SalesRepRepository;
import com.homework3.homework3overriders.service.interfaces.LeadService;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    public Lead findById(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<Lead> optionalLead = leadRepository.findById(id);
        if (optionalLead.isPresent()) {
            return optionalLead.get();
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any lead");
    }

    public void deleteById(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1) {
            throw new InvalidParameterException();
        }
        leadRepository.findById(id).orElseThrow(() -> new NoSuchElementException("The ID " + id + " does not match with any lead"));
        leadRepository.deleteById(id);
    }

    public void save(Lead lead) throws InvalidParameterException, ExistentElementException {
        if (lead.getId() != null) {
            if (lead.getId() < 1) {
                throw new InvalidParameterException();
            }
            Optional<Lead> optionalLead = leadRepository.findById(lead.getId());
            if (optionalLead.isPresent()) {
                throw new ExistentElementException("The ID " + lead.getId() + " already exists in the leads Database");
            } else {
                leadRepository.save(lead);
            }
        } else {
            leadRepository.save(lead);
        }
    }

    public List<Lead> getAll() {
        return leadRepository.findAll();
    }

    public Lead createLead() {
        //get name from user
        String name = "";
        do{
            PrintText.askLeadName();
            String initialName = Lead.getNameFromUser();
            if(initialName.matches((Lead.regexNameValidation()))) {
                name = initialName;
            } else {
                name = "";
                PrintText.invalidName();
            }
        } while ("".equals(name));

        //get phone number
        String phoneNumber = "";
        do{
            PrintText.askPhoneNumber();
            String initialNumber = Lead.getPhoneNumberFromUser();
            if(initialNumber.matches(Lead.regexPhoneNumberValidation())){
                phoneNumber = initialNumber;
            } else {
                phoneNumber = "";
                PrintText.invalidPhoneNumber();
            }
        } while ("".equals(phoneNumber));

        //get email
        String email = "";
        do{
            PrintText.askEmail();
            String initialEmail = Lead.getEmailFromUser();
            if(initialEmail.matches(Lead.regexEmailValidation())){
                email = initialEmail;
            } else {
                email = "";
                PrintText.invalidEmail();
            }
        } while ("".equals(email));

        //get company Name
        String companyName = "";
        do{
            PrintText.askCompanyName();
            String initialCompanyName = Lead.getCompanyNameFromUser();
            if(initialCompanyName.matches((Lead.regexCompanyNameValidation()))) {
                companyName = initialCompanyName;
            } else {
                companyName = "";
                PrintText.invalidName();
            }
        } while ("".equals(companyName));

        //Get SalesRep id
        Long salesRepId = Long.valueOf(0);
        Optional<SalesRep> optionalSalesRep = null;
        do {
            PrintText.askSalesRepId();
            salesRepId = SalesRep.getSalesRepIdFromCommand();
            if (salesRepId > 0) {
                optionalSalesRep = salesRepRepository.findById(salesRepId);
                if (!optionalSalesRep.isPresent()){
                    List<SalesRep> allSalesReps = salesRepRepository.findAll();
                    PrintText.wrongSalesRepNotFound(salesRepId, allSalesReps);
                    salesRepId = Long.valueOf(0);
                }
            }

        } while (salesRepId == 0);
        Lead newLead = new Lead(name, phoneNumber, email, companyName, optionalSalesRep.get());

        return newLead;
    }
}
