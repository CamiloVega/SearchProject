package com.example.tilt.searchproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Tilt on 1/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingResult {

    @JsonProperty("listing")
    private Listing listing;

    @JsonProperty("pricing_quote")
    private PricingQuote pricing_quote;

    public Listing getListing() {
        return listing;
    }

    public PricingQuote getPricingQuote() {
        return pricing_quote;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void setPricing_quote(PricingQuote pricing_quote) {
        this.pricing_quote = pricing_quote;
    }

    public ListingResult() {

    }

    public String getListingName() {
        return this.listing.getName();
    }

    public String getListingImage() {
        return this.listing.getPictureURL();
    }

    public String getPricing() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        numberFormat.setCurrency(Currency.getInstance(pricing_quote.getRate().currency));
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat.format(pricing_quote.getRate().amount);
    }

    public String getPricingType () {
        return this.pricing_quote.getRateType();
    }

    @JsonRootName("listing")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Listing {
        private int id;
        private String name;
        private String picture_url;

        public Listing() {

        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPictureURL() {
            return picture_url;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonRootName("pricing_quote")
    public class PricingQuote {
        private Rate rate;
        private String rate_type;

        public PricingQuote() {

        }

        public void setRate(Rate rate) {
            this.rate = rate;
        }

        public void setRate_type(String rate_type) {
            this.rate_type = rate_type;
        }

        public Rate getRate() {
            return rate;
        }

        public String getRateType() {
            return rate_type;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonRootName("rate")
        private class Rate {
            private int amount;
            private String currency;

            public Rate() {

            }
            public void setAmount(int amount) {
                this.amount = amount;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public int getAmount() {
                return amount;
            }
            public String getCurrency() {
                return currency;
            }
        }
    }
}
