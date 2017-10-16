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
 * Represents an object with an ID.
 */
public abstract class IdentifiableModel<T> {
    /** Represents the ID. */
    private T id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public T getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public IdentifiableModel<T> setId(T id) {
        this.id = id;
        return this;
    }

    /**
     * Check if the given object equals to this object.
     *
     * @param object the object to compare
     * @return true if given object equals to this object, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if(object != null && object == this){
            result = true;
        }else if(object != null && object.getClass() == this.getClass() &&
                // If they are both null
                (((IdentifiableModel<?>)object).getId() == this.getId()
                // If they are not null but are equal objects.
                || ((IdentifiableModel<?>)object).getId() != null && this.getId() != null &&
                ((IdentifiableModel<?>)object).getId().equals(this.getId()))) {
            result = true;
        }

        return result;
    }

    /**
     * Return the hash code of this object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        if(this.id == null){
            result = super.hashCode();
        }else{
            result = id.toString().hashCode();
        }
        return result;
    }
}
