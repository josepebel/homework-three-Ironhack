package com.homework3.homework3overriders.repository;

import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepRepositoryTest {

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

    private SalesRep salesRep1;
    private SalesRep salesRep2;
    private SalesRep salesRep3;

    private Lead lead1;
    private Lead lead2;
    private Lead lead3;
    private Lead lead4;

    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact contact4;
    private Contact contact5;

    private Account account1;
    private Account account2;
    private Account account3;

    private Opportunity opportunity1;
    private  Opportunity opportunity2;
    private  Opportunity opportunity3;
    private  Opportunity opportunity4;
    private  Opportunity opportunity5;
    private  Opportunity opportunity6;


    @BeforeEach
    void setUp() {

        salesRep1 = new SalesRep("David Diaz");
        salesRep2 = new SalesRep("Maria Campaña");
        salesRep3 = new SalesRep("Jose Peño");

        lead1 = new Lead("Luis","666666666","luis@gmail.com","Citroen");
        lead2 = new Lead("Pablo Gutierrez","666666666","pablo@gmail.com","VW");
        lead3 = new Lead("Javier Gutierrez","666666666","javier@gmail.com","Seat");
        lead4 = new Lead("Luis Javier","666666666","luisjavier@gmail.com","Mercedes");

        lead1.setSalesRep(salesRep1);
        lead2.setSalesRep(salesRep1);
        lead3.setSalesRep(salesRep2);
        lead4.setSalesRep(salesRep3);

        salesRepRepository.saveAll(List.of(salesRep1,salesRep2,salesRep3));
        leadRepository.saveAll(List.of(lead1,lead2,lead3,lead4));

        contact1 = new Contact("Diego Ruiz","6667666666","diego@gmail.com","Dacia");
        contact2 = new Contact("Abel Ruiz","6667666666","diego@gmail.com","Dacia");
        contact3 = new Contact("Gerard Moreno","6667666666","diego@gmail.com","Jansen");
        contact4 = new Contact("Pablo Sanabria","6667666666","diego@gmail.com","Amazon");
        contact5 = new Contact("Alvaro Benitez","6667656666","alvaro@gmail.com","Amazon");

        contactRepository.saveAll(List.of(contact1,contact2,contact3,contact4,contact5));

        opportunity1 = new Opportunity(Product.BOX,500,contact1, Status.OPEN,salesRep1);
        opportunity2 = new Opportunity(Product.BOX,1500,contact2, Status.OPEN,salesRep1);
        opportunity3 = new Opportunity(Product.HYBRID,100,contact3, Status.CLOSED_LOST,salesRep2);
        opportunity4 = new Opportunity(Product.FLATBED,10000,contact4, Status.CLOSED_WON,salesRep3);
        opportunity5 = new Opportunity(Product.FLATBED,20,contact5, Status.OPEN,salesRep3);
        opportunity6 = new Opportunity(Product.BOX,50,contact5,Status.CLOSED_WON,salesRep3);

        opportunityRepository.saveAll(List.of(opportunity1,opportunity2,opportunity3,opportunity4,opportunity5,opportunity6));

/*       account1 = new Account(Industry.OTHER,1000,"Madrid","España");
        account1.setOpportunities(List.of(opportunity1,opportunity2));
        account2 = new Account(Industry.MEDICAL,3000,"Frankfurt","Alemania");
        account2.setOpportunities(List.of(opportunity3));
        account3 = new Account(Industry.ECOMMERCE,6000,"Londres","Reino Unido");
        account3.setOpportunities(List.of(opportunity4,opportunity5,opportunity6));

        accountRepository.saveAll(List.of(account1,account2,account3));*/

    }

    @AfterEach
    void tearDown() {

        leadRepository.deleteAll();
        accountRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
    }


    @Nested
    @DisplayName("Lead By Sales Rep Test")
    class leadBySalesRep {


        @Test
        @DisplayName("Lead by Sales Rep - Size")
        void leadBySalesRep_listExtracted() {

            List<Object[]> leadBySalesRep = salesRepRepository.leadBySalesRep();
            for (Object[] row : leadBySalesRep) {
                String name = (String) row[0];
                Long count = (Long) row[1];
                System.out.println(name + " " + count);
            }

            assertEquals(3, leadBySalesRep.size());
        }

        @Test
        @DisplayName("Lead by Sales Rep with 0 leads - Size")
        void leadBySalesRep_SalesRepWith0Leads_listExtracted() {
            lead4.setSalesRep(salesRep1);
            leadRepository.save(lead4);//dejamos el SalesRep4 sin LEAD
            List<Object[]> leadBySalesRep = salesRepRepository.leadBySalesRep();
            for (Object[] row : leadBySalesRep) {
                String name = (String) row[0];
                Long count = (Long) row[1];
                System.out.println(name + " " + count);
            }
            assertEquals(3, leadBySalesRep.size());
        }

        @Test
        @DisplayName("Lead by Sales Rep with no Sales Rep - Size")
        void leadBySalesRep_noSalesRep_listExtracted() {
            leadRepository.deleteAll();
            accountRepository.deleteAll();
            opportunityRepository.deleteAll();
            contactRepository.deleteAll();
            salesRepRepository.deleteAll();

            List<Object[]> leadBySalesRep = salesRepRepository.leadBySalesRep();
            for (Object[] row : leadBySalesRep) {
                String name = (String) row[0];
                Long count = (Long) row[1];
                System.out.println(name + " " + count);
            }
            assertEquals(0, leadBySalesRep.size());
        }


    }


    @Nested
    @DisplayName("Opportunities By Sales Rep Test")
    class opportunitiesBySalesRep {

        @Test
        @DisplayName("Opportunities By Sales Rep - Size and Total")
        void opportunitiesBySalesRep() {
            List<Object[]> opportunitiesBySalesRep = salesRepRepository.opportunitiesBySalesRep();
            Long totalOpportunities = (long)0;
            for (Object[] row: opportunitiesBySalesRep) {
                String name = (String)row[0];
                Long opportunitiesCount = (Long)row[1];
                System.out.println(name + " " + opportunitiesCount);
                totalOpportunities += opportunitiesCount;
            }

            assertEquals(3,opportunitiesBySalesRep.size()); //David y Jose have open opportunities
            assertEquals(6,totalOpportunities); //Total number of open opportunities is 3

    }


        @Test
        @DisplayName("Opportunities By Sales Rep - Sales Rep and opportunities")
        void countOpportunitiesBySalesRep() {
            List<Object[]> opportunitiesBySalesRep = salesRepRepository.opportunitiesBySalesRep();
            assertEquals("David Diaz", opportunitiesBySalesRep.get(0)[0]);
            assertEquals(2L, opportunitiesBySalesRep.get(0)[1]);
            assertEquals("Maria Campaña", opportunitiesBySalesRep.get(1)[0]);
            assertEquals(1L, opportunitiesBySalesRep.get(1)[1]);
            assertEquals("Jose Peño", opportunitiesBySalesRep.get(2)[0]);
            assertEquals(3L, opportunitiesBySalesRep.get(2)[1]);
        }

        @Test
        @DisplayName("Opportunities By Sales Rep with no opportunities- Size and Total")
        void opportunitiesBySalesRep_salesRepWithNoOpportunities() {
            opportunity3.setSalesRep(salesRep3);
            opportunityRepository.save(opportunity3);
            List<Object[]> opportunitiesBySalesRep = salesRepRepository.opportunitiesBySalesRep();
            Long totalOpportunities = (long) 0;
            for (Object[] row : opportunitiesBySalesRep) {
                String name = (String) row[0];
                Long opportunitiesCount = (Long) row[1];
                System.out.println(name + " " + opportunitiesCount);
                totalOpportunities += opportunitiesCount;
            }

            assertEquals(3, opportunitiesBySalesRep.size()); //David y Jose have open opportunities
            assertEquals(6, totalOpportunities); //Total number of open opportunities is 3

        }

        @Test
        @DisplayName("Close Won Opportunities By Sales Rep - Size and Sales Rep with opportunities")
        void closedWonOpportunitiesBySalesRep_listExtracted() {
            List<Object[]> opportunitiesWonBySalesRep = salesRepRepository.closedWonOpportunitiesBySalesRep();
            String concats = "";
            for (Object[] row : opportunitiesWonBySalesRep) {
                String name = (String) row[0];
                BigInteger closedWonCount = (BigInteger) row[1];
                concats += name + "-" + closedWonCount.toString();
                System.out.println(name + " " + closedWonCount);
            }
            System.out.println(concats);
            assertEquals(3, opportunitiesWonBySalesRep.size()); //Include 0 values
            assertTrue(concats.contains("Jose Peño-2"));

        }

        @Test
        @DisplayName("Close Lost Opportunities By Sales Rep - Size and Sales Rep with opportunities")
        void closedLostOpportunitiesBySalesRep_listExtracted() {
            List<Object[]> opportunitiesLostBySalesRep = salesRepRepository.closedLostOpportunitiesBySalesRep();
            String concats = "";
            for (Object[] row : opportunitiesLostBySalesRep) {
                String name = (String) row[0];
                BigInteger closedLostCount = (BigInteger) row[1];
                concats += name + "-" + closedLostCount.toString();
                System.out.println(name + " " + closedLostCount);
            }

            System.out.println(concats);
            assertEquals(3, opportunitiesLostBySalesRep.size());
            assertTrue(concats.contains("Maria Campaña-1"));
        }

        @Test
        @DisplayName("Open Opportunities By Sales Rep - Size and Sales Rep with opportunities")
        void openOpportunitiesBySalesRep_listExtracted() {
            List<Object[]> opportunitiesOpenBySalesRep = salesRepRepository.openOpportunitiesBySalesRep();
            Integer totalOpenOpportunities = (Integer) 0;
            String concats = "";
            for (Object[] row : opportunitiesOpenBySalesRep) {
                String name = (String) row[0];
                BigInteger openCount = (BigInteger) row[1];
                concats += name + "-" + openCount.toString();
                System.out.println(name + " " + openCount);
                totalOpenOpportunities += openCount.intValue();
            }

            System.out.println(concats);
            assertEquals(3, opportunitiesOpenBySalesRep.size());
            assertTrue(concats.contains("David Diaz-2"));
            assertEquals(3, totalOpenOpportunities); //Total number of open opportunities is 3
        }

    }
}