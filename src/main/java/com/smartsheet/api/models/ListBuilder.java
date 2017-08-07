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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Simple class that makes creating type safe lists of object easier
 */
public class ListBuilder<T> {
    private List<T> list;

    /**
     * Default constructor
     */
    public ListBuilder() {
    }

    /**
     * Constructor
     * @param initialCapacity Initial capacity of the internal storage of the list.
     */
    public ListBuilder(int initialCapacity) {
        this.list = new ArrayList<T>(initialCapacity);
    }

    /**
     * Adds a single element to the list
     * @param element element to add
     * @return ListBuilder
     */
    public ListBuilder<T> add(T element) {
        list.add(element);
        return this;
    }

    /**
     * Adds multiple elements to the list
     * @param elements elements to add
     * @return ListBuilder
     */
    public ListBuilder<T> add(T... elements) {
        Collections.addAll(list, elements);
        return this;
    }

    /**
     * Adds multiple elements to the list
     * @param elements elements to add
     * @return ListBuilder
     */
    public ListBuilder<T> add(Collection<T> elements) {
        list.addAll(elements);
        return this;
    }

    /**
     * Retrieves the built up list of objects
     * @return The build up list of objects
     */
    public List<T> build() {
        return list;
    }
}
