package com.dj.springboot;

import com.taxjar.Taxjar;
import com.taxjar.model.rates.Rate;
import com.taxjar.model.rates.RateResponse;

import java.util.HashMap;
import java.util.Map;

public class TaxJarService {

    private Taxjar taxjar;
    public static final String API_TOKEN = "95e284cdcc9945f9813fa439157840a9";

    public TaxJarService() {
        taxjar = new Taxjar(API_TOKEN);
    }

    public static void main(String[] args) {
        TaxJarService tax = new TaxJarService();
        Rate rate = tax.getTaxRate("US", "90509");
        System.out.println(rate.getCombinedRate());
    }

    /**
     * 获取税率
     *
     * @param country
     * @param postalCode
     */
    public Rate getTaxRate(String country, String postalCode) {
        Map<String, String> params = new HashMap<>();
        params.put("country", country);
        try {
            RateResponse response = taxjar.ratesForLocation(postalCode, params);
            Rate rate = response.rate;
            return rate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
