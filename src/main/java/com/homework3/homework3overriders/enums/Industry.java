package com.homework3.homework3overriders.enums;

import com.homework3.homework3overriders.supportiveClasses.PrintText;

import java.util.Scanner;

public enum Industry {
    PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, OTHER;

    public static Industry getIndustryFromScanner() {

        String command = null;
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();

        if (command.equalsIgnoreCase(PRODUCE.toString())){
            return Industry.PRODUCE;
        } else if (command.equalsIgnoreCase(ECOMMERCE.toString())){
            return Industry.ECOMMERCE;
        } else if (command.equalsIgnoreCase(MANUFACTURING.toString())){
            return Industry.MANUFACTURING;
        }else if (command.equalsIgnoreCase(MEDICAL.toString())){
            return Industry.MEDICAL;
        }else if (command.equalsIgnoreCase(OTHER.toString())){
            return Industry.OTHER;
        } else
            PrintText.wrongIndustry();
            return null;
    }
}
