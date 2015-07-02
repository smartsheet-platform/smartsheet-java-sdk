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
import com.smartsheet.api.models.Favorite;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class FavoriteResourcesImplTest extends ResourcesImplBase {
    private FavoriteResourcesImpl favoriteResourcesImpl;

    @Before
    public void setUp() throws Exception {
        favoriteResourcesImpl = new FavoriteResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testAddFavorites() throws Exception {
        server.setResponseBody(new File("src/test/resources/addFavorites.json"));
        List<Favorite> favoritesToAdd = new Favorite.AddFavoriteBuilder().addFavorite(8400677765441412L, "sheet").build();
        List < Favorite > addedfavorites = favoriteResourcesImpl.addFavorites(favoritesToAdd);
        assertEquals(1, addedfavorites.size());
    }
}