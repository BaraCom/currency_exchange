package com.bkolomiets.currency_exchange.service;

import static com.bkolomiets.currency_exchange.entity.enums.TableName.getTablesNames;

public class Menu {

    public static void mainMenu() {
        System.out.println(
            "Select a user:\n"
            + "\t 1 - client"
            + "\t 2 - admin");
        System.out.print("Enter your choice: ");
    }

    public static void clientMenu() {
        System.out.println("--------------------");

        getTablesNames();

        System.out.println("--------------------");
        System.out.print("Enter your choice: ");
    }

    public static void adminMenu() {
        System.out.println(
            "+------------------------------------------+\n"
            + "|  1 - get PURCHASE data by currency name  |\n"
            + "|  2 - get SALE data by currency name      |\n"
            + "|  3 - get EXCHANGE data by currency name  |\n"
            + "|  4 - update the data (does NOT WORK)     |\n"
            + "|  5 - delete the data                     |\n"
            + "+------------------------------------------+");
        System.out.print("\t\t\tEnter your choice: ");
    }

    public static void deleteMenu() {
        System.out.print("Enter the number of the desired table: ");
    }
}

