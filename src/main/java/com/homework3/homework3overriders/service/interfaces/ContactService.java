package com.homework3.homework3overriders.service.interfaces;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Contact;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ContactService {

    void delete(Long id) throws InvalidParameterException, NoSuchElementException;

    Contact findById(Long id) throws InvalidParameterException, NoSuchElementException;

    void save(Contact contact) throws ExistentElementException;

    List<Contact> getAll();
}
