package com.homework3.homework3overriders.supportiveClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@Component
public class CheckCommands {

    @Autowired
    private Interactions interactions;
    private int state;

    public CheckCommands() {

        state = 0;
    }


    public void checkCommand (String command) throws IllegalArgumentException{

        String[] words = command.trim().split(" ");
        if (words.length ==2 && "New".equalsIgnoreCase(words[0]) && "Lead".equalsIgnoreCase(words[1])) {
            interactions.createLead();
            state = 1;

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "leads".equalsIgnoreCase(words[1])){
            interactions.showLeads();
            state = 2;

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("lead")){
            try {
                Long id = Long.parseLong(words[2]);
                interactions.lookupLead(id);
                state = 3;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -3;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -3;
            }

        } else if (words.length == 2 && words[0].equalsIgnoreCase("convert") ){
            try {
                Long id = Long.parseLong(words[1]);
                interactions.convertLead(id);
                state = 4;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -4;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -4;
            }

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "opportunities".equalsIgnoreCase(words[1])){
            interactions.showOpportunities();
            state = 5;

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "contacts".equalsIgnoreCase(words[1])){
            interactions.showContacts();
            state = 6;

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("opportunity")){
            try {
                Long id = Long.parseLong(words[2]);
                interactions.lookupOpportunity(id);
                state = 7;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -7;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -7;
            }

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("contact")){
            try {
                Long id = Long.parseLong(words[2]);
                interactions.lookupContact(id);
                state = 8;
            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -8;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -8;
            }

        } else if (words.length==2 && "close-lost".equalsIgnoreCase(words[0])){
            System.out.println();
            try {
                Long id = Long.parseLong(words[1]);
                state = interactions.closeLost(id);

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -9;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -9;
            }

        } else if (words.length==2 && "close-won".equalsIgnoreCase(words[0])){
             try {
                 Long id = Long.parseLong(words[1]);
                 state = interactions.closeWon(id);

             } catch (InputMismatchException e){
                 PrintText.wrongIdFormat();
                 state = -10;
             } catch (NumberFormatException e){
                 PrintText.wrongIdFormat();
                 state = -10;
             }

        } else if (words.length == 2 && "show".equalsIgnoreCase(words[0])&& "SalesReps".equalsIgnoreCase(words[1])) {
            interactions.showSalesRep();
            state = 11;

        } else if (words.length ==2 && "New".equalsIgnoreCase(words[0]) && "SalesRep".equalsIgnoreCase(words[1])) {
                interactions.createSalesRep();
                state = 12;

/////////////////////////////////Report by SalesRep   //////////////////////////////////////

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "Lead".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "SalesRep".equalsIgnoreCase(words[3])) {
            interactions.showLeadsBySalesRep();
            state = 13;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "Opportunity".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "SalesRep".equalsIgnoreCase(words[3])) {
            interactions.showOpportunitiesBySalesRep();
            state = 14;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-WON".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "SalesRep".equalsIgnoreCase(words[3])) {
            interactions.showClosedWonOpportunitiesBySalesRep();
            state = 15;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-LOST".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "SalesRep".equalsIgnoreCase(words[3])) {
            interactions.showClosedLostOpportunitiesBySalesRep();
            state = 16;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "OPEN".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "SalesRep".equalsIgnoreCase(words[3])) {
            interactions.showOpenOpportunitiesBySalesRep();
            state = 17;

/////////////////////////////////Show Accounts   //////////////////////////////////////

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "accounts".equalsIgnoreCase(words[1])){
            interactions.showAccounts();
            state = 18;

/////////////////////////////////Report by Product//////////////////////////////////////

        } else if (words.length ==5 && "Report".equalsIgnoreCase(words[0]) && "Opportunity".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "the".equalsIgnoreCase(words[3])&& "product".equalsIgnoreCase(words[4])) {
            interactions.showOpportunitiesByProduct();
            state = 19;

        } else if (words.length ==5 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-WON".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "the".equalsIgnoreCase(words[3])&& "product".equalsIgnoreCase(words[4])) {
            interactions.showWonOpportunitiesByProduct();
            state = 20;

        } else if (words.length ==5 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-LOST".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "the".equalsIgnoreCase(words[3])&& "product".equalsIgnoreCase(words[4])) {
            interactions.showLostOpportunitiesByProduct();
            state = 21;

        } else if (words.length ==5 && "Report".equalsIgnoreCase(words[0]) && "OPEN".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "the".equalsIgnoreCase(words[3])&& "product".equalsIgnoreCase(words[4])) {
            interactions.showOpenOpportunitiesByProduct();
            state = 22;

/////////////////////////////////Report by Country//////////////////////////////////////

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "Opportunity".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Country".equalsIgnoreCase(words[3])) {
            interactions.showOpportunitiesByCountry();
            state = 23;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-WON".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Country".equalsIgnoreCase(words[3])) {
            interactions.showWonOpportunitiesByCountry();
            state = 24;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-LOST".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Country".equalsIgnoreCase(words[3])) {
            interactions.showLostOpportunitiesByCountry();
            state = 25;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "OPEN".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Country".equalsIgnoreCase(words[3])) {
            interactions.showOpenOpportunitiesByCountry();
            state = 26;

/////////////////////////////////Report by City//////////////////////////////////////

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "Opportunity".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "City".equalsIgnoreCase(words[3])) {
            interactions.showOpportunitiesByCity();
            state = 27;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-WON".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "City".equalsIgnoreCase(words[3])) {
            interactions.showWonOpportunitiesByCity();
            state = 28;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-LOST".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "City".equalsIgnoreCase(words[3])) {
            interactions.showLostOpportunitiesByCity();
            state = 29;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "OPEN".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "City".equalsIgnoreCase(words[3])) {
            interactions.showOpenOpportunitiesByCity();
            state = 30;

/////////////////////////////////Report by Industry//////////////////////////////////////

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "Opportunity".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Industry".equalsIgnoreCase(words[3])) {
            interactions.showOpportunitiesByIndustry();
            state = 31;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-WON".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Industry".equalsIgnoreCase(words[3])) {
            interactions.showWonOpportunitiesByIndustry();
            state = 32;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "CLOSED-LOST".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Industry".equalsIgnoreCase(words[3])) {
            interactions.showLostOpportunitiesByIndustry();
            state = 33;

        } else if (words.length ==4 && "Report".equalsIgnoreCase(words[0]) && "OPEN".equalsIgnoreCase(words[1])&& "by".equalsIgnoreCase(words[2])&& "Industry".equalsIgnoreCase(words[3])) {
            interactions.showOpenOpportunitiesByIndustry();
            state = 34;

/////////////////////////////////Statistic EmployeeCount//////////////////////////////////////

        } else if (words.length ==2 && "Mean".equalsIgnoreCase(words[0]) && "EmployeeCount".equalsIgnoreCase(words[1])) {
                interactions.showMeanEmployeeCount();
                state = 35;

        } else if (words.length ==2 && "Median".equalsIgnoreCase(words[0]) && "EmployeeCount".equalsIgnoreCase(words[1])) {
            interactions.showMedianEmployeeCount();
            state = 36;

        } else if (words.length ==2 && "Max".equalsIgnoreCase(words[0]) && "EmployeeCount".equalsIgnoreCase(words[1])) {
            interactions.showMaxEmployeeCount();
            state = 37;

        } else if (words.length ==2 && "Min".equalsIgnoreCase(words[0]) && "EmployeeCount".equalsIgnoreCase(words[1])) {
            interactions.showMinEmployeeCount();
            state = 38;

/////////////////////////////////Statistic Quantity//////////////////////////////////////

        } else if (words.length ==2 && "Mean".equalsIgnoreCase(words[0]) && "Quantity".equalsIgnoreCase(words[1])) {
            interactions.showMeanQuantityOfProducts();
            state = 39;

        } else if (words.length ==2 && "Median".equalsIgnoreCase(words[0]) && "Quantity".equalsIgnoreCase(words[1])) {
            interactions.showMedianQuantityOfProducts();
            state = 40;

        } else if (words.length ==2 && "Max".equalsIgnoreCase(words[0]) && "Quantity".equalsIgnoreCase(words[1])) {
            interactions.showMaxQuantityOfProducts();
            state = 41;

        } else if (words.length ==2 && "Min".equalsIgnoreCase(words[0]) && "Quantity".equalsIgnoreCase(words[1])) {
            interactions.showMinQuantityOfProducts();
            state = 42;

            /////////////////////////////////Statistic Opportunity//////////////////////////////////////

        } else if (words.length ==4 && "Mean".equalsIgnoreCase(words[0]) && "Opps".equalsIgnoreCase(words[1])&& "per".equalsIgnoreCase(words[2])&& "Account".equalsIgnoreCase(words[3])) {
            interactions.showMeanOpportunitiesPerAccount();
            state = 43;

        } else if (words.length ==4 && "Median".equalsIgnoreCase(words[0]) && "Opps".equalsIgnoreCase(words[1])&& "per".equalsIgnoreCase(words[2])&& "Account".equalsIgnoreCase(words[3])) {
            interactions.showMedianOpportunitiesPerAccount();
            state = 44;

        } else if (words.length ==4 && "Max".equalsIgnoreCase(words[0]) && "Opps".equalsIgnoreCase(words[1])&& "per".equalsIgnoreCase(words[2])&& "Account".equalsIgnoreCase(words[3])) {
            interactions.showMaxOpportunitiesPerAccount();
            state = 45;

        } else if (words.length ==4 && "Min".equalsIgnoreCase(words[0]) && "Opps".equalsIgnoreCase(words[1])&& "per".equalsIgnoreCase(words[2])&& "Account".equalsIgnoreCase(words[3])) {
            interactions.showMinOpportunitiesPerAccount();
            state = 46;


        }else if (words.length == 1 && "help".equalsIgnoreCase(words[0])) {
            PrintText.printAvailableCommands();
            state = 0;

        }else if (words.length == 1 && "exit".equalsIgnoreCase(words[0])) {
            System.out.println("");
            System.out.println(Colors.WHITE + Colors.BOLD + "Good bye!" + Colors.RESET);
            System.err.println("");
            state = 99;

        } else {
            throw new IllegalArgumentException("Unknown command");
        }
    }


    public Interactions getInteractions() {
        return interactions;
    }

    public int getState() {
        return state;
    }
}
