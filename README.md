#Smartsheet Java SDK

This is a Java SDK to simplify connecting to [Smartsheet API](http://www.smartsheet.com/developers/api-documentation) services in a Java applications.

##Installation
There are 3 different approaches that can be used to install the SDK. We offer these 3 approaches so that the SDK can be installed in a manner most familiar or best for the environment that it is being used in. Only one of the following installation steps need to be choosen:

1. [Maven](#1-maven)
2. [Download Jar File or Jar with Dependencies](#2-download-jar-file)
3. [Compile From Source](#3-compile-from-source)

###1. Maven
Add the SDK as a dependency in your project.

```xml
<dependency>
  <groupId>com.smartsheet</groupId>
  <artifactId>smartsheet-sdk-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

###2. Download Jar File
<!--* [The SDK packaged in a jar with Dependencies](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST) built in.-->
* [The SDK packaged in a jar](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST). This jar requires that all of the following dependencies are manually added to the path.

        Apache HttpComponents 4.3.2
        Simple Logging Facade for Java 4.3.2
        Jackson FasterXML 2.2.3
        Jackson Core 2.2.3

###3. Compile from Source
The source code for the jar can be downloaded from Github and then compiled. This can be accomplished using [git](http://git-scm.com/) and [maven](http://maven.apache.org/) with the following 3 steps.

```bash
git clone https://github.com/smartsheet-platform/smartsheet-java-sdk.git
cd smartsheet-java-sdk
mvn package
```

## Documentation
The SDK API documentation can be viewed online at the following location: [http://smartsheet-platform.github.io/smartsheet-java-sdk/](http://smartsheet-platform.github.io/smartsheet-java-sdk/)

The documentation can also be downloaded as a jar file [here](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java-sources&v=LATEST).

##Example Usage
---

```java
// Set the Access Token
Token token = new Token();
token.setAccessToken("15zytv32phcmx5xqowxqad5qxm");

// Use the Smartsheet Builder to create a Smartsheet
Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(token.getAccessToken()).build();

// Get home
Home home = smartsheet.home().getHome(EnumSet.of(ObjectInclusion.TEMPLATES));

// List home folders
List<Folder> homeFolders = home.getFolders();
for(Folder folder : homeFolders){
    System.out.println("folder:"+folder.getName());
}

// List Sheets
List<Sheet> homeSheets = smartsheet.sheets().listSheets();
for(Sheet sheet : homeSheets){
    System.out.println("sheet:"+sheet.getName());
}

// Create folder in home
Folder folder = new Folder();
folder.setName("New Folder");
folder = smartsheet.home().folders().createFolder(folder);
System.out.println("Folder ID:"+folder.getId()+", Folder Name:"+folder.getName());

// Setup flag Column Object
Column flagColumn = new Column.AddColumnToSheetBuilder().setType(ColumnType.CHECKBOX).setTitle("Finished").build();
// Setup text Column Object
Column textColumn = new Column.AddColumnToSheetBuilder().setPrimary(true).setTitle("To Do List").setType(ColumnType.TEXT_NUMBER).build();
// Add the 2 Columns (flag & text) to a new Sheet Object
Sheet sheet = new Sheet.CreateSheetBuilder().setName("New Sheet").setColumns(Arrays.asList(flagColumn, textColumn)).build();
// Send the request to create the sheet @ Smartsheet
sheet = smartsheet.sheets().createSheet(sheet);
```

## Contributing
---
If you would like to contribute a change to the SDK, please fork a branch and then submit a pull request. [More info here](https://help.github.com/articles/using-pull-requests).

##Support
---
If you have any questions or issues with this SDK please feel free to send us an email at: api@smartsheet.com