package com.homework3.homework3overriders.service.interfaces;

import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Account;
import com.homework3.homework3overriders.model.Contact;
import com.homework3.homework3overriders.model.Opportunity;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface AccountService {
    Account findById(Long id) throws InvalidParameterException, NoSuchElementException;

    void delete(Long id) throws InvalidParameterException, NoSuchElementException;

    void save(Account account);

    Account obtainAccount(Contact contact, Opportunity opportunity);

    List<Account> getAll();
}
