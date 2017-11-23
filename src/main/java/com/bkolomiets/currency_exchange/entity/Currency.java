package com.bkolomiets.currency_exchange.entity;

import java.math.BigDecimal;

public class Currency {
    private BigDecimal rate;
    private String date;
    private BigDecimal sumConversion;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getSumConversion() {
        return sumConversion;
    }

    public void setSumConversion(BigDecimal amount, BigDecimal sourceRate, BigDecimal destinationRate, int choiceMenu) {
        if (choiceMenu == 1) {
            this.sumConversion = amount.multiply(sourceRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else if (choiceMenu == 2) {
            this.sumConversion = amount.divide(sourceRate, 2, BigDecimal.ROUND_HALF_UP);
        } else if (choiceMenu == 3) {
            this.sumConversion = amount.multiply(amount).multiply(sourceRate)
                .divide(amount.multiply(destinationRate), 2, BigDecimal.ROUND_HALF_UP);
        }
    }
}