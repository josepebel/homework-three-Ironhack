package com.homework3.homework3overriders.service.impl;

import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Account;
import com.homework3.homework3overriders.model.Contact;
import com.homework3.homework3overriders.model.Opportunity;
import com.homework3.homework3overriders.model.SalesRep;
import com.homework3.homework3overriders.repository.OpportunityRepository;
import com.homework3.homework3overriders.service.interfaces.AccountService;
import com.homework3.homework3overriders.service.interfaces.ContactService;
import com.homework3.homework3overriders.service.interfaces.OpportunityService;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;


    @Autowired
    ContactService contactService;

    @Autowired
    AccountService accountService;

    public Opportunity findById(Long id)  throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(id);
        if (optionalOpportunity.isPresent()) {
            return optionalOpportunity.get();
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any opportunity");
    }

    public void delete(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(id);
        if (optionalOpportunity.isPresent()){
            //Según el diagrama de clases, cuando se borra una oportunidad se borra su decisionMaker
            contactService.delete(optionalOpportunity.get().getDecisionMaker().getId());
            opportunityRepository.delete(optionalOpportunity.get());
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any opportunity");
        }
    }
    public void save(Opportunity opportunity) throws ExistentElementException {
        if (opportunity.getId() != null) {
            if (opportunity.getId() < 1)
                throw new InvalidParameterException();
            Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(opportunity.getId());
            if (optionalOpportunity.isPresent()) {
                throw new ExistentElementException("The ID " + opportunity.getId() + " already exists in the opportunities Database");
            } else {
                opportunityRepository.save(opportunity);
            }
        } else {
            opportunityRepository.save(opportunity);
        }
    }

    public Account findAccountByOpportunity(Opportunity opportunity) throws NoSuchElementException {
        Optional<Opportunity> optionalOpportunity =  opportunityRepository.findById(opportunity.getId());
        if (optionalOpportunity.isPresent()){
            Account account = optionalOpportunity.get().getAccount();
            if (account == null){
                throw new NoSuchElementException("The Opportunity " + opportunity + " does not have any account");
            } else {
                return account;
            }
        } else {
            throw new NoSuchElementException("The Opportunity " + opportunity + " does not match with any account");
        }
    }

    public List<Opportunity> getAll() {
        return opportunityRepository.findAll();
    }

    public Opportunity obtainOpportunity(Contact contact, SalesRep salesRep) {
        //Get product from user
        Product product = null;
        do {
            PrintText.askProduct();
            product = Product.getProductFromScanner();
        } while (product == null);

        //Get number of trucks from user
        int numberOfTrucks = 0;
        do {
            PrintText.askNumberOfTrucks();
            numberOfTrucks = Opportunity.getNumberOfTrucks();
        } while (numberOfTrucks == 0);

        //Create the opportunity from the lead
        Opportunity opportunity = new Opportunity(product, numberOfTrucks, contact, salesRep);

        return opportunity;
    }

    public int closeLost(Long id) throws InvalidParameterException, NoSuchElementException {
        Opportunity opportunity = findById(id);
        if (opportunity.getStatus().equals(Status.OPEN)) {
            opportunity.setStatus(Status.CLOSED_LOST);
            opportunityRepository.save(opportunity);
            PrintText.closeOpportunityLost(id);
            return 9;
        } else {
            PrintText.opportunityAlreadyClosed(id);
            return -9;
        }
    }
    public int closeWon(Long id) throws InvalidParameterException, NoSuchElementException {
        Opportunity opportunity = findById(id);
        if (opportunity.getStatus().equals(Status.OPEN)) {
            opportunity.setStatus(Status.CLOSED_WON);
            opportunityRepository.save(opportunity);
            PrintText.closeOpportunityWon(id);
            return 10;
        } else {
            PrintText.opportunityAlreadyClosed(id);
            return -10;
        }

    }

    public List<Object[]> opportunitiesByProduct() {
        return opportunityRepository.opportunitiesByProduct();
    }

    public List<Object[]> openOpportunitiesByProduct() {
        return opportunityRepository.openOpportunitiesByProduct();
    }

    public List<Object[]> wonOpportunitiesByProduct() {
        return opportunityRepository.wonOpportunitiesByProduct();
    }

    public List<Object[]> lostOpportunitiesByProduct() {
        return opportunityRepository.lostOpportunitiesByProduct();
    }

    public List<Object[]> opportunitiesByCountry() {
        return opportunityRepository.opportunitiesByCountry();
    }

    public List<Object[]> openOpportunitiesByCountry() {
        return opportunityRepository.openOpportunitiesByCountry();
    }

    public List<Object[]> wonOpportunitiesByCountry() {
        return opportunityRepository.wonOpportunitiesByCountry();
    }

    public List<Object[]> lostOpportunitiesByCountry() {
        return opportunityRepository.lostOpportunitiesByCountry();
    }

    public List<Object[]> opportunitiesByCity() {
        return opportunityRepository.opportunitiesByCity();
    }

    public List<Object[]> openOpportunitiesByCity() {
        return opportunityRepository.openOpportunitiesByCity();
    }

    public List<Object[]> wonOpportunitiesByCity() {
        return opportunityRepository.wonOpportunitiesByCity();
    }

    public List<Object[]> lostOpportunitiesByCity() {
        return opportunityRepository.lostOpportunitiesByCity();
    }

    public List<Object[]> opportunitiesByIndustry() {
        return opportunityRepository.opportunitiesByIndustry();
    }

    public List<Object[]> openOpportunitiesByIndustry() {
        return opportunityRepository.openOpportunitiesByIndustry();
    }

    public List<Object[]> wonOpportunitiesByIndustry() {
        return opportunityRepository.wonOpportunitiesByIndustry();
    }

    public List<Object[]> lostOpportunitiesByIndustry() {
        return opportunityRepository.lostOpportunitiesByIndustry();
    }

    public double getMeanEmployeeCount() throws IllegalStateException {
            if(accountService.getAll().size() == 0){
                throw new IllegalStateException();
            }
            return opportunityRepository.getMeanEmployeeCount();
    }

    public BigDecimal getMedianEmployeeCount() throws IllegalStateException {
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        opportunityRepository.setRowIndex();
        return (BigDecimal) opportunityRepository.getMedianEmployeeCount().get(0)[0];
    }

    public int getMaxEmployeeCount() throws IllegalStateException{
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMaxEmployeeCount();
    }

    public int getMinEmployeeCount() throws IllegalStateException{
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMinEmployeeCount();
    }

    public double getMeanQuantityOfProducts() throws IllegalStateException{
        if(getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMeanQuantityOfProducts();
    }

    public BigDecimal getMedianQuantityOfProducts() throws IllegalStateException {
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        opportunityRepository.setRowIndex();
        return (BigDecimal) opportunityRepository.getMedianQuantityOfProducts().get(0)[0];
    }


    public int getMaxQuantityOfProducts() throws IllegalStateException{
        if(getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMaxQuantityOfProducts();
    }

    public int getMinQuantityOfProducts() throws IllegalStateException{
        if(getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMinQuantityOfProducts();
    }

    public double getMeanOpportunitiesPerAccount() throws IllegalStateException{
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMeanOpportunitiesPerAccount();
    }

    public BigDecimal getMedianOpportunitiesPerAccount() throws IllegalStateException {
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        opportunityRepository.setRowIndex();
        return (BigDecimal) opportunityRepository.getMedianOpportunitiesPerAccount().get(0)[0];
    }

    public int getMaxOpportunitiesPerAccount() throws IllegalStateException{
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMaxOpportunitiesPerAccount();
    }

    public int getMinOpportunitiesPerAccount() throws IllegalStateException{
        if(accountService.getAll().size() == 0){
            throw new IllegalStateException();
        }
        return opportunityRepository.getMinOpportunitiesPerAccount();
    }
}
