package com.homework3.homework3overriders.service.impl;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Contact;
import com.homework3.homework3overriders.repository.ContactRepository;
import com.homework3.homework3overriders.service.interfaces.ContactService;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    public void delete(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any contact");
    }

    public Contact findById(Long id) throws InvalidParameterException, NoSuchElementException {
        if(id < 1)
            throw new InvalidParameterException();
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if(optionalContact.isPresent()) {
            return optionalContact.get();
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any contact");

    }

    public void save(Contact contact) throws ExistentElementException{
        if (contact.getId() != null) {
            if (contact.getId() < 1)
                throw new InvalidParameterException();
            Optional<Contact> optionalContact = contactRepository.findById(contact.getId());
            if (optionalContact.isPresent()) {
                throw new ExistentElementException("The ID " + contact.getId() + " already exists in the contacts Database");
            } else
                contactRepository.save(contact);
        } else {
            contactRepository.save(contact);
        }
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }
}
