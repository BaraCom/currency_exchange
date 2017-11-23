package com.bkolomiets.currency_exchange.service;

import com.bkolomiets.currency_exchange.entity.enums.CurrencyEnum;
import com.bkolomiets.currency_exchange.entity.enums.TableName;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import static com.bkolomiets.currency_exchange.entity.enums.CurrencyEnum.getNamesOfCurrency;
import static com.bkolomiets.currency_exchange.entity.enums.TableName.getTablesNames;
import static com.bkolomiets.currency_exchange.service.FinancialTransaction.deleteFromDB;
import static com.bkolomiets.currency_exchange.service.FinancialTransaction.getFromDB;
import static com.bkolomiets.currency_exchange.service.FinancialTransaction.saveInDB;
import static com.bkolomiets.currency_exchange.service.Menu.*;

public class CurrencyData {
    private static final int CLIENT = 1;
    private static final int ADMIN = 2;

    private static final int PURCHASE = 1;
    private static final int SALE = 2;
    private static final int EXCHANGE = 3;

    private static final int GET_PURCHASE = 1;
    private static final int GET_SALE = 2;
    private static final int GET_EXCHANGE = 3;
    private static final int UPDATE = 4;
    private static final int DELETE = 5;

    public static void resultMainMenuChoice() {
        boolean flag = true;

        mainMenu();

        while (flag) {
            Scanner scanner = new Scanner(System.in);

            try {
                switch (scanner.nextInt()) {
                    case CLIENT:
                        clientMenu();
                        resultClientMenuChoice();
                        scanner.close();
                        flag = false;
                        break;
                    case ADMIN:
                        adminMenu();
                        resultAdminMenuChoice();
                        scanner.close();
                        flag = false;
                        break;
                    default:
                        System.out.println("\n- Non-existent item -\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("\n- Enter the menu item number -\n");
            }
        }
    }

    private static  void resultAdminMenuChoice() {
        boolean flag = true;

        while (flag) {
            try {
                Scanner scan = new Scanner(System.in);

                switch (scan.nextInt()) {
                    case GET_PURCHASE:
                        getNamesOfCurrency();
                        System.out.print("Enter the currency name: ");
                        getFromDB(getCorrectCurrencyName(), 1);
                        scan.close();
                        flag = false;
                        break;
                    case GET_SALE:
                        getNamesOfCurrency();
                        System.out.print("Enter the currency name: ");
                        getFromDB(getCorrectCurrencyName(), 2);
                        scan.close();
                        flag = false;
                        break;
                    case GET_EXCHANGE:
                        getNamesOfCurrency();
                        System.out.print("Enter the currency name: ");
                        getFromDB(getCorrectCurrencyName(), 3);
                        scan.close();
                        flag = false;
                        break;
                    case UPDATE:
                        scan.close();
                        flag = false;
                        break;
                    case DELETE:
                        getTablesNames();
                        resultDeleteMenu();

                        scan.close();
                        flag = false;
                        break;
                    default:
                        System.out.println("\n- Non-existent item -\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("\n- Enter the menu item number -\n");
            }
        }
    }

    private static void resultClientMenuChoice() {
        boolean flag = true;

        while (flag) {
            Scanner scanner = new Scanner(System.in);

            try {
                switch (scanner.nextInt()) {
                    case PURCHASE:
                        getNamesOfCurrency();
                        saveInDB(getSourceName(), "", getAmount(), 1);
                        scanner.close();
                        flag = false;
                        break;
                    case SALE:
                        getNamesOfCurrency();
                        saveInDB(getSourceName(), "", getAmount(), 2);
                        scanner.close();
                        flag = false;
                        break;
                    case EXCHANGE:
                        getNamesOfCurrency();
                        saveInDB(getSourceName(), getDestinationName(), getAmount(), 3);
                        scanner.close();
                        flag = false;
                        break;
                    default:
                        System.out.println("\n- Non-existent item -\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("\n- Enter the menu item number -\n");
            }
        }
    }

    private static void resultDeleteMenu() {
        boolean flag = true;

        deleteMenu();

        while (flag) {
            Scanner scanner = new Scanner(System.in);

            try {
                switch (scanner.nextInt()) {
                    case PURCHASE:
                        deleteFromDB(TableName.purchase.name(), getUserId());
                        scanner.close();
                        flag = false;
                        break;
                    case SALE:
                        deleteFromDB(TableName.sale.name(), getUserId());
                        scanner.close();
                        flag = false;
                        break;
                    case EXCHANGE:
                        deleteFromDB(TableName.exchange.name(), getUserId());
                        scanner.close();
                        flag = false;
                        break;
                    default:
                        System.out.println("\n- Non-existent item -\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("\n- Enter the menu item number -\n");
            }
        }
    }

    private static String getSourceName() {
        System.out.print("Source currency: ");

        return getCorrectCurrencyName();
    }

    private static String getDestinationName() {
        System.out.print("Destination currency: ");

        return getCorrectCurrencyName();
    }

    private static BigDecimal getAmount() {
        System.out.print("Amount of currency: ");

        return getCorrectAmount();
    }

    private static String getUserId() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the UUID of the user: ");

        return scanner.nextLine();
    }

    private static String getCorrectCurrencyName() {
        Scanner scanner = new Scanner(System.in);
        CurrencyEnum[] currencyEnum = CurrencyEnum.values();
        char[] inputName;

        while (true) {
            StringBuilder name = new StringBuilder();

            inputName = scanner.nextLine().toCharArray();

            for (char symbol : inputName) {
                name.append(Character.toString(symbol).toUpperCase().trim());
            }

            for (CurrencyEnum currency : currencyEnum) {
                if (currency.name().equals(name.toString())) {
                    return name.toString();
                }
            }
            System.out.print("Enter the correct currency name: ");
        }
    }

    private static BigDecimal getCorrectAmount() {
        char[] symbols;

        while (true) {
            try {
                StringBuilder amount = new StringBuilder();
                Scanner scanner = new Scanner(System.in);
                symbols = scanner.nextLine().toCharArray();

                for (char symbol : symbols) {
                    if (!Character.toString(symbol).matches("[0-9]")) {
                        amount.append(Character.toString(symbol).replace(symbol, '.'));
                    } else {
                        amount.append(Character.toString(symbol));
                    }
                }
                return new BigDecimal(amount.toString());
            } catch (NumberFormatException e) {
                System.out.print("Enter the correct amount: ");
            }
        }
    }
}