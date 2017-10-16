package com.smartsheet.api.models.format;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


/**
 * @author kskeem
 * 
 * An enum representing the available currency formats availble in Smartsheet.
 * 
 */
public enum Currency {
    NONE                (null,  ""),
    ARGENTINE_PESO      ("ARS", "\u0024"),
    AUSTRALIAN_DOLLAR   ("AUD", "$"),
    BRAZIL_REAIS        ("BRL", "\u0052$"),
    CANADIAN_DOLLAR     ("CAD", "$"),
    CHILEAN_PESOS       ("CLP", "$"),
    EURO                ("EUR", "\u20AC"),
    BRITISH_POUND       ("GBP", "\u00A3"),
    ISRAEL_SHEKEL       ("ILS", "\u20AA"),
    INDIA_RUPEES        ("INR", "\u20A8"),
    JAPAN_YEN           ("JPY", "\u00A5"),
    MEXICAN_PESOS       ("MXN", "$"),
    RUSSIAN_RUBLES      ("RUB", "\u0440\u0443\u0431"),
    USD                 ("USD", "$"),
    SOUTHAFRICA_RAND    ("ZAR", "R"),
    SWISS_FRANC         ("CHF", "CHF"),
    CHINA_YUAN          ("CNY", "\u5143"),
    DENMARK_KRONER      ("DKK", "kr"),
    HONKKONG_DOLLAR     ("HKD", "\u0048\u004B$"),
    SOUTHKOREAN_WON     ("KRW", "\u20A9"),
    NORWAY_KRONER       ("NOK", "kr"),
    NEWZEALAND_DOLLAR   ("NZD", "$"),
    SWEDEN_KRONOR       ("SEK", "kr"),
    SINGAPORE_DOLLAR    ("SGD", "$"),
    ;

    /**
     * The default setting when the {@link Format} for {@link Currency} is null;
     */
    public static final Currency DEFAULT = NONE;

    private final String code;
    private final String symbol;

    Currency (String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }
}