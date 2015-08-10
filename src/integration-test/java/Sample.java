import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.oauth.Token;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Sample {

    public Sample() {
    }

    public static void main(String[] args) throws SmartsheetException{
        SampleProgram();
    }

    public static void SampleProgram() throws SmartsheetException{
        // Set the Access Token
        Token token = new Token();
        token.setAccessToken("INSERT_YOUR_TOKEN_HERE");

        // Use the Smartsheet Builder to create a Smartsheet
        Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(token.getAccessToken()).build();

        // Get home with Source Inclusion parameter
        Home home = smartsheet.homeResources().getHome(EnumSet.of(SourceInclusion.SOURCE));

        // List home folders
        List<Folder> homeFolders = home.getFolders();
        for(Folder folder : homeFolders){
            System.out.println("folder:"+folder.getName());
        }

        //List Sheets with Source Inclusion parameters and null Pagination parameters
        PagedResult<Sheet> homeSheets = smartsheet.sheetResources().listSheets(EnumSet.of(SourceInclusion.SOURCE), null);
        for(Sheet sheet : homeSheets.getData()){
            System.out.println("sheet: " + sheet.getName());
        }

        // Create folder in home
        Folder folder = new Folder.CreateFolderBuilder().setName("New Folder").build();
        folder = smartsheet.homeResources().folderResources().createFolder(folder);
        System.out.println("Folder ID: " + folder.getId() + ", Folder Name: " + folder.getName());

        // Setup checkbox Column Object
        Column checkboxColumn = new Column.AddColumnToSheetBuilder().setType(ColumnType.CHECKBOX).setTitle("Finished").build();
        // Setup text Column Object
        Column textColumn = new Column.AddColumnToSheetBuilder().setPrimary(true).setTitle("To Do List").setType(ColumnType.TEXT_NUMBER).build();
        // Add the 2 Columns (flag & text) to a new Sheet Object
        Sheet sheet = new Sheet.CreateSheetBuilder().setName("New Sheet").setColumns(Arrays.asList(checkboxColumn, textColumn)).build();
        // Send the request to create the sheet @ Smartsheet
        sheet = smartsheet.sheetResources().createSheet(sheet);
    }
}
