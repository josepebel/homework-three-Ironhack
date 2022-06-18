package com.homework3.homework3overriders.repository;

import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OpportunityRepositoryTest {

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

    private Lead lead1;

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

        opportunity1 = new Opportunity(Product.BOX, 10, contact1, Status.OPEN, salesRep1);
        opportunity1.setAccount(account1);
        opportunity2 = new Opportunity(Product.FLATBED, 6, contact3, Status.CLOSED_WON, salesRep2);
        opportunity2.setAccount(account2);
        opportunity3 = new Opportunity(Product.HYBRID, 2, contact5, Status.CLOSED_WON, salesRep1);
        opportunity3.setAccount(account3);
        opportunity4 = new Opportunity(Product.FLATBED, 8, contact6, Status.CLOSED_LOST, salesRep2);
        opportunity4.setAccount(account4);
        opportunity5 = new Opportunity(Product.BOX, 10, contact7, Status.CLOSED_WON, salesRep1);
        opportunity5.setAccount(account5);
        opportunity6 = new Opportunity(Product.FLATBED, 6, contact8, Status.OPEN, salesRep2);
        opportunity6.setAccount(account6);

        opportunityRepository.saveAll(List.of(opportunity1,opportunity2,opportunity3,opportunity4,opportunity5,opportunity6));

        // ========= ID RESET ========= //

        account1.setId(1L);
        account2.setId(2L);
        account3.setId(3L);
        account4.setId(4L);
        account5.setId(5L);
        account6.setId(6L);

        contact1.setId(1L);
        contact2.setId(2L);
        contact3.setId(3L);
        contact4.setId(4L);
        contact5.setId(5L);
        contact6.setId(6L);
        contact7.setId(7L);
        contact8.setId(8L);

        salesRep1.setId(1L);
        salesRep2.setId(2L);

        opportunity1.setId(1L);
        opportunity2.setId(2L);
        opportunity3.setId(3L);
        opportunity4.setId(4L);
        opportunity5.setId(5L);
        opportunity6.setId(6L);
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        salesRepRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        leadRepository.deleteAll();
    }

    @Nested
    @DisplayName("Opportunities by Product Tests")
    class OpportunitiesByProduct{
    @Test
    @DisplayName("Opportunities by Product - Size")
    void opportunitiesByProduct_validList_CorrectSize() {
        List<Object[]> opportunities = opportunityRepository.opportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.size(), 3);
    }

    @Test
    @DisplayName("Opportunities by Product - Product and Quantity")
    void opportunitiesByProduct_validList_CorrectProduct() {
        List<Object[]> opportunities = opportunityRepository.opportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }

        assertEquals(opportunities.get(0)[0], Product.BOX);
        assertEquals(opportunities.get(0)[1], 2L);
        assertEquals(opportunities.get(1)[0], Product.FLATBED);
        assertEquals(opportunities.get(1)[1], 3L);
        assertEquals(opportunities.get(2)[0], Product.HYBRID);
        assertEquals(opportunities.get(2)[1], 1L);

    }


    @Test
    @DisplayName("Open Opportunities by Product - Size")
    void openOpportunitiesByProduct_validList_CorrectSize() {
        List<Object[]> opportunities = opportunityRepository.openOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.size(), 2);
    }

    @Test
    @DisplayName("Open Opportunities by Product - Product and Quantity")
    void openOpportunitiesByProduct_validList_CorrectOpenProduct() {
        List<Object[]> opportunities = opportunityRepository.openOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];

        }


        assertEquals(opportunities.get(0)[0], Product.BOX);
        assertEquals(opportunities.get(0)[1], 1L);
        assertEquals(opportunities.get(1)[0], Product.FLATBED);
        assertEquals(opportunities.get(1)[1], 1L);
    }

    @Test
    @DisplayName("Won Opportunities by Product - Size")
    void wonOpportunitiesByProduct_validList_CorrectSize() {
        List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.size(), 3);
    }

    @Test
    @DisplayName("Won Opportunities by Product - Product and Quantity")
    void wonOpportunitiesByProduct_validList_CorrectWonProduct() {
        List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.get(0)[0], Product.FLATBED);
        assertEquals(opportunities.get(0)[1], 1L);
        assertEquals(opportunities.get(1)[0], Product.HYBRID);
        assertEquals(opportunities.get(1)[1], 1L);
        assertEquals(opportunities.get(2)[0], Product.BOX);
        assertEquals(opportunities.get(2)[1], 1L);
    }

    @Test
    @DisplayName("Lost Opportunities by Product - Size")
    void lostOpportunitiesByProduct_validList_CorrectSize() {
        List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.size(), 1);
    }

    @Test
    @DisplayName("Lost Opportunities by Product - Product and Quantity")
    void lostOpportunitiesByProduct_validList_CorrectLostProduct() {
        List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByProduct();
        for (Object[] row: opportunities) {
            Product product = (Product)row[0];
            String productName = product.name();
            Long count = (Long)row[1];
        }
        assertEquals(opportunities.get(0)[0], Product.FLATBED);
        assertEquals(opportunities.get(0)[1], 1L);
    }

    }


    @Nested
    @DisplayName("Opportunities by Country Tests")
    class OpportunitiesByCountry {

        @Test
        @DisplayName("Opportunities by Country - Size")
        void opportunitiesByCountry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 5);
        }

        @Test
        @DisplayName("Opportunities by Country - Country and Quantity")
        void opportunitiesByCountry_validList_CorrectCountry() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "España");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "Turquía");
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], "China");
            assertEquals(opportunities.get(2)[1], 1L);
            assertEquals(opportunities.get(3)[0], "Alemania");
            assertEquals(opportunities.get(3)[1], 2L);
            assertEquals(opportunities.get(4)[0], "Inglaterra");
            assertEquals(opportunities.get(4)[1], 1L);

        }

        @Test
        @DisplayName("Open Opportunities by Country - Size")
        void openOpportunitiesByCountry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 2);
        }

        @Test
        @DisplayName("Open Opportunities by Country - Country and Quantity")
        void openOpportunitiesByCountry_validList_CorrectCountry() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "España");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "Inglaterra");
            assertEquals(opportunities.get(1)[1], 1L);
        }

        @Test
        @DisplayName("Won Opportunities by Country - Size")
        void wonOpportunitiesByCountry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 3);
        }

        @Test
        @DisplayName("Won Opportunities by Country - Country and Quantity")
        void wonOpportunitiesByCountry_validList_CorrectCountry() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Turquía");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "China");
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], "Alemania");
            assertEquals(opportunities.get(2)[1], 1L);
        }

        @Test
        @DisplayName("Lost Opportunities by Country - Size")
        void lostOpportunitiesByCountry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 1);
        }

        @Test
        @DisplayName("Lost Opportunities by Country - Country and Quantity")
        void lostOpportunitiesByCountry_validList_CorrectCountry() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByCountry();
            for (Object[] row : opportunities) {
                String country = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Alemania");
            assertEquals(opportunities.get(0)[1], 1L);
        }


    }

    @Nested
    @DisplayName("Opportunities by City Tests")
    class OpportunitiesByCity {

        @Test
        @DisplayName("Opportunities by City - Size")
        void opportunitiesByCity_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 6);
        }

        @Test
        @DisplayName("Opportunities by City - City and Quantity")
        void opportunitiesByCity_validList_CorrectCity() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Madrid");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "Ankara");
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], "Wuhan");
            assertEquals(opportunities.get(2)[1], 1L);
            assertEquals(opportunities.get(3)[0], "Berlin");
            assertEquals(opportunities.get(3)[1], 1L);
            assertEquals(opportunities.get(4)[0], "Frankfurt");
            assertEquals(opportunities.get(4)[1], 1L);
            assertEquals(opportunities.get(5)[0], "Londres");
            assertEquals(opportunities.get(5)[1], 1L);
        }

        @Test
        @DisplayName("Open Opportunities by City - Size")
        void openOpportunitiesByCity_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 2);
        }

        @Test
        @DisplayName("Open Opportunities by City - City and Quantity")
        void openOpportunitiesByCity_validList_CorrectCity() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Madrid");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "Londres");
            assertEquals(opportunities.get(1)[1], 1L);
        }

        @Test
        @DisplayName("Won Opportunities by City - Size")
        void wonOpportunitiesByCity_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 3);
        }

        @Test
        @DisplayName("Won Opportunities by City - City and Quantity")
        void wonOpportunitiesByCity_validList_CorrectCity() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Ankara");
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], "Wuhan");
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], "Frankfurt");
            assertEquals(opportunities.get(2)[1], 1L);
        }

        @Test
        @DisplayName("Lost Opportunities by City - Size")
        void lostOpportunitiesByCity_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 1);
        }

        @Test
        @DisplayName("Lost Opportunities by City - City and Quantity")
        void lostOpportunitiesByCity_validList_CorrectCity() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByCity();
            for (Object[] row : opportunities) {
                String city = (String) row[0];
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], "Berlin");
            assertEquals(opportunities.get(0)[1], 1L);
        }

    }


    @Nested
    @DisplayName("Opportunities by Industry Tests")
    class OpportunitiesByIndustry {



        @Test
        @DisplayName("Opportunities by Industry - Size")
        void opportunitiesByIndustry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 4);
        }

        @Test
        @DisplayName("Opportunities by Industry - Industry and Quantity")
        void opportunitiesByIndustry_validList_CorrectIndustry() {
            List<Object[]> opportunities = opportunityRepository.opportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], Industry.ECOMMERCE);
            assertEquals(opportunities.get(0)[1], 2L);
            assertEquals(opportunities.get(1)[0], Industry.MANUFACTURING);
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], Industry.MEDICAL);
            assertEquals(opportunities.get(2)[1], 1L);
            assertEquals(opportunities.get(3)[0], Industry.OTHER);
            assertEquals(opportunities.get(3)[1], 2L);
        }

        @Test
        @DisplayName("Open Opportunities by Industry - Size")
        void openOpportunitiesByIndustry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 2);
        }

        @Test
        @DisplayName("Open Opportunities by Industry - Industry and Quantity")
        void openOpportunitiesByIndustry_validList_CorrectIndustry() {
            List<Object[]> opportunities = opportunityRepository.openOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], Industry.ECOMMERCE);
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], Industry.OTHER);
            assertEquals(opportunities.get(1)[1], 1L);
        }

        @Test
        @DisplayName("Won Opportunities by Industry - Size")
        void wonOpportunitiesByIndustry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 3);
        }

        @Test
        @DisplayName("Won Opportunities by Industry - Industry and Quantity")
        void wonOpportunitiesByIndustry_validList_CorrectIndustry() {
            List<Object[]> opportunities = opportunityRepository.wonOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], Industry.MANUFACTURING);
            assertEquals(opportunities.get(0)[1], 1L);
            assertEquals(opportunities.get(1)[0], Industry.MEDICAL);
            assertEquals(opportunities.get(1)[1], 1L);
            assertEquals(opportunities.get(2)[0], Industry.OTHER);
            assertEquals(opportunities.get(2)[1], 1L);
        }

        @Test
        @DisplayName("Lost Opportunities by Industry - Size")
        void lostOpportunitiesByIndustry_validList_CorrectSize() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.size(), 1);
        }

        @Test
        @DisplayName("Lost Opportunities by Industry - Industry and Quantity")
        void lostOpportunitiesByIndustry_validList_CorrectIndustry() {
            List<Object[]> opportunities = opportunityRepository.lostOpportunitiesByIndustry();
            for (Object[] row : opportunities) {
                Industry industryEnum = (Industry) row[0];
                String industry = industryEnum.name();
                Long count = (Long) row[1];
            }
            assertEquals(opportunities.get(0)[0], Industry.ECOMMERCE);
            assertEquals(opportunities.get(0)[1], 1L);
        }
    }



    @Nested
    @DisplayName("EmployeeCount States Tests")
    class EmployeeCountStates {


        @Test
        @DisplayName("EmployeeCount Mean")
        void getMeanEmployeeCount_validAccountList_correctMean() {
            double result = opportunityRepository.getMeanEmployeeCount();
            assertEquals(result, 520.5);
        }

        @Test
        @DisplayName("EmployeeCount Median")
        void getMedianEmployeeCount_validAccountList_correctMedian() {
            opportunityRepository.setRowIndex();
            List<Object[]> result = opportunityRepository.getMedianEmployeeCount();

            assertEquals(result.get(0)[0], new BigDecimal("135.0000"));
        }


        @Test
        @DisplayName("EmployeeCount Max")
        void getMaxEmployeeCount_validAccountList_correctMax() {
            int result = opportunityRepository.getMaxEmployeeCount();
            assertEquals(result, 2500);
        }

        @Test
        @DisplayName("EmployeeCount Min")
        void getMinEmployeeCount_validAccountList_correctMin() {
            int result = opportunityRepository.getMinEmployeeCount();
            assertEquals(result, 25);

        }

    }

    //Quantity States


    @Nested
    @DisplayName("Quantity States Tests")
    class QuantityStates {


        @Test
        @DisplayName("Quantity Mean")
        void getMeanQuantityOfProducts_validOpportunityList_correctMean() {
            double result = opportunityRepository.getMeanQuantityOfProducts();
            assertEquals(result, 7);
        }

        @Test
        @DisplayName("Quantity Median")
        void getMedianQuantityOfProducts_validOpportunityList_correctMedian() {
            opportunityRepository.setRowIndex();
            List<Object[]> result = opportunityRepository.getMedianQuantityOfProducts();

            assertEquals(result.get(0)[0], new BigDecimal("7.0000"));
        }


        @Test
        @DisplayName("Quantity Max")
        void getMaxQuantityOfProducts_validOpportunityList_correctMax() {
            int result = opportunityRepository.getMaxQuantityOfProducts();
            assertEquals(result, 10);
        }

        @Test
        @DisplayName("Quantity Min")
        void getMinQuantityOfProducts_validOpportunityList_correctMin() {
            int result = opportunityRepository.getMinQuantityOfProducts();
            assertEquals(result, 2);
        }


    }

    //Opportunity States

    @Nested
    @DisplayName("Opportunity States Tests")
    class OpportunityStates {

        @Test
        @DisplayName("Quantity Mean")
        void GetMeanOpportunitiesPerAccount_existingAccount_correctMean() {
            double result = opportunityRepository.getMeanOpportunitiesPerAccount();
            assertEquals(result, 1);
        }

        @Test
        @DisplayName("Quantity Median")
        void getMedianOpportunitiesPerAccount_existingAccount_correctMedian() {
            opportunityRepository.setRowIndex();
            List<Object[]> result = opportunityRepository.getMedianOpportunitiesPerAccount();
            assertEquals(result.get(0)[0], new BigDecimal("1.0000"));
        }

        @Test
        @DisplayName("Quantity Max")
        void GetMaxOpportunitiesPerAccount_existingAccount_correctMax() {
            int result = opportunityRepository.getMaxOpportunitiesPerAccount();
            assertEquals(result, 1);
        }

        @Test
        @DisplayName("Quantity Min")
        void GetMinOpportunitiesPerAccount_existingAccount_correctMin() {
            int result = opportunityRepository.getMinOpportunitiesPerAccount();
            assertEquals(result, 1);
        }
    }

}
