package com.designre.ipms.chp07;

import java.io.IOException;

public class Currency {

    private String units;
    private long amount;
    private int cents;


    public Currency(double amount, String code) {
        this.units = code;
        setAmount(amount);
    }

    private void setAmount(double amount) {
        this.amount = (new Double(amount)).longValue();
        this.cents = (int) ((amount * 100.0) % 100);
    }

    public Currency toEuros(ExchangeRate converter) {
        if ("EUR".equals(units))
            return this;
        else {
            double input = amount + cents/100.0;
            double rate;
            try {
                rate = converter.getRate(units, "EUR");
                double output = input * rate;
                return new Currency(output, "EUR");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }

    public Currency toEurosCAD(ExchangeRate converter) {
        if ("EUR".equals(units))
            return this;
        else {
            double input = amount + cents/100.0;
            double rate;
            try {
                rate = converter.getRate(units, "CAD");
                double output = input * rate;
                return new Currency(output, "EUR");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public int hashCode() {
        int result = units.hashCode();
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + cents;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Currency) {
            Currency other = (Currency) o;
            return this.units.equals(other.units)
                    && this.amount == other.amount
                    && this.cents == other.cents;
        }
        return false;
    }

    @Override
    public String toString() {
        return amount + "." + Math.abs(cents) + " " + units;
    }

}
