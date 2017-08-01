package com.smartsheet.api.models;
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
public class Currency {

    /**
     * Represents currency code.
     */
    private String code;

    /**
     * Represents currency symbol.
     */
    private String symbol;

    /**
     * Gets the currency code.
     *
     * @return the currency code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the currency code.
     *
     * @param code the currency code
     */
    public Currency setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Gets the currency symbol.
     *
     * @return the currency symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the currency symbol.
     *
     * @param symbol the currency symbol
     */
    public Currency setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
}
