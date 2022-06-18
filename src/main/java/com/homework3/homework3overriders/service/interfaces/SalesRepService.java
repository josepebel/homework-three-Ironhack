package com.homework3.homework3overriders.service.interfaces;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.SalesRep;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface SalesRepService {
    void save(SalesRep salesRep) throws ExistentElementException;

    SalesRep findById(Long id) throws InvalidParameterException, NoSuchElementException;

    void delete(Long id) throws InvalidParameterException, NoSuchElementException;

    SalesRep createSalesRep();

    List<SalesRep> getAll();

    List<Object[]> showLeadsBySalesRep();
    List<Object[]> showOpportunitiesBySalesRep();
    List<Object[]> showClosedWonOpportunitiesBySalesRep();
    List<Object[]> showClosedLostOpportunitiesBySalesRep();
    List<Object[]> showOpenOpportunitiesBySalesRep();
}
