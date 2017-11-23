package com.bkolomiets.currency_exchange.entity.enums;

import java.math.BigDecimal;

public enum CurrencyEnum {
    USD (26.93),
    EUR (31.36),
    RUB (0.46),
    UAH (0);

    private final double RATE;

    CurrencyEnum(double rate) {
        this.RATE = rate;
    }

    public double getRATE() {
        return RATE;
    }

    public static void getNamesOfCurrency() {
        CurrencyEnum[] currencyEnums = values();

        for (CurrencyEnum currencyEnum : currencyEnums) {
            if (!currencyEnum.equals(CurrencyEnum.UAH)) {
                System.out.println(currencyEnum.toString());
            }
        }
    }

    public static BigDecimal getCurrencyRate(String source, String destination, int choiceMenu) {
        try {
            if (choiceMenu == 1) {
                return new BigDecimal(CurrencyEnum.valueOf(source).getRATE());
            } else if (choiceMenu == 2) {
                return new BigDecimal(CurrencyEnum.valueOf(source).getRATE() + 1.67);
            } else if (choiceMenu == 3) {
                if (CurrencyEnum.valueOf(source).getRATE() < CurrencyEnum.valueOf(destination).getRATE()) {
                    return new BigDecimal(
                        CurrencyEnum.valueOf(destination).getRATE() - CurrencyEnum.valueOf(source).getRATE()
                    );
                } else {
                    return new BigDecimal(
                        CurrencyEnum.valueOf(source).getRATE() - CurrencyEnum.valueOf(destination).getRATE()
                    );
                }
            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
            System.out.println("This is an incorrect value.");
        }
        return BigDecimal.valueOf(0.0);
    }
}