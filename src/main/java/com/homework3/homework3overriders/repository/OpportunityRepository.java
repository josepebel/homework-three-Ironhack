package com.homework3.homework3overriders.repository;

import com.homework3.homework3overriders.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Query("SELECT o.product, COUNT(*) FROM Opportunity o GROUP BY o.product")
    List<Object[]> opportunitiesByProduct();

    @Query("SELECT o.product, COUNT(*) FROM Opportunity o WHERE o.status = 'OPEN' GROUP BY o.product")
    List<Object[]> openOpportunitiesByProduct();

    @Query("SELECT o.product, COUNT(*) FROM Opportunity o WHERE o.status = 'CLOSED_WON' GROUP BY o.product")
    List<Object[]> wonOpportunitiesByProduct();

    @Query("SELECT o.product, COUNT(*) FROM Opportunity o WHERE o.status = 'CLOSED_LOST' GROUP BY o.product")
    List<Object[]> lostOpportunitiesByProduct();

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN o.account a GROUP BY a.country")
    List<Object[]> opportunitiesByCountry();

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'OPEN' GROUP BY a.country")
    List<Object[]> openOpportunitiesByCountry();

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_WON' GROUP BY a.country")
    List<Object[]> wonOpportunitiesByCountry();

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_LOST' GROUP BY a.country")
    List<Object[]> lostOpportunitiesByCountry();

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN o.account a GROUP BY a.city")
    List<Object[]> opportunitiesByCity();

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'OPEN' GROUP BY a.city")
    List<Object[]> openOpportunitiesByCity();

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_WON' GROUP BY a.city")
    List<Object[]> wonOpportunitiesByCity();

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_LOST' GROUP BY a.city")
    List<Object[]> lostOpportunitiesByCity();

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN o.account a GROUP BY a.industry")
    List<Object[]> opportunitiesByIndustry();

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'OPEN' GROUP BY a.industry")
    List<Object[]> openOpportunitiesByIndustry();

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_WON' GROUP BY a.industry")
    List<Object[]> wonOpportunitiesByIndustry();

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN o.account a WHERE o.status = 'CLOSED_LOST' GROUP BY a.industry")
    List<Object[]> lostOpportunitiesByIndustry();
    
    //EmployeeCount States
    //The mean employeeCount can be displayed by typing “Mean EmployeeCount”
    @Query("SELECT AVG(a.employeeCount) FROM Account a")
    double getMeanEmployeeCount() throws IllegalStateException;

    //The median employeeCount can be displayed by typing “Median EmployeeCount”
    @Query(value = "SELECT AVG(employee_count) as Median FROM (SELECT @rowindex\\:=@rowindex + 1 AS rowindex, employee_count AS employee_count FROM account ORDER BY employee_count) AS a WHERE a.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    List<Object[]> getMedianEmployeeCount();

    //The maximum employeeCount can be displayed by typing “Max EmployeeCount”
    @Query("SELECT MAX(a.employeeCount) FROM Account a")
    int getMaxEmployeeCount();

    //The minimum employeeCount can be displayed by typing “Min EmployeeCount”
    @Query("SELECT MIN(a.employeeCount) FROM Account a")
    int getMinEmployeeCount();

    //Quantity States
    //The mean quantity of products order can be displayed by typing “Mean Quantity”
    @Query("SELECT AVG(o.quantity) FROM Opportunity o")
    double getMeanQuantityOfProducts();

    //The median quantity of products order can be displayed by typing “Median Quantity”
    @Query(value = "SELECT AVG(o.quantity) as Median FROM (SELECT @rowindex\\:=@rowindex + 1 AS rowindex, quantity AS quantity FROM opportunity ORDER BY quantity) AS o WHERE o.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    List<Object[]> getMedianQuantityOfProducts();

    //The maximum quantity of products order can be displayed by typing “Max Quantity”
    @Query("SELECT MAX(o.quantity) FROM Opportunity o")
    int getMaxQuantityOfProducts();

    //The minimum quantity of products order can be displayed by typing “Min Quantity”
    @Query("SELECT MIN(o.quantity) FROM Opportunity o")
    int getMinQuantityOfProducts();

    //Opportunity States
    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    @Query(value = "SELECT AVG(ops) FROM (SELECT COUNT(*) ops, a.id FROM opportunity o LEFT JOIN account a ON o.account_id = a.id GROUP BY a.id) AS T", nativeQuery = true)
    double getMeanOpportunitiesPerAccount();

    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    @Query(value = "SELECT AVG(ops) as Median FROM (SELECT @rowindex\\:=@rowindex + 1 AS rowindex, COUNT(*) AS ops FROM opportunity o LEFT JOIN account a ON o.account_id = a.id GROUP BY a.id ORDER BY ops) AS T WHERE T.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    List<Object[]> getMedianOpportunitiesPerAccount();

    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account
    @Query(value = "SELECT MAX(ops) FROM (SELECT COUNT(*) ops, a.id FROM opportunity o LEFT JOIN account a ON o.account_id = a.id GROUP BY a.id) AS T", nativeQuery = true)
    int getMaxOpportunitiesPerAccount();

    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
    @Query(value = "SELECT MIN(ops) FROM (SELECT COUNT(*) ops, a.id FROM opportunity o LEFT JOIN account a ON o.account_id = a.id GROUP BY a.id) AS T", nativeQuery = true)
    int getMinOpportunitiesPerAccount();


    // Set rowindex for Median querys
    @Modifying
    @Transactional
    @Query(value = "SET @rowindex \\:=-1;", nativeQuery = true)
    void setRowIndex();
}
