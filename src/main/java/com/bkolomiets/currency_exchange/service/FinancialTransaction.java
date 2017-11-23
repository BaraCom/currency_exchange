package com.bkolomiets.currency_exchange.service;

import com.bkolomiets.currency_exchange.entity.Order;
import com.bkolomiets.currency_exchange.dao.impl.CurrencyDaoImpl;
import com.bkolomiets.currency_exchange.entity.enums.CurrencyEnum;
import static com.bkolomiets.currency_exchange.entity.enums.CurrencyEnum.getCurrencyRate;
import com.bkolomiets.currency_exchange.entity.enums.TableName;
import static com.bkolomiets.currency_exchange.entity.enums.TableName.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.UUID;

public class FinancialTransaction {
    private static final int PURCHASE = 1;
    private static final int SALE = 2;
    private static final int EXCHANGE = 3;

    public static void saveInDB(String sourceName, String destinationName, BigDecimal amount, int choiceMenu) {
        Order order = new Order();
        TableName[] names = values();

        order.setUuid(UUID.randomUUID());
        order.setAmount(amount);
        if (choiceMenu == PURCHASE) {
            order.setSourceCurrency(CurrencyEnum.valueOf(sourceName));
            order.setDestinationCurrency(CurrencyEnum.UAH);
            order.setRate(getCurrencyRate(order.getSourceCurrency().toString(), "", choiceMenu));
            order.setSumConversion(order.getAmount(), order.getRate(), new BigDecimal(0), choiceMenu);
        } else if (choiceMenu == SALE) {
            order.setSourceCurrency(CurrencyEnum.UAH);
            order.setDestinationCurrency(CurrencyEnum.valueOf(sourceName));
            order.setRate(getCurrencyRate(order.getDestinationCurrency().toString(), "", choiceMenu));
            order.setSumConversion(order.getAmount(), order.getRate(), null, choiceMenu);
        } else if (choiceMenu == EXCHANGE) {
            order.setSourceCurrency(CurrencyEnum.valueOf(sourceName));
            order.setDestinationCurrency(CurrencyEnum.valueOf(destinationName));
            order.setRate(
                    getCurrencyRate(
                            order.getSourceCurrency().toString()
                            , order.getDestinationCurrency().toString()
                            , choiceMenu)
            );
            order.setSumConversion(order.getAmount()
                    , new BigDecimal(CurrencyEnum.valueOf(sourceName).getRATE())
                    , new BigDecimal(CurrencyEnum.valueOf(destinationName).getRATE())
                    , choiceMenu);
        }
        order.setDate(new GregorianCalendar().getTime().toString());

        new CurrencyDaoImpl().save(order, names[choiceMenu - 1].toString());
    }

    public static void getFromDB(String currencyName, int menuChoice) {
        new CurrencyDaoImpl().getByName(currencyName, menuChoice);
    }

    public static void deleteFromDB(String tableName, String id) {
        new CurrencyDaoImpl().delete(tableName, id);
    }
}
