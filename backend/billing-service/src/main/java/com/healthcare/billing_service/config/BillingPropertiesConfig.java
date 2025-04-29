package com.healthcare.billing_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "billing")
public class BillingPropertiesConfig {

    private String currency;
    private Double taxRate;

    public BillingPropertiesConfig() {
    }
    public BillingPropertiesConfig(String currency, Double taxRate) {
        this.currency = currency;
        this.taxRate = taxRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "BillingPropertiesConfig{" +
                "currency='" + currency + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}