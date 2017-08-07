package com.smartsheet.api.models;

import java.util.List;

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
public class FontFamily {

    /**
     * Represents the name of the font family.
     */
    private String name;

    /**
     * Represents the traits of the font family.
     */
    private List<String> traits;

    /**
     * Gets the name of the font family.
     *
     * @return the name of the font family.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the font family.
     *
     * @param name the new name of the font family.
     */
    public FontFamily setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the traits of the font family.
     *
     * @return the traits of the font family.
     */
    public List<String> getTraits() {
        return traits;
    }

    /**
     * Sets the traits of the font family.
     *
     * @param traits the new traits of the font family.
     */
    public FontFamily setTraits(List<String> traits) {
        this.traits = traits;
        return this;
    }
}
