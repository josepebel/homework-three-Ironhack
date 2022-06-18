# CRM System (Data Layer HW3)

The primary goal in this homework is to add persistent data storage to our CRM system and to build a reporting feature.

## 📊 UML Diagrams

<a href="https://github.com/pablopggp/overriders-homework3/blob/developments/src/main/resources/ClassDiagram3.png"> Class Diagram </a>

<a href="https://github.com/pablopggp/overriders-homework3/blob/developments/src/main/resources/UseCaseDiagram3.png"> Use Case Diagram </a>

## 💻 Usage
Prior to being able to start the CRM System we need to manually setup our Database using the included SQL Script
located in `src/main/resources/homework3.sql`.

To start the application we have to run directly the class `Homework3overridersApplication.java`

This is due to how our CRM System works, by entering commands to the console.

The applications keeps its functionality when run through the  command `mvn spring-boot:run`, but the user interface loses its *charm*.

## 🛠️Testing
Due to technical limitations, to be able to run the testing we'll have to comment and uncomment a couple of code blocks in the
`Homework3overridersApplication.java` class.

The instructions are already written in the class and hopefully they won't be too confusing to follow.

Sorry for the incoveniences, we're *hardly* working to fix them.

## 🧑‍💻 About Us
The Overriders is formed by:

[David Díaz](https://github.com/daviddiazmartinez)

[José Antonio Peño](https://github.com/josepebel)

[Luis Badolato](https://github.com/luisbadolato)

[María Campaña](https://github.com/Johari-lab)

[Pablo Gutiérrez García-Pardo](https://github.com/pablopggp).



## 📌  Full Specifications

**CORE FUNCTIONALITY**
1.  Create a new use case diagram and add it to your `README.md`.

2.  Create a new class diagram and add it to your `README.md`.

3.  Create unit tests for every method other than basic getters, setters, and constructors (getters and setters with logic do require unit tests).

4.  Handle all exceptions gracefully (incorrect input should not crash the program).

5.  All data should be stored in a normalized SQL database.

6.  `SalesReps` can be added to the CRM by typing the command “New SalesRep” (case insensitive).
7. When a new SalesRep is created the user will be prompted for a name. A name must be supplied to create the new SalesRep.
8. The system should automatically create an `id` for the SalesRep.
9. A list of all `SalesReps` can be displayed by typing “Show SalesReps” (case insensitive).
10. When a new Lead is created, the user should be prompted for a SalesRep id in addition to the prompts created in the previous assignment. The new Lead should be associated with a SalesRep by the provided id.
11. When a Lead is converted to an Opportunity, the Opportunity should automatically be associated with the same SalesRep as the Lead.
12. Review requirements 12 and 13 from the previous assignment. Note that when a Lead was converted an Opportunity was created, after which users were prompted for Account information to create a new `Account`. Between these steps, users should be prompted to select whether they would like to create a new `Account`.
13. If the user types Y, the program should continue as it previously did by prompting the user for Account information.
14. If the user types N, the program should prompt the user for an Account id and associate the Contact and Opportunity with the specified Account. (You must handle all probable occurrences such as a user inputting an id that doesn’t exist).

___

**REPORTING FUNCTIONALITY**
-   By `SalesRep`



1.  A count of Leads by SalesRep can be displayed by typing “Report Lead by SalesRep”
2.  A count of all Opportunities by SalesRep can be displayed by typing “Report Opportunity by SalesRep”
3.  A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing “Report CLOSED-WON by SalesRep”
4.  A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing “Report CLOSED-LOST by SalesRep”
5.  A count of all OPEN Opportunities by SalesRep can be displayed by typing “Report OPEN by SalesRep”



-   By `Product`



1.  A count of all Opportunities by the product can be displayed by typing “Report Opportunity by the product”
2.  A count of all CLOSED_WON Opportunities by the product can be displayed by typing “Report CLOSED-WON by the product”
3.  A count of all CLOSED_LOST Opportunities by the product can be displayed by typing “Report CLOSED-LOST by the product”
4.  A count of all OPEN Opportunities by the product can be displayed by typing “Report OPEN by the product”



-   By `Country`



1.  A count of all Opportunities by country can be displayed by typing “Report Opportunity by Country”
2.  A count of all CLOSED_WON Opportunities by country can be displayed by typing “Report CLOSED-WON by Country”
3.  A count of all CLOSED_LOST Opportunities by country can be displayed by typing “Report CLOSED-LOST by Country”
4.  A count of all OPEN Opportunities by country can be displayed by typing “Report OPEN by Country”



-   By `City`



1.  A count of all Opportunities by the city can be displayed by typing “Report Opportunity by City”
2.  A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by City”
3.  A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by City”
4.  A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by City”



-   By `Industry`



1.  A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
2.  A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
3.  A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
4.  A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”



-   `EmployeeCount` States



1.  The mean `employeeCount` can be displayed by typing “Mean EmployeeCount”
2.  The median `employeeCount` can be displayed by typing “Median EmployeeCount”
3.  The maximum `employeeCount` can be displayed by typing “Max EmployeeCount”
4.  The minimum `employeeCount` can be displayed by typing “Min EmployeeCount”



-   `Quantity` States



1.  The mean quantity of products order can be displayed by typing “Mean Quantity”
2.  The median quantity of products order can be displayed by typing “Median Quantity”
3.  The maximum quantity of products order can be displayed by typing “Max Quantity”
4.  The minimum quantity of products order can be displayed by typing “Min Quantity”



-   `Opportunity` States



1.  The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
2.  The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
3.  The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
4.  The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”

