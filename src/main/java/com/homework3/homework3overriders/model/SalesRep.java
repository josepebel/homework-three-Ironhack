package com.homework3.homework3overriders.model;

import com.homework3.homework3overriders.supportiveClasses.PrintText;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Lead> leads;

    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Opportunity> opportunities;

    // Constructor

    public SalesRep() {
    }

    public SalesRep(String name) {
        setName(name);
    }

    //////////////////////////APPLICABLE METHODS/////////////////

    public static String getNameFromUser(){
        Scanner scanner = new Scanner(System.in);
        String name = "";
        try {

            name = scanner.nextLine();
        } catch (InputMismatchException e){
            name= "";
        }

        return name;
    }

    public static Long getSalesRepIdFromCommand(){

        Scanner scanner = new Scanner(System.in);
        Long salesRepId = Long.valueOf(0);
        try {
            String lectorComando = scanner.nextLine();
            salesRepId = Long.parseLong(lectorComando);
        } catch (InputMismatchException e){
            salesRepId = Long.valueOf(0);
        }
        catch (NumberFormatException e){
            salesRepId = Long.valueOf(0);
        }
        if (salesRepId < 1) {
            PrintText.wrongIdSalesRep();
            salesRepId = Long.valueOf(0);
        }
        return salesRepId;

    }


    ////////////////////////////////GETTERS AND SETTERS//////////////////////


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String regexNameValidation() {
        String validation = "(?i)(^[a-záéíóúüñç])((?![ .,'-]$)[a-záéíóúüñç .,'-]){0,24}$";
        return validation;
    }

    public Boolean setName(String name) {
        if(name.matches(regexNameValidation())) {
            this.name = name;
            return true;
        } else {
            throw new IllegalArgumentException("Name format should be valid.");
        }
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public String toStringFormatted() {
        return  "ID " + id +
                " | Name: " + name;
    }
}
