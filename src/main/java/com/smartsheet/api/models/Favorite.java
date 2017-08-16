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

import com.smartsheet.api.models.enums.FavoriteType;

import java.util.ArrayList;
import java.util.List;

public class Favorite{

    /**
     * Represents type of favorite (workspace, folder, sheet, report, template, sight).
     */
    private FavoriteType type;

    /**
     * Represents ID of the favorited item.
     */
    private Long objectId;

    /**
     * Gets the ID of the favorited item.
     *
     * @return the objectId
     */
    public Long getObjectId() {
        return objectId;
    }

    /**
     * Sets the ID of the favorited item.
     *
     * @param objectId ID of the favorited item.
     */
    public Favorite setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }

    /**
     * Gets the type of favorite (workspace, folder, sheet, report, template).
     *
     * @return the type
     */
    public FavoriteType getType() {
        return type;
    }

    /**
     * Sets the type of favorite (workspace, folder, sheet, report, template).
     *
     * @param type the new dependencies enabled
     */
    public Favorite setType(FavoriteType type) {
        this.type = type;
        return this;
    }

    /**
     * A convenience class for making a {@link Favorite} object with the appropriate fields for adding to a {@link Favorite}.
     */
    public static class AddFavoriteBuilder {

        List<Favorite> favorites = new ArrayList<Favorite>();

        public AddFavoriteBuilder addFavorite(long objectId, FavoriteType type) {
            Favorite favorite = new Favorite();
            favorite.setObjectId(objectId);
            favorite.setType(type);
            favorites.add(favorite);
            return this;
        }

        public List<Favorite> build() { return favorites; }
    }
}

