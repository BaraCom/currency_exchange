package com.bkolomiets.currency_exchange.entity.enums;

public enum TableName {
    purchase,
    sale,
    exchange;

    public static void getTablesNames() {
        TableName[] names = TableName.values();
        int point = 1;

        for (TableName name : names) {
            System.out.println(point + " - " + name);
            point++;
        }
    }
}