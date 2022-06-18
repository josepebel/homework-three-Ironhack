package com.homework3.homework3overriders.service.interfaces;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Lead;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;

public interface LeadService {
    Lead findById(Long id) throws InvalidParameterException, NoSuchElementException;

    void deleteById(Long id) throws InvalidParameterException, NoSuchElementException;

    void save(Lead lead) throws InvalidParameterException, ExistentElementException;

    List<Lead> getAll();

    Lead createLead();
}
