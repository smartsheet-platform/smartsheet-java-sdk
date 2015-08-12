package com.smartsheet.api.internal;

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


import com.smartsheet.api.ColumnResources;
import com.smartsheet.api.models.Column;

/**
 * @deprecated As of release 2.0
 * This is the implementation of the ColumnResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
@Deprecated
public class ColumnResourcesImpl implements ColumnResources {

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public ColumnResourcesImpl() {

    }

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public Column updateColumn(Column column) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public void deleteColumn(long id, long sheetId) {
        throw new UnsupportedOperationException();
    }
}