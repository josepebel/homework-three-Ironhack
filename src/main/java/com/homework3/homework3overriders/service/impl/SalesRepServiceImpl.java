package com.homework3.homework3overriders.service.impl;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.SalesRep;
import com.homework3.homework3overriders.repository.SalesRepRepository;
import com.homework3.homework3overriders.service.interfaces.SalesRepService;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SalesRepServiceImpl implements SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    public void save(SalesRep salesRep) throws ExistentElementException {
        if (salesRep.getId() != null) {
            if (salesRep.getId() < 1)
                throw new InvalidParameterException();
            Optional<SalesRep> optionalSalesRep = salesRepRepository.findById(salesRep.getId());
            if (optionalSalesRep.isPresent()) {
                throw new ExistentElementException("The ID " + salesRep.getId() + " already exists in the SalesRep Database");
            } else {
                salesRepRepository.save(salesRep);
            }
        } else {
            salesRepRepository.save(salesRep);
        }
    }

    public SalesRep findById(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<SalesRep> optionalSalesRep = salesRepRepository.findById(id);
        if (optionalSalesRep.isPresent()) {
            return optionalSalesRep.get();
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any SalesRep");
    }

    public void delete(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<SalesRep> optionalSalesRep = salesRepRepository.findById(id);
        //Según el diagrama de clases, cuando se borra un salesRep se borran sus lead y sus opportunities
        if (optionalSalesRep.isPresent()) {
            salesRepRepository.delete(optionalSalesRep.get());
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any SalesRep");
    }

    public SalesRep createSalesRep() {
        //get name from user
        String name = "";
        do{
            PrintText.askSalesRepName();
            String initialName = SalesRep.getNameFromUser();
            if(initialName.matches((SalesRep.regexNameValidation()))) {
                name = initialName;
            } else {
                name = "";
                PrintText.invalidName();
            }
        } while ("".equals(name));


        SalesRep newSalesRep = new SalesRep(name);

        return newSalesRep;
    }

    public List<SalesRep> getAll(){
        return salesRepRepository.findAll();
    }

    public List<Object[]> showLeadsBySalesRep(){ return salesRepRepository.leadBySalesRep();}
    public List<Object[]> showOpportunitiesBySalesRep(){return salesRepRepository.opportunitiesBySalesRep();}
    public List<Object[]> showClosedWonOpportunitiesBySalesRep(){return salesRepRepository.closedWonOpportunitiesBySalesRep();}
    public List<Object[]> showClosedLostOpportunitiesBySalesRep(){return salesRepRepository.closedLostOpportunitiesBySalesRep();}
    public List<Object[]> showOpenOpportunitiesBySalesRep(){return salesRepRepository.openOpportunitiesBySalesRep();}
}
