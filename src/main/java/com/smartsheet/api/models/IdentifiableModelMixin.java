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


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a Jackson Mixin class that excludes "id" field from being serialized to JSON. This is needed because when
 * updating a resource, the resource ID should be present in the resource model but it shouldn't be serialized and sent
 * to Smartsheet REST API.
 *
 * It defines two abstract methods ("id" getter and setter) and annotates them as @JsonIgnore and @JsonProperty
 * respectively.
 *
 * It is a static private inner class of JacksonJsonSerializer class.
 *
 * Thread Safety: This class is thread safe since it's immutable.
 */

public abstract class IdentifiableModelMixin<T> {
    @JsonIgnore public abstract T getId();

    @JsonProperty public abstract void setId(T id);
}
