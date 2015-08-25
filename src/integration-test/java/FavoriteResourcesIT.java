/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.FavoriteType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FavoriteResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testAddFavorites() throws SmartsheetException, IOException {
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());
        Folder folder = createFolder();

        List<Favorite> favoritesToAdd = new Favorite.AddFavoriteBuilder().addFavorite(sheet.getId(), FavoriteType.SHEET).addFavorite(folder.getId(), FavoriteType.FOLDER).build();
        List < Favorite > addedfavorites = smartsheet.favoriteResources().addFavorites(favoritesToAdd);
        assertEquals(2, addedfavorites.size());
        deleteFolder(folder.getId());
        deleteSheet(sheet.getId());
    }

    @Test
    public void testListFavorites() throws SmartsheetException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Favorite> favorites = smartsheet.favoriteResources().listFavorites(parameters);
        assertNotNull(favorites);
    }

    @Test
    public void testRemoveFavorites() throws Exception {

        Folder folder1= createFolder();
        Folder folder2= createFolder();

        smartsheet.favoriteResources().addFavorites(new Favorite.AddFavoriteBuilder().addFavorite(folder1.getId(), FavoriteType.FOLDER).addFavorite(folder2.getId(), FavoriteType.FOLDER).build());

        smartsheet.favoriteResources().removeFavorites(FavoriteType.FOLDER, new HashSet(Arrays.asList(folder1.getId())));
        //assertNotNull(smartsheet.favoriteResources().listFavorites(null));

        //clean up
        deleteFolder(folder1.getId());
        deleteFolder(folder2.getId());
    }

    @Test
    public void testDeleteUser() throws IOException, SmartsheetException {
        DeleteUserParameters parameters = new DeleteUserParameters(12345L, true, true);
        //smartsheet.userResources().deleteUser(1234L, parameters);
    }


}
