package com.homework3.homework3overriders.model;

import com.homework3.homework3overriders.enums.Product;
import com.homework3.homework3overriders.enums.Status;
import com.homework3.homework3overriders.supportiveClasses.PrintText;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "decision_maker_id")
    private Contact decisionMaker;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    //Constructor

    public Opportunity() {
    }

    public Opportunity(Product product, int quantity, Contact decisionMaker) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = Status.OPEN;
    }

    public Opportunity(Product product, int quantity, Contact decisionMaker, Status status, SalesRep salesRep) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.salesRep = salesRep;
    }

    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesRep) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.salesRep = salesRep;
        this.status = Status.OPEN;

    }

    public static int getNumberOfTrucks(){

        Scanner scanner = new Scanner(System.in);
        int numberOfTrucks = 0;
        try {
            String lectorComando = scanner.nextLine();
            numberOfTrucks = Integer.parseInt(lectorComando);
        } catch (InputMismatchException e){
            numberOfTrucks = 0;
        }
        catch (NumberFormatException e){
            numberOfTrucks = 0;
        }
        if (numberOfTrucks < 1) {
            PrintText.wrongNumberOfTrucks();
            numberOfTrucks = 0;
        }
        return numberOfTrucks;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    ////////////////////////////////GETTERS AND SETTERS//////////////////////


    public void setId(Long id) {
        this.id = id;
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", decisionMaker=" + decisionMaker +
                ", status=" + status +
                '}';
    }

    public String toStringFormatted() {
        return  "Opportunity ID " + id +
                " | Product: " + product +
                " | Quantity: " + quantity +
                " | Decision Maker: " + decisionMaker.getName() +
                " | Status: " + status;
    }

}
