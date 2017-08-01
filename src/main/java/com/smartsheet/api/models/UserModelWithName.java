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

/**
 * Represents the User Model object with name.
 */
public class UserModelWithName extends UserModel{

    /**
     * Represents the user's Name
     */
    private String name;

    /**
     * Gets the user's name
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name
     * @param name the user name
     */
    public UserModelWithName setName(String name) {
        this.name = name;
        return this;
    }
}
