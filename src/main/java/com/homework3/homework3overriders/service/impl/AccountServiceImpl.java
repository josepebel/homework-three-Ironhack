package com.homework3.homework3overriders.service.impl;

import com.homework3.homework3overriders.enums.Industry;
import com.homework3.homework3overriders.exceptions.ExistentElementException;
import com.homework3.homework3overriders.model.Account;
import com.homework3.homework3overriders.model.Contact;
import com.homework3.homework3overriders.model.Opportunity;
import com.homework3.homework3overriders.repository.AccountRepository;
import com.homework3.homework3overriders.service.interfaces.AccountService;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findById(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any account");
    }

    public void delete(Long id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();

        Optional<Account> optionalAccount = accountRepository.findById(id);
        //Según el diagrama de clases, cuando se borra una account se borran sus contacts
        if (optionalAccount.isPresent()){
            accountRepository.delete(optionalAccount.get());
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any account");
        }
    }

    public void save(Account account)  {
        if (account.getId() != null) {
            if (account.getId() < 1)
                throw new InvalidParameterException();
            Optional<Account> optionalAccount = accountRepository.findById(account.getId());
            if (optionalAccount.isPresent()) {
                PrintText.existingAccountIsUsed(account.getId());
                //throw new ExistentElementException("The ID " + account.getId() + " already exists in the accounts Database");
            } else {
                accountRepository.save(account);
            }
        } else {
            accountRepository.save(account);
        }
    }

    public Account obtainAccount(Contact contact, Opportunity opportunity){
        Account account = null;
        List<Account> allAccounts = accountRepository.findAll();
        if (allAccounts.size() > 0) {
            //Get number of employees
            String command = "";
            do {
                PrintText.askIfCreateNewAccount();
                command = Account.getIfCreateNewAccount();
            } while (!command.trim().equalsIgnoreCase("Y") & !command.trim().equalsIgnoreCase("N"));

            if (command.trim().equalsIgnoreCase("Y")) {
                account = obtainAccountInput(contact, opportunity);
            } else {
                account = obtainExistingAccount(contact, opportunity);
            }
        } else{
            account = obtainAccountInput(contact, opportunity);
        }
        contact.setAccount(account);
        opportunity.setAccount(account);
        return account;

    }

    private Account obtainExistingAccount(Contact contact, Opportunity opportunity) {

        Optional<Account> optionalAccount = null;
        do {
            PrintText.askIdOfExistingAccount();
            Long idAccount = Account.getIdOfExistingAccount();
            optionalAccount = accountRepository.findById(idAccount);
            if (!optionalAccount.isPresent()){
                PrintText.noIdFound("The ID " + idAccount + " does not match with any account");
                PrintText.idDoesNotExist();
                List<Account> allAccounts = accountRepository.findAll();
                PrintText.recommendationShowAccounts(allAccounts);
            }
        } while (!optionalAccount.isPresent());


        Account account = optionalAccount.get();
        return account;
    }

    public Account obtainAccountInput(Contact contact, Opportunity opportunity) {
        //Get industry
        Industry industry = null;
        do {
            PrintText.askIndustry();
            industry = Industry.getIndustryFromScanner();
        } while (industry == null);

        //Get number of employees
        int numberOfEmployees = 0;
        do {
            PrintText.askNumberOfEmployees();
            numberOfEmployees = Account.getNumberOfEmployees();
        } while (numberOfEmployees == 0);

        Scanner scanner = new Scanner(System.in);

        //Get city
        //String city = null;
        //PrintText.askCity();
        //city = scanner.nextLine();
        
        String city = "";
        do{
            PrintText.askCity();
            String initialCity = Account.getCityFromUser();
            if(initialCity.matches((Account.regexCityValidation()))) {
                city = initialCity;
            } else {
                city = "";
                PrintText.invalidName();
            }
        } while ("".equals(city));

        //Get country
        //String country = null;
        //PrintText.askCountry();
        //country = scanner.nextLine();
        
        String country = "";
        do{
            PrintText.askCountry();
            String initialCountry = Account.getCountryFromUser();
            if(initialCountry.matches((Account.regexCountryValidation()))) {
                country = initialCountry;
            } else {
                country = "";
                PrintText.invalidName();
            }
        } while ("".equals(country));

        Account account = new Account(industry, numberOfEmployees, city, country);
        return account;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
