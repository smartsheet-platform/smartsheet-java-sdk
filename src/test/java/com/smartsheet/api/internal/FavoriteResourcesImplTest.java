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
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Favorite;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import static org.junit.Assert.*;

public class FavoriteResourcesImplTest extends ResourcesImplBase {
    private FavoriteResourcesImpl favoriteResources;

    @Before
    public void setUp() throws Exception {
        favoriteResources = new FavoriteResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testAddFavorites() throws Exception {
        server.setResponseBody(new File("src/test/resources/addFavorites.json"));
        List<Favorite> favoritesToAdd = new Favorite.AddFavoriteBuilder().addFavorite(8400677765441412L, "sheet").build();
        List < Favorite > addedfavorites = favoriteResources.addFavorites(favoritesToAdd);
        assertEquals(1, addedfavorites.size());
    }

    @Test
    public void testListFavorites() throws Exception {
        server.setResponseBody(new File("src/test/resources/listFavorites.json"));
        PaginationParameters parameters = new PaginationParameters(false,1,1);
        PagedResult<Favorite> favorites = favoriteResources.listFavorites(parameters);
        assertNotNull(favorites.getData().get(0).getType());
        assertEquals(favorites.getData().get(0).getType(), "sheet");
        //assertEquals(favorites.getData().get(1).getColumnType(), "folder");
    }
}