# Smartsheet SDK for Java [![Build Status](https://travis-ci.com/smartsheet-platform/smartsheet-java-sdk.svg?branch=master)](https://travis-ci.com/smartsheet-platform/smartsheet-java-sdk) [![Coverage Status](https://coveralls.io/repos/github/smartsheet-platform/smartsheet-java-sdk/badge.svg?branch=master)](https://coveralls.io/github/smartsheet-platform/smartsheet-java-sdk?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.smartsheet/smartsheet-sdk-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.smartsheet/smartsheet-sdk-java/)

This is a Java SDK to simplify connecting to [Smartsheet API](https://www.smartsheet.redoc.ly) in Java applications.

## Notice
This repo has moved to the Smartsheet Organization on GitHub and can be accessed [here](https://github.com/smartsheet/smartsheet-java-sdk).

## System Requirements

The SDK supports Java version 1.6 or later.

## Installation
There are three different ways to install the SDK. Select the one that fits your environment best:

* [Install By Using Maven](#install-by-using-maven)
* [Install By Downloading the Jar File](#install-by-downloading-the-jar-file)
* [Install By Compiling Directly From Source](#install-by-compiling-directly-from-source)

### Install By Using Maven
Add the SDK as a dependency in your project.

```xml
<dependency>
  <groupId>com.smartsheet</groupId>
  <artifactId>smartsheet-sdk-java</artifactId>
  <version>[version]</version>
</dependency>
```
The current artifact version reference can be found [here](https://oss.sonatype.org/#nexus-search;quick~smartsheet)

### Install By Downloading the Jar File
<!--* [The SDK packaged in a jar with Dependencies](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST) built in.-->
[SDK packaged as a jar](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST). This jar requires that all of the following dependencies are manually added to the path:

    Apache HttpComponents 4.5
    Simple Logging Facade for Java 1.7.12
    Jackson FasterXML 2.6.2
    Jackson Core 2.6.2

You can also navigate to the [Sonatype Library Page](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22smartsheet-sdk-java%22) instead of directly downloading the Jar file.

### Install By Compiling Directly From Source
The source code for the jar can be downloaded from Github and then compiled. This can be accomplished using [git](http://git-scm.com/) and [maven](http://maven.apache.org/) with the following 3 steps.

```bash
git clone https://github.com/smartsheet-platform/smartsheet-java-sdk.git
cd smartsheet-java-sdk
mvn package
```

## Example Usage
To call the API, you will need an *access token*, which looks something like this example: ll352u9jujauoqz4gstvsae05. You can find the access token in the UI at Account > Personal Settings > API Access.

The following is a brief sample that shows you how to:

* Initialize the client
* List all sheets
* Load one sheet

To initialize the client, you'll need to include the appropriate **import** directives in your code. For example, the code examples in this section require the following **import** directives:

```java
import com.smartsheet.api.*;
import com.smartsheet.api.models.*;
import com.smartsheet.api.oauth.*;
import java.io.FileInputStream;
import java.util.*;
```

```java
// Initialize client
String accessToken = "JKlMNOpQ12RStUVwxYZAbcde3F5g6hijklM789";

Smartsheet smartsheet = SmartsheetFactory.createDefaultClient(accessToken);

// List all sheets
PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets(
    null,           // EnumSet<SourceInclusion> includes
    null,           // PaginationParameters
    null            // Date modifiedSince
);

System.out.println("Found " + sheets.getTotalCount() + " sheets");

long sheetId = sheets.getData().get(0).getId();      // Default to first sheet

// sheetId = 567034672138842L;                       // TODO: Uncomment if you wish to read a specific sheet

System.out.println("Loading sheet id: " + sheetId);

// Load the entire sheet
Sheet sheet = smartsheet.sheetResources().getSheet(
    sheetId,                // long sheetId
    null,                   // EnumSet<SheetInclusion> includes
    null,                   // EnumSet<ObjectExclusion> excludes
    null,                   // Set<Long> rowIds
    null,                   // Set<Integer> rowNumbers
    null,                   // Set<Long> columnIds
    null,                   // Integer pageSize
    null                    // Integer page
);
System.out.println("Loaded " + sheet.getTotalRowCount() + " rows from sheet: " + sheet.getName());
```

A simple, but complete sample application is here: https://github.com/smartsheet-samples/java-read-write-sheet

More Java examples available [here](https://github.com/smartsheet-samples/).

## Advanced Topics
For details about logging, testing, how to use a passthrough option, and how to override HTTP client behavior, see [Advanced Topics](ADVANCED.md).

## Documentation
The full Smartsheet API documentation is here: https://smartsheet.redoc.ly

The generated SDK javadoc is here: [http://smartsheet-platform.github.io/smartsheet-java-sdk](http://smartsheet-platform.github.io/smartsheet-java-sdk) (Download as a jar file [here](http://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST&c=javadoc).)

## Contributing
If you would like to contribute a change to the SDK, please fork a branch and then submit a pull request. [More info here](https://help.github.com/articles/using-pull-requests).

## Version Numbers
Starting from the v2.68.0 release, Smartsheet SDKs will use a new versioning strategy. Since all users are on the 
Smartsheet API 2.0, the SDK version numbers will start with 2. The 2nd number will be an internal reference number.
The 3rd number is for incremental changes.

For example, v2.68.0 means that you are using our 2.0 version of the API, the API is synced internally to a tag of 68,
and then if there are numbers after the last decimal, that will indicate a minor change.

## Support
If you have any questions or issues with this SDK please post on [StackOverflow using the tag "smartsheet-api"](http://stackoverflow.com/questions/tagged/smartsheet-api) or contact us directly at devrel@smartsheet.com.

## Release Notes
Each specific release is available for download via [Github](https://github.com/smartsheet-platform/smartsheet-java-sdk/tags) or the [Maven repository](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.smartsheet%22%20AND%20a%3A%22smartsheet-sdk-java%22).

See https://github.com/smartsheet-platform/smartsheet-java-sdk/releases
