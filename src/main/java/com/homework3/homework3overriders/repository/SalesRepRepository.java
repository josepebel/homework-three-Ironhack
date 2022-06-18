package com.homework3.homework3overriders.repository;

import com.homework3.homework3overriders.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {


    @Query("SELECT s.name, COUNT(l) FROM SalesRep s LEFT JOIN s.leads l GROUP BY s.name")
    List<Object[]> leadBySalesRep();

    @Query("SELECT s.name, COUNT(o) FROM SalesRep s LEFT JOIN s.opportunities o GROUP BY s.name" )
    List<Object[]> opportunitiesBySalesRep();

    @Query(value = "SELECT s.name,COUNT((IF(o.status = 'CLOSED_WON',1,null))) as 'CLOSED_WON' FROM sales_rep s LEFT JOIN opportunity o ON s.id = o.sales_rep_id GROUP BY s.name",nativeQuery = true)
    List<Object[]> closedWonOpportunitiesBySalesRep();

    @Query(value = "SELECT s.name,COUNT((IF(o.status = 'CLOSED_LOST',1,null))) as 'CLOSED_WON' FROM sales_rep s LEFT JOIN opportunity o ON s.id = o.sales_rep_id GROUP BY s.name",nativeQuery = true)
    List<Object[]> closedLostOpportunitiesBySalesRep();

    @Query(value = "SELECT s.name,COUNT((IF(o.status = 'OPEN',1,null))) as 'CLOSED_WON' FROM sales_rep s LEFT JOIN opportunity o ON s.id = o.sales_rep_id GROUP BY s.name",nativeQuery = true)
    List<Object[]> openOpportunitiesBySalesRep();

    //Alternativa tipo para mostrar todos los SalesRep sin incluir los 0

/*    @Query("SELECT s.name, COUNT(o) FROM SalesRep s LEFT JOIN s.opportunities o WHERE o.status = 'CLOSED_WON' GROUP BY s.name")
    List<Object[]> closedWonOpportunitiesBySalesRep();

    @Query("SELECT s.name, COUNT(o) FROM SalesRep s LEFT JOIN s.opportunities o WHERE o.status = 'CLOSED_LOST' GROUP BY s.name")
    List<Object[]> closedLostOpportunitiesBySalesRep();

    @Query("SELECT s.name, COUNT(o) FROM SalesRep s LEFT JOIN s.opportunities o WHERE o.status = 'OPEN' GROUP BY s.name")
    List<Object[]> openOpportunitiesBySalesRep();*/


}
