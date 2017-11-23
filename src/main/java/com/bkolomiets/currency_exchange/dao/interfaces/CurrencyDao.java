package com.bkolomiets.currency_exchange.dao.interfaces;

import com.bkolomiets.currency_exchange.entity.Order;

public interface CurrencyDao {

    void getByName(String name, int choiceMenu);

    void save(Order order, String tableName);

    void update(Order order);

    void delete(String tableName, String id);
}