package com.homework3.homework3overriders.supportiveClasses;

import com.homework3.homework3overriders.model.*;
import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.enums.Product;

import java.math.BigDecimal;
import java.util.List;

public class PrintText {

    public static void insertCommand(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.WHITE + Colors.BG_LIGHT_BLUE+"Insert command \"help\" to see all available commands");
        System.out.println("");
        System.out.println("Please, insert an available command:");
        System.out.println(Colors.RESET);
    };


    public static void printAvailableCommands() {

        System.out.println(Colors.WHITE + Colors.BG_LIGHT_BLUE+" ========================================================================================");
        System.out.println();
        System.out.println("   BASIC COMMANDS:                                                                          ");
        System.out.println();
        System.out.println("   Lead commands to search, track and convert potential opportunities are:                          ");
        System.out.println("      New Lead                              To create a new lead                                    ");
        System.out.println("      Show Leads                            To list all Leads' id and name                          ");
        System.out.println("      Lookup Lead [id]                      To display the details of a Lead                        ");
        System.out.println("      Convert [id]                          To convert a Lead into an Opportunity                   ");
        System.out.println();
        System.out.println("   SalesRep commands to create, search, track and control SalesRep activity are:                    ");
        System.out.println("      New SalesRep                          To create a new SalesRep                                ");
        System.out.println("      Show SalesReps                        To list all SalesReps' id and name                      ");
        System.out.println();
        System.out.println("   Opportunity commands to search and update opportunities are:                                     ");
        System.out.println("      Show Opportunities                    To list all Opportunities' id and name                  ");
        System.out.println("      Lookup Opportunity [id]               To display the details of an Opportunity                ");
        System.out.println("      close-lost [id]                       To update the status of a OPEN opportunity              ");
        System.out.println("      close-won [id]                        To update the status of a OPEN opportunity              ");
        System.out.println();
        System.out.println("   Accounts commands to search and update accounts are:                                             ");
        System.out.println("      Show Accounts                         To list all Accounts' info                              ");
        System.out.println();
        System.out.println(" ========================================================================================");
        System.out.println();
        System.out.println("   REPORTING COMMANDS:                                                                          ");
        System.out.println();
        System.out.println("   - By SalesRep:                                                                                   ");
        System.out.println("      Report Lead by SalesRep               A count of Leads by SalesRep                            ");
        System.out.println("      Report Opportunity by SalesRep        A count of all Opportunities by SalesRep                ");
        System.out.println("      Report CLOSED-WON by SalesRep         A count of all CLOSED_WON Opportunities by SalesRep     ");
        System.out.println("      Report CLOSED-LOST by SalesRep        A count of all CLOSED_LOST Opportunities by SalesRep    ");
        System.out.println("      Report OPEN by SalesRep               A count of all OPEN Opportunities by SalesRep           ");
        System.out.println();
        System.out.println("   - By Product:                                                                                    ");
        System.out.println("      Report Opportunity by the product     A count of all Opportunities by the product             ");
        System.out.println("      Report CLOSED-WON by the product      A count of all CLOSED_WON Opportunities by the product  ");
        System.out.println("      Report CLOSED-LOST by the product     A count of all CLOSED_LOST Opportunities by the product");
        System.out.println("      Report OPEN by the product            A count of all OPEN Opportunities by the product        ");
        System.out.println();
        System.out.println("   - By Country:                                                                                    ");
        System.out.println("      Report Opportunity by Country         A count of all Opportunities by country                 ");
        System.out.println("      Report CLOSED-WON by Country          A count of all CLOSED_WON Opportunities by country      ");
        System.out.println("      Report CLOSED-LOST by Country         A count of all CLOSED_LOST Opportunities by country     ");
        System.out.println("      Report OPEN by Country                A count of all OPEN Opportunities by country            ");
        System.out.println();
        System.out.println("   - By City:                                                                                       ");
        System.out.println("      Report Opportunity by City            A count of all Opportunities by city                    ");
        System.out.println("      Report CLOSED-WON by City             A count of all CLOSED_WON Opportunities by city         ");
        System.out.println("      Report CLOSED-LOST by City            A count of all CLOSED_LOST Opportunities by city        ");
        System.out.println("      Report OPEN by City                   A count of all OPEN Opportunities by city               ");
        System.out.println();
        System.out.println("   - By Industry:                                                                                   ");
        System.out.println("      Report Opportunity by Industry        A count of all Opportunities by industry                ");
        System.out.println("      Report CLOSED-WON by Industry         A count of all CLOSED_WON Opportunities by industry     ");
        System.out.println("      Report CLOSED-LOST by Industry        A count of all CLOSED_LOST Opportunities by industry    ");
        System.out.println("      Report OPEN by Industry               A count of all OPEN Opportunities by industry           ");
        System.out.println();
        System.out.println("   - EmployeeCount States:                                                                          ");
        System.out.println("      Mean EmployeeCount                    The mean employeeCount                                  ");
        System.out.println("      Median EmployeeCount                  The median employeeCount                                ");
        System.out.println("      Max EmployeeCount                     The maximum employeeCount                               ");
        System.out.println("      Min EmployeeCount                     The minimum employeeCount                               ");
        System.out.println();
        System.out.println("   - Quantity States:                                                                               ");
        System.out.println("      Mean Quantity                         The mean quantity of products order                     ");
        System.out.println("      Median Quantity                       The median quantity of products                         ");
        System.out.println("      Max Quantity                          The maximum quantity of products                        ");
        System.out.println("      Min Quantity                          The minimum quantity of products                        ");
        System.out.println();
        System.out.println("   - Opportunity States:                                                                            ");
        System.out.println("      Mean Opps per Account                 The mean number of Opportunities associated with an Account");
        System.out.println("      Median Opps per Account               The median number of Opportunities associated with an Account");
        System.out.println("      Max Opps per Account                  The max number of Opportunities associated with an Account");
        System.out.println("      Min Opps per Account                  The minimum number of Opportunities associated with an Account");
        System.out.println();
        System.out.println("   Application commands:                                                                            ");
        System.out.println("      Exit                                  To close the application                                ");
        System.out.println();
        System.out.println(" ==========================================================================================================");
        System.out.println(Colors.RESET);
    }

    public static void wrongCommand(String error){
        System.err.println(error);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Please enter a valid command");
        System.out.println(" ");
    }

    public static void askProduct() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the product for this opportunity. Products available: ");
        System.out.println("- " + Product.HYBRID);
        System.out.println("- " + Product.FLATBED);
        System.out.println("- " + Product.BOX);
    }

    public static void wrongProduct() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.LIGHT_RED+Colors.BOLD+"Insert a correct product");
    }

    public static void askNumberOfTrucks() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the number of trucks");
    }

    public static void wrongNumberOfTrucks() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a number of trucks bigger than zero");
    }

    public static void wrongIdSalesRep() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a SalesRep id bigger than zero");
    }

    public static void wrongNumberIdOfAccount() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert an Account id bigger than zero");
    }

    public static void askIndustry() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+ "Insert the industry. Industries availables");
        System.out.println("- " + Industry.PRODUCE);
        System.out.println("- " + Industry.ECOMMERCE);
        System.out.println("- " + Industry.MANUFACTURING);
        System.out.println("- " + Industry.MEDICAL);
        System.out.println("- " + Industry.OTHER);
    }

    public static void askNumberOfEmployees() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the number of employees");
    }

    public static void wrongIndustry() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a correct industry");
    }

    public static void wrongNumberOfEmployees() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a number of employees bigger than zero");
    }

    public static void askLeadName(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert new Lead's name");
    }

    public static void invalidName(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Invalid name");
    }

    public static void askPhoneNumber(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert a valid phone number");
    }

    public static void invalidPhoneNumber() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Invalid phone number"+Colors.RESET);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Phone number should have a valid format:"+Colors.RESET);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "123456789  +34123456789  (34)123456789"+Colors.RESET);
    }

    public static void askEmail(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert a valid email address");
    }

    public static void invalidEmail() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"Invalid email address"+Colors.RESET);
    }

    public static void askCompanyName(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert company's name");
    }

    public static void askCity() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert account's city");
    }

    public static void askCountry() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert account's country");
    }
    public static void leadCreated(Lead lead){
        System.out.println(Colors.RESET);
        System.out.println(Colors.WHITE + Colors.BG_GREEN + Colors.BOLD);
        System.out.println("  New Lead created: " + lead.toStringFormatted());
        System.out.println(Colors.RESET + Colors.WHITE + Colors.BG_GREEN);
    }

    public static void leadConverted(Lead lead, Opportunity opportunity) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.WHITE + Colors.BG_GREEN + Colors.BOLD);
        System.out.println("  Lead: " + lead.toStringFormatted() + " converted to Opportuntity: " + opportunity.toStringFormatted());
        System.out.println(Colors.RESET + Colors.WHITE + Colors.BG_GREEN);

    }

    public static void printLead(Lead lead) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("    " + lead.toStringFormatted());
        System.out.println();

    }

    public static void printOpportunity(Opportunity opportunity) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("    " + opportunity.toStringFormatted());
        System.out.println();
    }

    public static void printAccount(Account account) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  " + account.toStringFormatted());
        System.out.println();
    }

    public static void printAccountWithoutOpportunity(Account account) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("     ╚══ " + account.toStringFormattedWithoutOpportunityAndContact());
        System.out.println();
    }

    public static void printContact(Contact contact) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("       ╚══ Contact - ID " +contact.getId()+ " : " + contact.toStringFormatted());
        System.out.println();
    }

    public static void noLeadsYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are no leads yet.");
        System.err.println("");
    }

    public static void noOpportunitiesYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are no opportunities yet.");
        System.err.println("");
    }

    public static void noAccountsYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are no accounts yet.");
        System.err.println("");
    }

    public static void noContactsYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are no contacts yet.");
        System.err.println("");
    }

    public static void noSalesRepYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are no SalesRep yet.");
        System.err.println("");
    }

    public static void cannotCreateLeadWithoutSalesReps() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"Leads cannot be created because there are not SalesRep yet.");
        System.err.println("");
    }

    public static void wrongIdFormat() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"The ID must be a number bigger than zero");
        System.err.println("");
    }

    public static void noIdFound(String message) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+message);
    }

    public static void wrongSalesRepNotFound(Long id, List<SalesRep> salesRepsList) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"The ID " + id + " does not match with any SalesRep. The available Sales Reps are: "+Colors.RESET);
        for (SalesRep salesRep : salesRepsList){
            System.out.println(salesRep.toStringFormatted());
        }
    }

    public static void closeOpportunityLost(Long id) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.RED + Colors.BOLD);
        System.out.println("  The opportunity " + id + " has been closed as LOST");
        System.out.println();
    }

    public static void closeOpportunityWon(Long id) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.GREEN + Colors.BOLD);
        System.out.println("  The opportunity " + id + " has been closed as WON");
        System.out.println();
    }

    public static void opportunityAlreadyClosed(Long id) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"  The opportunity " + id + " is already CLOSED");
        System.err.println("");
    }

    public static void headerShowLeads() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing leads:");
        System.out.println();
    }

    public static void headerShowOpportunities() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing opportunities:");
        System.out.println();
    }

    public static void headerShowAccounts() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing accounts:");
        System.out.println();
    }
    public static void headerShowContacts() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing contacts:");
        System.out.println();
    }

    public static void headerShowSalesRep() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing SalesRep:");
        System.out.println();
    }

    public static void iDAlreadyExists(String message) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+message);
    }

    public static void idDoesNotExist(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Introduce another id."+Colors.RESET);
    }

    public static void recommendationShowLeads(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "If you are not sure what is the ID, use the command 'Show Leads' to visualize all the leads."+Colors.RESET);
    }

    public static void recommendationShowOpportunities(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "If you are not sure what is the ID, use the command 'Show Opportunities' to visualize all the opportunities."+Colors.RESET);
    }


    public static void recommendationShowAccounts(List<Account> accountList) {
        System.out.println(Colors.RESET+ "Existing accounts: "+Colors.RESET);
        for (Account account : accountList){
            System.out.println(account.toStringFormattedWithoutOpportunityAndContact());
        }
    }

    public static void existingAccountIsUsed(Long id) {
        System.out.println(Colors.RESET+Colors.BLUE+Colors.BOLD+ "The account with ID  " + id + " has been linked to the new Opportunity"+Colors.RESET);
    }

    public static void askSalesRepName(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert new SalesRep's name");
    }

    public static void askSalesRepId() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the SalesRep id for this Lead");

    }

    public static void askIdOfExistingAccount() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the Id of the Account you want to use");

    }

    public static void askIfCreateNewAccount() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Would you like to create a new Account? (Y/N)");

    }

    public static void salesRepCreated(SalesRep salesRep){
        System.out.println(Colors.RESET);
        System.out.println(Colors.WHITE + Colors.BG_GREEN + Colors.BOLD);
        System.out.println("  New SalesRep created: " + salesRep.toStringFormatted());
        System.out.println(Colors.RESET + Colors.WHITE + Colors.BG_GREEN);
    }


    public static void showContactsByAccount(Account account) {
        for (Contact contact : account.getContacts()){
            PrintText.printContact(contact);
        }
    }

    public static void printStatisticDouble(String statisticType,double value) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+statisticType + ": "+value);
    }

    public static void printStatisticBigDecimal(String statisticType, BigDecimal value) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+statisticType + ": "+value);
    }

    public static void printStatisticInteger(String statisticType,Integer value) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+statisticType + ": "+value);
    }

    public static void reportUpperMargin() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
    }
    public static void reportLowerMargin() {
        System.out.println();
        System.out.println(Colors.RESET);
    }

    public static void printReportBySalesRep(List<Object[]> report) {
        if (report.size() == 0){
            PrintText.noSalesRepYet();
        } else{
            PrintText.reportUpperMargin();
            for (Object[] registry : report){
                System.out.println("  " + registry[0] + ": " + registry[1]);
            }
            PrintText.reportLowerMargin();
        }
    }
    public static void printOpportunitiesReport(List<Object[]> report) {
        if (report.size() == 0){
            PrintText.noOpportunitiesYet();
        } else{
            PrintText.reportUpperMargin();
            for (Object[] registry : report){
                System.out.println("  " + registry[0] + ": " + registry[1]);
            }
            PrintText.reportLowerMargin();
        }
    }

}
