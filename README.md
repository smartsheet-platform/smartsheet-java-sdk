[![Build Status](https://travis-ci.org/smartsheet-platform/smartsheet-java-sdk.svg?branch=master)](https://travis-ci.org/smartsheet-platform/smartsheet-java-sdk) 
# Smartsheet SDK for Java

This is a Java SDK to simplify connecting to [Smartsheet API](http://www.smartsheet.com/developers/api-documentation) in Java applications.

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
  <version>2.2.3</version>
</dependency>
```

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

## Documentation
The full Smartsheet API documentation is here: http://smartsheet-platform.github.io/api-docs/?java

The generated SDK javadoc is here: [http://smartsheet-platform.github.io/smartsheet-java-sdk](http://smartsheet-platform.github.io/smartsheet-java-sdk) (Download as a jar file [here](http://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=com.smartsheet&a=smartsheet-sdk-java&v=LATEST&c=javadoc).)

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
String accessToken = "ll352u9jujauoqz4gstvsae05";

Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(accessToken).build();

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

## Contributing
If you would like to contribute a change to the SDK, please fork a branch and then submit a pull request. [More info here](https://help.github.com/articles/using-pull-requests).

#### Running the tests
Unit tests:
1. `mvn test`

Integration tests:
1. Set up an api access token in `src/integration-test/resources/config.properties`
2. `mvn integration-test`

Mock API tests:
1. Clone the [Smartsheet sdk tests](https://github.com/smartsheet-platform/smartsheet-sdk-tests) repo and follow the instructions from the readme to start the mock server.
2. `mvn test -Dtest=com.smartsheet.api.sdk_test.*`

## Support
If you have any questions or issues with this SDK please post on [StackOverflow using the tag "smartsheet-api"](http://stackoverflow.com/questions/tagged/smartsheet-api) or contact us directly at api@smartsheet.com.

## Release Notes

Each specific release is available for download via [Github](https://github.com/smartsheet-platform/smartsheet-java-sdk/tags) or the [Maven repository](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.smartsheet%22%20AND%20a%3A%22smartsheet-sdk-java%22).

See https://github.com/smartsheet-platform/smartsheet-java-sdk/releases
