package com.bkolomiets.currency_exchange.entity;

import com.bkolomiets.currency_exchange.entity.enums.CurrencyEnum;
import java.math.BigDecimal;
import java.util.UUID;

public class Order extends Currency {
    private UUID uuid;
    private CurrencyEnum sourceCurrency;
    private CurrencyEnum destinationCurrency;
    private BigDecimal amount;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CurrencyEnum getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(CurrencyEnum sourceCurrencyConstant) {
        this.sourceCurrency = sourceCurrencyConstant;
    }

    public CurrencyEnum getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(CurrencyEnum destinationCurrencyConstant) {
        this.destinationCurrency = destinationCurrencyConstant;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}