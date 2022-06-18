package com.homework3.homework3overriders.supportiveClasses;

import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.*;
import com.homework3.homework3overriders.repository.*;
import com.homework3.homework3overriders.service.interfaces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InteractionsTest {

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

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private Interactions interactions = new Interactions();

    private SalesRep salesRep1;
    private SalesRep salesRep2;

    private Lead lead1;
    private Lead lead2;

    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact contact4;
    private Contact contact5;
    private Contact contact6;
    private Contact contact7;
    private Contact contact8;

    private Account account1;
    private Account account2;
    private Account account3;
    private Account account4;
    private Account account5;
    private Account account6;

    private Opportunity opportunity1;
    private Opportunity opportunity2;
    private Opportunity opportunity3;
    private Opportunity opportunity4;
    private Opportunity opportunity5;
    private Opportunity opportunity6;


    @BeforeEach
    void setUp() {
        account1 = new Account(Industry.ECOMMERCE, 25, "Madrid", "España");
        account2 = new Account(Industry.MANUFACTURING, 150, "Ankara", "Turquía");
        account3 = new Account(Industry.MEDICAL, 2500, "Wuhan", "China");
        account4 = new Account(Industry.ECOMMERCE, 120, "Berlin", "Alemania");
        account5 = new Account(Industry.OTHER, 78, "Frankfurt", "Alemania");
        account6 = new Account(Industry.OTHER, 250, "Londres", "Inglaterra");

        contact1 = new Contact("Diego Ruiz","666123123","diegoruiz@gmail.com","Zalando");
        contact2 = new Contact("Abel Ruiz","666234234","abelruiz@gmail.com","Zalando");
        contact3 = new Contact("Pepe Pérez", "888999000", "pepepere@gmail.com", "Manufacturas Perez");
        contact4 = new Contact("Manolo Bacteria","666999777","manolobacteria@gmail.com","Norton Antivirus");
        contact5 = new Contact("Ricardo Virus","666999666","ricardovirus@gmail.com","Norton Antivirus");
        contact6 = new Contact("Gunther Fragher","333999666","guntherfragher@gmail.com","Ebay");
        contact7 = new Contact("Anna Müller","333555666","annamuller@gmail.com","Volkswagen");
        contact8 = new Contact("Michael Nyman","222444555","eldelpiano@gmail.com","Pianos Nyman");

        account1.setContacts(List.of(contact1, contact2));
        account2.setContacts(List.of(contact3));
        account3.setContacts(List.of(contact5, contact4));
        account4.setContacts(List.of(contact6));
        account5.setContacts(List.of(contact7));
        account6.setContacts(List.of(contact8));

        accountRepository.saveAll(List.of(account1, account2, account3, account4, account5, account6));
        contactRepository.saveAll(List.of(contact1, contact2, contact3, contact4, contact5, contact6, contact7, contact8));

        salesRep1 = new SalesRep("Dwight Schrute");
        salesRep2 = new SalesRep("Jim Halpert");

        salesRepRepository.saveAll(List.of(salesRep1, salesRep2));

        lead1 = new Lead("Pepe Lopez", "687456456", "pepelopez@pepelopez.com", "Mudanzas Lopez");
        lead2 = new Lead("Miguel Boro", "666432344", "miguelboro@gmail.com", "Dropshipping Boro");

        leadRepository.saveAll(List.of(lead1, lead2));

        opportunity1 = new Opportunity(Product.BOX, 10, contact1, Status.OPEN, salesRep1);
        opportunity1.setAccount(account1);
        opportunity2 = new Opportunity(Product.FLATBED, 6, contact3, Status.CLOSED_WON, salesRep2);
        opportunity2.setAccount(account2);
        opportunity3 = new Opportunity(Product.HYBRID, 4, contact5, Status.CLOSED_WON, salesRep1);
        opportunity3.setAccount(account3);
        opportunity4 = new Opportunity(Product.FLATBED, 6, contact6, Status.CLOSED_LOST, salesRep2);
        opportunity4.setAccount(account4);
        opportunity5 = new Opportunity(Product.BOX, 10, contact7, Status.CLOSED_WON, salesRep1);
        opportunity5.setAccount(account5);
        opportunity6 = new Opportunity(Product.FLATBED, 6, contact8, Status.OPEN, salesRep2);
        opportunity6.setAccount(account6);

        opportunityRepository.saveAll(List.of(opportunity1,opportunity2,opportunity3,opportunity4,opportunity5,opportunity6));

    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
    }


//    Lead Methods

    @Test
    void createLead_ValidLead_LeadAdded() {
        Lead lead3 = new Lead("Mónica", "657896012", "moni_ca@gmail.com", "Noshe");
        leadRepository.save(lead3);
        List<Lead> leadList = leadRepository.findAll();
        assertEquals(3, leadList.size());
        leadList = leadRepository.findAll();
        assertEquals(3, leadList.size());
    }

    @Test
    void createLead_NoSalesRep_NoLeadAdded() {
        salesRepRepository.deleteAll();
        interactions.createLead();
        assertEquals(0, salesRepRepository.findAll().size());
        assertEquals(2, leadRepository.findAll().size());
    }

    @Test
    void showLeads_NoParams_LeadsShown() {
        interactions.showLeads();
        List<Lead> leads = leadRepository.findAll();
        assertEquals(2, leads.size());
        leads = leadService.getAll();
        assertEquals(2, leads.size());
    }

    @Test
    void lookupLead_ValidId_LeadFound() {
        Optional<Lead> optionalLead = leadRepository.findById(lead1.getId());
        assertTrue(optionalLead.isPresent());
        assertEquals("Pepe Lopez", optionalLead.get().getName());
        Optional<Lead> optionalLead2 = leadRepository.findById(lead2.getId());
        assertTrue(optionalLead2.isPresent());
        assertEquals("Miguel Boro", optionalLead2.get().getName());
    }

    @Test
    void lookupLead_IdLessThanZero_ThrowsException() {
        assertThrows(InvalidParameterException.class, () -> {
            leadService.findById(-1L);
        });
    }

    @Test
    void lookupLead_InvalidId_ThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            leadService.findById(4563434L);
        });
    }

    @Test
    void convertLead_InvalidId_NoLeadConverted() {
        interactions.convertLead(3457863378765L);
        assertEquals(2, leadRepository.findAll().size());
        assertEquals(6, opportunityRepository.findAll().size());
    }

    // Opportunity Methods

    @Test
    void showOpportunities() {
        interactions.showOpportunities();
        List<Opportunity> opportunities = opportunityRepository.findAll();
        assertEquals(6, opportunities.size());
        opportunities = opportunityService.getAll();
        assertEquals(6, opportunities.size());
    }

    @Test
    void lookupOpportunity_ValidId_OppoFound() {
        Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(opportunity1.getId());
        assertTrue(optionalOpportunity.isPresent());
        assertEquals("Diego Ruiz", optionalOpportunity.get().getDecisionMaker().getName());
        Optional<Opportunity> optionalOpportunity2 = opportunityRepository.findById(opportunity2.getId());
        assertTrue(optionalOpportunity2.isPresent());
        assertEquals("Pepe Pérez", optionalOpportunity2.get().getDecisionMaker().getName());
    }
    @Test
    void lookupOpportunity_InvalidId_ThrowException() {
        interactions.lookupOpportunity(234234L);
        assertThrows(NoSuchElementException.class, () -> {
            opportunityService.findById(4563434L);
        });
    }

    @Test
    void closeLost_ValidOpportunity_StatusChanged() {
        assertEquals("OPEN", opportunity1.getStatus().name());
        opportunityService.closeLost(opportunity1.getId());
        opportunity1.setStatus(Status.CLOSED_LOST);
        assertEquals("CLOSED_LOST", opportunity1.getStatus().name());
    }

    @Test
    void closeWon_Valid_Opportunity_StatusChanged() {
        assertEquals("OPEN", opportunity1.getStatus().name());
        opportunityService.closeWon(opportunity1.getId());
        opportunity1.setStatus(Status.CLOSED_WON);
        assertEquals("CLOSED_WON", opportunity1.getStatus().name());
    }


//    Account Methods

    @Test
    void showAccounts() {
        interactions.showAccounts();
        List<Account> accounts = accountRepository.findAll();
        assertEquals(6, accounts.size());
    }

    @Test
    void lookupAccount_ValidId_AccountFound() {
        Optional<Account> optionalAccount = accountRepository.findById(account1.getId());
        assertTrue(optionalAccount.isPresent());
        assertEquals("Madrid", optionalAccount.get().getCity());
        Optional<Account> optionalAccount2 = accountRepository.findById(account2.getId());
        assertTrue(optionalAccount2.isPresent());
        assertEquals("Ankara", optionalAccount2.get().getCity());
    }

    @Test
    void lookupAccount_InvalidId_ThrowException() {
        interactions.lookupAccount(234234L);
        assertThrows(NoSuchElementException.class, () -> {
            accountService.findById(4563434L);
        });
    }


//    Contact Methods

    @Test
    void showContacts() {
        interactions.showContacts();
        List<Contact> contacts = contactRepository.findAll();
        assertEquals(8, contacts.size());
        contacts = contactService.getAll();
        assertEquals(8, contacts.size());
    }

    @Test
    void lookupContact_ValidId_ContactFoung() {
        Optional<Contact> optionalContact = contactRepository.findById(contact1.getId());
        assertTrue(optionalContact.isPresent());
        assertEquals("Diego Ruiz", optionalContact.get().getName());
        Optional<Contact> optionalContact2 = contactRepository.findById(contact2.getId());
        assertTrue(optionalContact2.isPresent());
        assertEquals("Abel Ruiz", optionalContact2.get().getName());
    }

    @Test
    void lookupContact_InvalidId_ThrowException() {
        interactions.lookupContact(234234L);
        assertThrows(NoSuchElementException.class, () -> {
            accountService.findById(4563434L);
        });
    }


//    SalesRep Methods

    @Test
    void createSalesRep() {
        SalesRep salesRep3 = new SalesRep("Mónica");
        salesRepRepository.save(salesRep3);
        List<SalesRep> salesRepList = salesRepService.getAll();
        assertEquals(3, salesRepList.size());
        salesRepList = salesRepRepository.findAll();
        assertEquals(3, salesRepList.size());
    }

    @Test
    void showSalesRep() {
        interactions.showSalesRep();
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertEquals(2, salesRepList.size());
        salesRepList = salesRepService.getAll();
        assertEquals(2, salesRepList.size());
    }

}