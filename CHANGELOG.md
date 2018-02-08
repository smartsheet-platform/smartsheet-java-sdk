# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [2.2.3] - 2017-12-7
### Added
- New `attachFile` overloads that accept an `inputStream`
- Constructors that accept object Id for Cell, Row, and Column
- Additional options when publishing Sheets or Reports

### Changes
- Mock tests
- Logging improvements

## [2.2.1] - 2017-02-13
### Changes
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