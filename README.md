# Smartsheet Java SDK

This is a Java SDK to simplify connecting to [Smartsheet API](http://www.smartsheet.com/developers/api-documentation) in Java applications.

## Installation
There are three different ways to install the SDK. Select the one that fits your environment best:

1. [Use Maven](#1-maven)
2. [Download Jar File](#2-download-jar-file)
3. [Compile From Source](#3-compile-from-source)

### 1. Use Maven
Add the SDK as a dependency in your project.

```xml
<dependency>
  <groupId>com.smartsheet</groupId>
  <artifactId>smartsheet-sdk-java</artifactId>
  <version>2.2.0</version>
</dependency>
```

### 2. Download Jar File
<!--* [The SDK packaged in a jar with Dependencies](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST) built in.-->
[SDK packaged as a jar](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST). This jar requires that all of the following dependencies are manually added to the path:

	Apache HttpComponents 4.5
	Simple Logging Facade for Java 1.7.12
	Jackson FasterXML 2.6.2
	Jackson Core 2.6.2

### 3. Compile from source
The source code for the jar can be downloaded from Github and then compiled. This can be accomplished using [git](http://git-scm.com/) and [maven](http://maven.apache.org/) with the following 3 steps.

```bash
git clone https://github.com/smartsheet-platform/smartsheet-java-sdk.git
cd smartsheet-java-sdk
mvn package
```

## Documentation
The API is documented here: http://smartsheet-platform.github.io/api-docs/?java
The SDK javadoc is here: [http://smartsheet-platform.github.io/smartsheet-java-sdk](http://smartsheet-platform.github.io/smartsheet-java-sdk) (Download as a jar file [here](http://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST&c=javadoc).)

## Example Usage
A reference sample application is here: https://github.com/smartsheet-samples/java-read-write-sheet


To use the Java SDK, you'll need to include the appropriate **import** directives in your code. For example, the code examples in this section require the following **import** directives:

```java
import com.smartsheet.api.*;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.SourceInclusion;
import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.oauth.*;
```

```java
public static void SampleCode() throws SmartsheetException{
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
    Column checkboxColumn = new Column.AddColumnToSheetBuilder()
                                    .setType(ColumnType.CHECKBOX)
                                    .setTitle("Finished")
                                    .build();
    // Setup text Column Object
    Column textColumn = new Column.AddColumnToSheetBuilder()
                                    .setPrimary(true)
                                    .setTitle("To Do List")
                                    .setType(ColumnType.TEXT_NUMBER)
                                    .build();

    // Add the 2 Columns (flag & text) to a new Sheet Object
    Sheet sheet = new Sheet.CreateSheetBuilder()
                            .setName("New Sheet")
                            .setColumns(Arrays.asList(checkboxColumn, textColumn))
                            .build();
        
    // Send the request to create the sheet @ Smartsheet
    sheet = smartsheet.sheetResources().createSheet(sheet);
}

/**
 * This provides an example of how to use OAuth to generate a Token from a third party application. It handles
 * requesting the authorization code, sending the user to a specific website to request access and then getting
 * the access token to use for all future requests.
 */
public static void OAuthExample() throws SmartsheetException, UnsupportedEncodingException, URISyntaxException,
        NoSuchAlgorithmException {

    // Setup the information that is necessary to request an authorization code
    OAuthFlow oauth = new OAuthFlowBuilder()
                            .setClientId("YOUR_CLIENT_ID")
                            .setClientSecret("YOUR_CLIENT_SECRET")
                            .setRedirectURL("https://YOUR_DOMAIN.com/").build();

    // Create the URL that the user will go to grant authorization to the application
    String url = oauth.newAuthorizationURL(EnumSet.of(com.smartsheet.api.oauth.AccessScope.CREATE_SHEETS,
            com.smartsheet.api.oauth.AccessScope.WRITE_SHEETS), "key=YOUR_VALUE");

    // Take the user to the following URL
    System.out.println(url);

    // After the user accepts or declines the authorization they are taken to the redirect URL. The URL of the page
    // the user is taken to can be used to generate an authorization Result object.
    String authorizationResponseURL = "https://yourDomain.com/?code=l4csislal82qi5h&expires_in=239550&state=key%3D12344";

    // On this page pass in the full URL of the page to create an authorizationResult object
    AuthorizationResult authResult = oauth.extractAuthorizationResult(authorizationResponseURL);

    // Get the token from the authorization result
    Token token = oauth.obtainNewToken(authResult);

    // Save the token or use it.
}
```

More Java examples available [here](https://github.com/smartsheet-samples/).

## Contributing
If you would like to contribute a change to the SDK, please fork a branch and then submit a pull request. [More info here](https://help.github.com/articles/using-pull-requests).

## Support
If you have any questions or issues with this SDK please post on [StackOverflow using the tag "smartsheet-api"](http://stackoverflow.com/questions/tagged/smartsheet-api) or contact us directly at api@smartsheet.com.

## Release Notes

Each specific release is available for download via [Github](https://github.com/smartsheet-platform/smartsheet-java-sdk/tags) or the [Maven repository](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.smartsheet%22%20AND%20a%3A%22smartsheet-sdk-java%22).

See https://github.com/smartsheet-platform/smartsheet-java-sdk/releases
