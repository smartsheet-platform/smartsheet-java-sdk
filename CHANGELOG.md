# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [2.126.0] - 2021-04-23
### Added
- add support for column formulas

### Changed
- add missing 'ADMIN_SIGHTS` and `READ_CONTACTS`

### Fixed
- add missing scopes `READ_CONTACTS` and `ADMIN_SIGHTS`
- fix typo in `image`

### Other
- build(deps-dev): bump jetty-server

## [2.120.0] - 2021-03-01
### Changed
- update jackson-databind to resolve security vulnerability
- build(deps-dev): bump junit from 4.12 to 4.13.1
- build(deps-dev): bump jetty-server

## [2.101.1] - 2020-08-31
### Changed
- add missing `rules` and `ruleRecipients` to create from template

### Fixed
- fix the double encoding of search terms

## [2.101.0] - 2020-07-29
### Fixed
- Separate ProfileImage model from Image model

## [2.93.0] - 2020-05-20
### Changed
- add support for Webhook subscope

## [2.86.2] - 2020-01-28
### Changed
- add overloads to addCellImage and addSheetSummaryFieldImage to take a
  File or InputStream.

## [2.86.1] - 2019-11-22
### Fixed
- fix NPE if failedItems is null

## [2.86.0] - 2019-11-08
### Added
- type and object definitions to support multi-picklist columns

### Changed
- add format and objectValue to CellHistoryInclusion
- additions to CellDataItem widget contents to support METRIC widgets
  containing sheet summary fields
- dashboards widget model to support widgets that are in an error state
- listColumns needs level support

## [2.83.0] - 2019-08-19
### Added
- support for sheet summary
- add dateFormat to format tables.
- add support for includes argument for ListUsers
- add column description property
- implement addRow, updateRow interfaces that allow includes and excludes

### Changed
- significant overhaul to Sights
- deprecated copyFolder, copyWorkspace methods with unsupported excludes
- serialize dates using ISO-8601 formatting

### Fixed
- resolve jackson-databind security vulnerability

## [2.68.4] - 2019-06-28
### Fixed
- removed errant plugin dependency

## [2.68.3] - 2019-06-27
### Added
- Initial support for an Android http client

### Fixed
- add CARD_DONE tag to supported list of column tags

## [2.68.2] - 2019-06-17
- Resolved Jackson security vulnerability

## [2.68.1] - 2019-06-15
- Travis build automation

## [2.68.0] - 2019-05-09
### Added
- Implement Event Reporting

## [2.2.9] - 2019-02-05
### Added
- Added BASE URI definition for Smartsheetgov
- Added group inclusion for GetCurrentUser

## [2.2.8] - 2019-01-15
### Changed
- Updated versions of Jackson in the POM to resolve security vulnerabilities

## [2.2.7] - 2018-11-06
### Changed
- Updated versions of Jetty and Jackson in the POM to resolve security vulnerabilities

## [2.2.6] - 2018-09-07
### Added 
- Multi-contact list 

## [2.2.5] - 2018-05-30
### Added
- Sheet import for XLSX and CSV (includes methods to import into Folders and Workspaces)
- Limited support for CHART widget types

### Changed
- Removed old Link model which was replaced by Hyperlink and CellLink   

### Fixed
- Hyperlink and HyperlinkSerializer were missing the sightId property

## [2.2.4] - 2018-04-02
### Added
- SmartsheetFactory for creating Smartsheet client objects
- [Automation rules](http://smartsheet-platform.github.io/api-docs/?shell#automation-rules)
- [Cross sheet references](http://smartsheet-platform.github.io/api-docs/?shell#cross-sheet-references)
- Passthrough mechanism to pass raw JSON requests through to the API (documented in the README)
- Sheet filter implementation
- Row sort feature
- User profile properties (including profileImage) to UserModel
- Scope, location and favoriteFlag inclusion to search
- getSheet() ifVersionAfter parameter 
- Client method to modify HTTP User-Agent header
- Bulk access to sheet version through sheetVersion inclusion
- Missing title widget for Sights
- Deserialization of error detail
- Logging examples for SimpleLogger and Log4j

### Changed
- HttpClient interface to allow SDK users to inject HTTP headers or implement an HTTP proxy by extending 
DefaultHttpClient (a proxy sample is provided in the README)
- Removed ShouldRetry and CalcBackoff interfaces and replaced with HttpClient interface methods. You can now customize 
shouldRetry or calcBackoff using the same method as proxy or request header injection (i.e. extend DefaultHttpClient).

### Fixed
- Several deserialization issues with Sights
- Share builders were improperly setting share type
- The rate-limit/backoff retry scenario did not work if the request contained a body because the body stream had not 
been reset prior to the retry (PUT/POST).  The Apache HttpClient will raise a NonRepeatableRequestException that is now 
handled by the SDK (which resets the body content stream).
- There is a keep-alive race condition that exists when the server disconnects idle connections. If a request is made 
in the window in between when the server has disconnected, but before the client has detected the disconnect, it looks 
to the client as if the server returned a blank HTTP status line. Idempotent methods (PUT, DELETE, GET) are retried 
automatically. A POST request will not be retried automatically by the Apache HttpClient. This fix will handle 
NoHttpResponseException exceptions and retry POSTs automatically after resetting the body content stream.


## [2.2.3] - 2017-12-7
### Added
- New `attachFile` overloads that accept an `inputStream`
- Constructors that accept object Id for Cell, Row, and Column
- Additional options when publishing Sheets or Reports

### Changed
- Mock tests
- Logging improvements

## [2.2.1] - 2017-02-13
### Changed
- Fixed flags for Folder/Workspace Copy or Remap

## [2.2.0] - 2017-08-02
### Added
- Logging
- Chained setters
- Sheet.ProjectSettings
- First class support for `PredecessorList` as an implementation of `ObjectValue`

    This is a breaking change if you use PredecessorList
    
    This code sample shows how to update PredecessorList after updating to 2.2.0:
    ```Java
      Predecessor predecessor = new Predecessor();
      predecessor.setRowId(265775251515268L);         // Id of row to depend on
      predecessor.setType("FS");

      PredecessorList pl = new PredecessorList(new ArrayList<Predecessor>(Arrays.asList(predecessor)));

      Cell cell = new Cell();
      cell.setColumnId(7984328786372484L);           // Id of 'Predecessors' column
      cell.setObjectValue(pl);

      Row row = new Row();
      row.setId(6146602304857988L);                  // Id of row to update
      row.setCells(new ArrayList<Cell>(Arrays.asList(cell)));
      ArrayList<Row> rows = new ArrayList<Row>(Arrays.asList(row));

      smartsheet.sheetResources().rowResources().updateRows(3102146867554180L, rows);  // Id of sheet
    ```
    
## Earlier releases
- Documented in [Github releases page](https://github.com/smartsheet-platform/smartsheet-java-sdk/releases)
