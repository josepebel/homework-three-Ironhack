package com.homework3.homework3overriders.enums;

import com.homework3.homework3overriders.supportiveClasses.PrintText;

import java.util.Scanner;

public enum Product {

    HYBRID, FLATBED, BOX;

    public static Product getProductFromScanner() {

        String command = null;
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();

        if (command.equalsIgnoreCase(HYBRID.toString())){
            return Product.HYBRID;
        } else if (command.equalsIgnoreCase(FLATBED.toString())){
            return Product.FLATBED;
        } else if (command.equalsIgnoreCase(BOX.toString())){
            return Product.BOX;
        } else
            PrintText.wrongProduct();
            return null;
    }
}
