package com.homework3.homework3overriders.supportiveClasses;

import com.homework3.homework3overriders.model.*;
import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.*;

@Component
public class Interactions {

    @Autowired
    private OpportunityService opportunityService;

    @Autowired
    private LeadService leadService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private SalesRepService salesRepService;

    @Autowired
    private AccountService accountService;

    public Interactions() {
    }

    ///////////MÉTODOS USADOS DESDE COMANDOS//////////////
    public void createLead(){
        List<SalesRep> salesReps = salesRepService.getAll();

        if (salesReps.size() > 0) {
            Lead newLead = leadService.createLead();

            try {
                leadService.save(newLead);
                PrintText.leadCreated(newLead);
            } catch (InvalidParameterException e) {
                PrintText.wrongIdFormat();
            } catch (ExistentElementException e) {
                PrintText.iDAlreadyExists(e.getMessage());
            }
        } else {
            PrintText.cannotCreateLeadWithoutSalesReps();
        }
    }

    public void createSalesRep(){

        SalesRep newSalesRep = salesRepService.createSalesRep();

        try {
            salesRepService.save(newSalesRep);
            PrintText.salesRepCreated(newSalesRep);
        }
        catch (InvalidParameterException e) {
            PrintText.wrongIdFormat();
        } catch (ExistentElementException e) {
            PrintText.iDAlreadyExists(e.getMessage());
        }

    }

    public void showSalesRep(){
        List<SalesRep> salesReps = salesRepService.getAll();
        if (salesReps.size() == 0){
            PrintText.noSalesRepYet();
        } else {
            PrintText.headerShowSalesRep();
            for (SalesRep salesRep : salesReps){
                System.out.println("    ID " + salesRep.getId() + "  |  Name: " + salesRep.getName());
                System.out.println(Colors.BG_WHITE);
            }
        }
    }


    public void showLeads(){
        List<Lead> leads = leadService.getAll();
        if (leads.size() == 0){
            PrintText.noLeadsYet();
        } else {
            PrintText.headerShowLeads();
            for (Lead lead : leads){
                System.out.println("    ID " + lead.getId() + "  |  Name: " + lead.getName());
                System.out.println(Colors.BG_WHITE);
            }
        }
    }

    public void lookupLead(Long id){
        try{
            PrintText.printLead(leadService.findById(id));
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowLeads();
        }
    }

    public void convertLead(Long id) {
        try {
            Lead lead = leadService.findById(id);

            //Create the contact from the lead
            Contact contact = lead.convertLead();
            Opportunity opportunity = opportunityService.obtainOpportunity(contact, lead.getSalesRep());
            Account account = accountService.obtainAccount(contact, opportunity);

            leadService.deleteById(id);
            accountService.save(account);
            contactService.save(contact);
            opportunityService.save(opportunity);
            PrintText.leadConverted(lead, opportunity);

        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowLeads();
        } catch (ExistentElementException e) {
            PrintText.iDAlreadyExists(e.getMessage());
        }
    }

    public void showOpportunities(){
        List<Opportunity> opportunities = opportunityService.getAll();
        if (opportunities.size() == 0){
            PrintText.noOpportunitiesYet();
        } else{

            PrintText.headerShowOpportunities();
            for (Opportunity opportunity : opportunities){
                System.out.println(opportunity.toStringFormatted());
                System.out.println(Colors.BG_WHITE);
            }
        }
    }

    public void lookupOpportunity(Long id){
        try{
            Opportunity opportunity = opportunityService.findById(id);
            PrintText.printOpportunity(opportunity);
            Account account = opportunityService.findAccountByOpportunity(opportunity);
            PrintText.printAccountWithoutOpportunity(account);
            PrintText.showContactsByAccount(account);
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
        }
    }

    public int closeLost (Long id)  {
        try{
            return opportunityService.closeLost(id);
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
            return -9;
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
            return -9;
        }
    }

    public int closeWon (Long id)  {
        try{
            return opportunityService.closeWon(id);
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
            return -10;
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
            return -10;
        }
    }

    public void showAccounts(){
        List<Account> accounts = accountService.getAll();
        if (accounts.size() == 0){
            PrintText.noAccountsYet();
        } else{

            PrintText.headerShowAccounts();
            for (Account account : accounts){
                System.out.println("Account: " + account.toStringFormattedWithoutOpportunityAndContact());
            }
        }
    }


    public void showContacts(){
        List<Contact> contacts = contactService.getAll();
        if (contacts.size() == 0){
            PrintText.noContactsYet();
        } else{

            PrintText.headerShowContacts();
            for (Contact contact : contacts){
                System.out.println("ID " + contact.getId());
                System.out.println("Account: " + contact.toStringFormatted());
            }
        }
    }

    public void lookupAccount(Long id){
        try{
            PrintText.printAccount(accountService.findById(id));
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
        }
    }

    public void lookupContact(Long id) {
        try {
            PrintText.printContact(contactService.findById(id));
        } catch (InvalidParameterException e) {
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e) {
            PrintText.noIdFound(e.getMessage());
        }
    }

    public void showLeadsBySalesRep(){
        List<Object[]> report = salesRepService.showLeadsBySalesRep();
        PrintText.printReportBySalesRep(report);
    }

    public void showOpportunitiesBySalesRep(){
        List<Object[]> report = salesRepService.showOpportunitiesBySalesRep();
        PrintText.printReportBySalesRep(report);
    }

    public void showClosedWonOpportunitiesBySalesRep(){
        List<Object[]> report = salesRepService.showClosedWonOpportunitiesBySalesRep();
        PrintText.printReportBySalesRep(report);
    }

    public void showClosedLostOpportunitiesBySalesRep(){
        List<Object[]> report = salesRepService.showClosedLostOpportunitiesBySalesRep();
        PrintText.printReportBySalesRep(report);
    }

    public void showOpenOpportunitiesBySalesRep(){
        List<Object[]> report = salesRepService.showOpenOpportunitiesBySalesRep();
        PrintText.printReportBySalesRep(report);
    }

    public void showOpportunitiesByProduct(){
        List<Object[]> report = opportunityService.opportunitiesByProduct();
        PrintText.printOpportunitiesReport(report);
    }


    public void showOpenOpportunitiesByProduct(){
        List<Object[]> report = opportunityService.openOpportunitiesByProduct();
        PrintText.printOpportunitiesReport(report);
    }

    public void showWonOpportunitiesByProduct(){
        List<Object[]> report = opportunityService.wonOpportunitiesByProduct();
        PrintText.printOpportunitiesReport(report);
    }

    public void showLostOpportunitiesByProduct(){
        List<Object[]> report = opportunityService.lostOpportunitiesByProduct();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpportunitiesByCountry(){
        List<Object[]> report = opportunityService.opportunitiesByCountry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpenOpportunitiesByCountry(){
        List<Object[]> report = opportunityService.openOpportunitiesByCountry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showWonOpportunitiesByCountry(){
        List<Object[]> report = opportunityService.wonOpportunitiesByCountry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showLostOpportunitiesByCountry(){
        List<Object[]> report = opportunityService.lostOpportunitiesByCountry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpportunitiesByCity(){
        List<Object[]> report = opportunityService.opportunitiesByCity();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpenOpportunitiesByCity(){
        List<Object[]> report = opportunityService.openOpportunitiesByCity();
        PrintText.printOpportunitiesReport(report);
    }

    public void showWonOpportunitiesByCity(){
        List<Object[]> report = opportunityService.wonOpportunitiesByCity();
        PrintText.printOpportunitiesReport(report);
    }

    public void showLostOpportunitiesByCity(){
        List<Object[]> report = opportunityService.lostOpportunitiesByCity();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpportunitiesByIndustry(){
        List<Object[]> report = opportunityService.opportunitiesByIndustry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showOpenOpportunitiesByIndustry(){
        List<Object[]> report = opportunityService.openOpportunitiesByIndustry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showWonOpportunitiesByIndustry(){
        List<Object[]> report = opportunityService.wonOpportunitiesByIndustry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showLostOpportunitiesByIndustry(){
        List<Object[]> report = opportunityService.lostOpportunitiesByIndustry();
        PrintText.printOpportunitiesReport(report);
    }

    public void showMeanEmployeeCount(){
        try {
            double report = opportunityService.getMeanEmployeeCount();
            PrintText.printStatisticDouble("Mean of count of employees", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMedianEmployeeCount(){
        try {
            BigDecimal report = opportunityService.getMedianEmployeeCount();
            PrintText.printStatisticBigDecimal("Median of count of employees", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMaxEmployeeCount(){
        try {
            int report = opportunityService.getMaxEmployeeCount();
            PrintText.printStatisticInteger("Maximum employees count", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMinEmployeeCount(){
        try {
            int report = opportunityService.getMinEmployeeCount();
            PrintText.printStatisticInteger("Minimum employees count", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMeanQuantityOfProducts(){
        try {
            double report = opportunityService.getMeanQuantityOfProducts();
            PrintText.printStatisticDouble("Mean of quantity of products", report);
        } catch (IllegalStateException e){
            PrintText.noOpportunitiesYet();
        }
    }

    public void showMedianQuantityOfProducts(){
        try {
            BigDecimal report = opportunityService.getMedianQuantityOfProducts();
            PrintText.printStatisticBigDecimal("Median of quantity of products", report);
        } catch (IllegalStateException e){
            PrintText.noOpportunitiesYet();
        }
    }

    public void showMaxQuantityOfProducts(){
        try {
            int report = opportunityService.getMaxQuantityOfProducts();
            PrintText.printStatisticInteger("Maximum quantity of products", report);
        } catch (IllegalStateException e){
            PrintText.noOpportunitiesYet();
        }
    }

    public void showMinQuantityOfProducts(){
        try {
            int report = opportunityService.getMinQuantityOfProducts();
            PrintText.printStatisticInteger("Minimum quantity of products", report);
        } catch (IllegalStateException e){
            PrintText.noOpportunitiesYet();
        }
    }

    public void showMeanOpportunitiesPerAccount(){
        try {
            double report = opportunityService.getMeanOpportunitiesPerAccount();
            PrintText.printStatisticDouble("Mean of opportunities per account", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMedianOpportunitiesPerAccount(){
        try {
            BigDecimal report = opportunityService.getMedianOpportunitiesPerAccount();
            PrintText.printStatisticBigDecimal("Median of opportunities per account", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMaxOpportunitiesPerAccount(){
        try {
            int report = opportunityService.getMaxOpportunitiesPerAccount();
            PrintText.printStatisticInteger("Maximum opportunities per account", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

    public void showMinOpportunitiesPerAccount(){
        try {
            int report = opportunityService.getMinOpportunitiesPerAccount();
            PrintText.printStatisticInteger("Minimum opportunities per account", report);
        } catch (IllegalStateException e){
            PrintText.noAccountsYet();
        }
    }

}
