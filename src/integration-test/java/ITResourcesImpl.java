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
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ColumnInclusion;
import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.models.enums.Symbol;
import com.smartsheet.api.models.enums.SystemColumnType;
import com.smartsheet.api.oauth.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ITResourcesImpl {
    Smartsheet smartsheet;
    Token token = new Token();
    String accessToken;

    public ITResourcesImpl() {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(inputStream);
            accessToken = prop.getProperty("AccessToken");

        }catch (IOException ex) {
            ex.printStackTrace();}
    }

    public Smartsheet createAuthentication() throws SmartsheetException{

        //requires changes for config file
        token.setAccessToken(accessToken);
        smartsheet = new SmartsheetBuilder().setAccessToken(token.getAccessToken()).build();

        //Assume user
        //Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(token.getAccessToken()).setAssumedUser("ericyan99@gmail.com").build();
        //UserProfile user= smartsheet.userResources().getCurrentUser();

        return smartsheet;
    }

    public Workspace createWorkspace(String name) throws SmartsheetException{
        Workspace newWorkspace = smartsheet.workspaceResources().createWorkspace(new Workspace.UpdateWorkspaceBuilder().setName("New Test Workspace").build());

        return newWorkspace;
    }

    public Sheet createSheetObject(){
        //set the sheet parameters
        Column columnA = new Column.AddColumnToSheetBuilder().setTitle("Favorite").setType(ColumnType.CHECKBOX).setSymbol(Symbol.STAR).build();
        Column columnB = new Column.AddColumnToSheetBuilder().setTitle("Primary Column").setType(ColumnType.TEXT_NUMBER).setPrimary(true).build();
        Column columnC = new Column.AddColumnToSheetBuilder().setTitle("col 3").setType(ColumnType.PICKLIST).setOptions(Arrays.asList("Not Started", "Started", "Completed")).setPrimary(false).build();
        Column columnD = new Column.AddColumnToSheetBuilder().setTitle("Date Column").setType(ColumnType.DATE).setPrimary(false).build();

        Sheet sheet = new Sheet.CreateSheetBuilder().setName("New Test Sheet").setColumns(Arrays.asList(columnA, columnB, columnC, columnD)).build();
        return sheet;
    }
    public Sheet createSheetObjectWithAutoNumberColumn(){
        //set the sheet parameters
        Column columnA = new Column.AddColumnToSheetBuilder().setTitle("Favorite").setType(ColumnType.CHECKBOX).setSymbol(Symbol.STAR).build();
        Column columnB = new Column.AddColumnToSheetBuilder().setTitle("Primary Column").setType(ColumnType.TEXT_NUMBER).setPrimary(true).build();
        Column columnC = new Column.AddColumnToSheetBuilder().setTitle("col 3").setType(ColumnType.PICKLIST).setOptions(Arrays.asList("Not Started", "Started", "Completed")).setPrimary(false).build();
        Column columnD = new Column.AddColumnToSheetBuilder().setTitle("AutoNumber").setType(ColumnType.TEXT_NUMBER).setSystemColumnType(SystemColumnType.AUTO_NUMBER).setPrimary(false).build();

        Sheet sheet = new Sheet.CreateSheetBuilder().setName("New Test Sheet").setColumns(Arrays.asList(columnA, columnB, columnC, columnD)).build();
        return sheet;
    }

    //method to create test folder for createSheetInFolder
    public Folder createFolder() throws IOException, SmartsheetException {
        Folder folder = new Folder.CreateFolderBuilder().setName("New Folder By Aditi").build();

        Folder newFolderHome = smartsheet.homeResources().folderResources().createFolder(folder);
        return newFolderHome;
    }

    public void deleteWorkspace(long workspaceId) throws IOException, SmartsheetException {
        smartsheet.workspaceResources().deleteWorkspace(workspaceId);
    }

    public void deleteFolder(long folderId) throws SmartsheetException, IOException {
        smartsheet.folderResources().deleteFolder(folderId);
    }

    public void deleteSheet(long sheetId) throws SmartsheetException, IOException {
        smartsheet.sheetResources().deleteSheet(sheetId);
    }

    public Row addRows(long sheetId) throws SmartsheetException, IOException {

        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Column> wrapper = smartsheet.sheetResources().columnResources().listColumns(sheetId, EnumSet.allOf(ColumnInclusion.class), parameters);

        // Create a set of cells
        List<Cell> cells = new Cell.UpdateRowCellsBuilder().addCell(wrapper.getData().get(1).getId(), "test value").build();

        // Create a row and add the cells to it.
        Row row = new Row.AddRowBuilder().setCells(cells).setToBottom(true).build();
        List<Row> rows = new ArrayList<Row>();
        rows.add(row);

       List<Row> rows1 = smartsheet.sheetResources().rowResources().addRows(sheetId, rows);
        return smartsheet.sheetResources().rowResources().getRow(sheetId, rows1.get(0).getId(), null, null);
    }
}
