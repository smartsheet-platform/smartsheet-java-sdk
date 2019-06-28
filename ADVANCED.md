# Advanced Topics for the Smartsheet SDK for Java

## Logging
There are two types of logging used by the Smartsheet Java SDK:

### Console Logger
The console logger logs REST API traffic directly to the console. The console logger is verbose, and as such is best 
used when developing new code or features. To use the console logger, set two system properties:
```java
System.setProperty("Smartsheet.trace.parts", "RequestBodySummary, ResponseBodySummary");
System.setProperty("Smartsheet.trace.pretty", "true");
```
*Smartsheet.trace.pretty* - if ```true```, formats log messages for improved JSON readability. 
If ```Smartsheet.trace.pretty``` is ```false``` the console logger will use a compact format. 

*Smartsheet.trace.parts* - determines what portions of the API traffic are logged. Valid trace parts entries include:
- RequestHeaders
- RequestBody
- RequestBodySummary
- ResponseHeaders
- ResponseBody
- ResponseBodySummary
- Request (RequestHeaders + RequestBodySummary)
- Response (ResponseHeaders + ResponseBodySummary)

By default, console log entries are truncated at 1024 characters. You can change the truncation limit by defining a 
system property ```Smartsheet.trace.truncateLen``` and setting it equal to the desired truncation limit, for example:
```java
System.setProperty("Smartsheet.trace.truncateLen", "512");
```

### Logging Framework
The Smartsheet Java SDK also has a dependency on the SLF4J facade. SLF4J is configurable at or post distribution and
is meant for production environments. More information about SLF4J and the supported logging frameworks is available 
[here](https://www.slf4j.org). 

Using SLF4J, the Smartsheet Java SDK logs all API queries including HTTP method, URI, HTTP status  and response time 
to `INFO`. API calls that fail (HTTP status != 200) are fully logged (request, response and full bodies) to `WARN`. 
Finally, successful (HTTP status 200) request and response summaries are logged to `DEBUG`.
 
The POM for the Smartsheet Java SDK also includes a test only dependency on the ```slf4j-simple``` logging framework.
Details on how to configure logging are framework dependant, however, a usage example for the Simple logger can be 
found in the *simplelogger.properties* file in the Sample folder. Alternately, a usage example for Log4j can 
be found in the java-read-write-sheet example [here](https://github.com/smartsheet-samples/java-read-write-sheet).

## Passthrough Option

If there is an API feature that is not yet supported by the Java SDK, there is a passthrough option that allows you to 
pass and receive raw JSON objects.

To invoke the passthrough, your code can call one of the following four methods:

`jsonResponse = smartsheet.passthroughResources().postRequest(endpoint, payload, parameters);`

`jsonResponse = smartsheet.passthroughResources().getRequest(endpoint, parameters);`

`jsonResponse = smartsheet.passthroughResources().putRequest(endpoint, payload, parameters);`

`jsonResponse = smartsheet.passthroughResources().deleteRequest(endpoint);`

* `endpoint (String)`: The specific API endpoint you wish to invoke. The client object base URL gets prepended to the caller’s 
endpoint URL argument, e.g., if endpoint is 'sheets' an HTTP GET is requested from the URL https://api.smartsheet.com/2.0/sheets
* `payload (String)`: The data to be passed through in the request payload as a string.
* `query_params (Hashmap<String, Object>)`: An optional list of query parameters.

All calls to passthrough methods return a JSON string result.

### Passthrough Example

The following example shows how to POST data to https://api.smartsheet.com/2.0/sheets using the passthrough method and 
a JSON string payload:
```
String payload =
    "{\"name\": \"my new sheet\"," +
        "\"columns\": [" +
            "{\"title\": \"Favorite\", \"type\": \"CHECKBOX\", \"symbol\": \"STAR\"}," +
            "{\"title\": \"Primary Column\", \"primary\": true, \"type\": \"TEXT_NUMBER\"}" +
        "]" +
    "}";
String jsonResponse = smartsheet.passthroughResources().postRequest("sheets", payload, null);
```

## Testing
Unit tests:
1. `mvn test`

Integration tests:
1. Store an access token in your environment as SMARTSHEET_ACCESS_TOKEN
2. `mvn integration-test`

Mock API tests:
1. Clone the [Smartsheet sdk tests](https://github.com/smartsheet-platform/smartsheet-sdk-tests) repo and follow the 
instructions from the readme to start the mock server.
2. `mvn test -Dtest=com.smartsheet.api.sdk_test.*`

## Android
Google doesn’t support the Apache HTTP Client on Android (used as the default HTTP client by the SDK). In order to make it easier to use the Smartsheet Java SDK, the SDK contains a 2nd HTTP client class, AndroidHttpClient. The AndroidHttpClient class is included with version 2.68.4+ of the SDK. To use the Smartsheet Java SDK on Android, follow these steps:

1. Add to the module-level build.gradle's dependencies section:
```gradle
implementation 'com.smartsheet:smartsheet-sdk-java:2.68.4'
```
2. Add to the module-level build.gradle's android section:
```gradle
packagingOptions {
    exclude 'META-INF/DEPENDENCIES'
}
```
3. When you invoke the Smartsheet client, instruct it to use the AndroidHttpClient to access the Smartsheet API:
```java
Smartsheet smartsheet = SmartsheetFactory.custom().setHttpClient(new AndroidHttpClient())
        .setAccessToken("[TOKEN]").build();
```

## Overriding HTTP Client Behavior
You can provide a number of customizations to the default HTTP behavior by extending the DefaultHttpClient class and 
overriding one or more methods (examples below). If required, you can remove use of the Apache HTTP Client 
by implementing the HttpClient interface in a custom client (see 
[Android QRScanner](https://github.com/smartsheet-samples/QRScanner)). 

Common customizations may include:
- implementing an HTTP proxy
- injecting additional HTTP headers
- overriding default timeout or retry behavior
 
### Sample ProxyHttpClient
The following example shows how to enable a proxy by providing the SmartsheetBuilder with an HttpClient which extends 
DefaultHttpClient.  

Invoke the SmartsheetBuilder with a custom HttpClient:

```java
ProxyHttpClient proxyHttpClient = new ProxyHttpClient("localhost", 8080);
Smartsheet smartsheet = SmartsheetFactory.custom().setHttpClient(proxyHttpClient).build();
``` 

```java
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;

public class ProxyHttpClient extends DefaultHttpClient {

    private String proxyHost;
    private Integer proxyPort;

    public ProxyHttpClient(String proxyHost, Integer proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    /** Override this method to inject additional headers, or setup proxy information
     * on the request.
     */
    @Override
    public HttpRequestBase createApacheRequest(HttpRequest smartsheetRequest) {
        HttpRequestBase apacheHttpRequest = super.createApacheRequest(smartsheetRequest);

        RequestConfig.Builder builder = RequestConfig.custom();
        if (apacheHttpRequest.getConfig() != null) {
            builder = RequestConfig.copy(apacheHttpRequest.getConfig());
        }
        HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
        builder.setProxy(proxy);
        RequestConfig config = builder.build();
        apacheHttpRequest.setConfig(config);
        return apacheHttpRequest;
    }
}
```
### Sample RetryHttpClient
The following example shows how to override the default retry/timeout logic.  

Invoke the SmartsheetBuilder with a custom HttpClient:
```java
Smartsheet smartsheet = SmartsheetFactory.custom().setHttpClient(new RetryHttpClient()).build();
smartsheet.setMaxRetryTimeMillis(30000);
```

```java
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.models.Error;

import java.io.IOException;

public class RetryHttpClient extends DefaultHttpClient {

    /**
     * Override this method to perform API requests for special cases
     */
    @Override
    public boolean shouldRetry(int previousAttempts, long totalElapsedTimeMillis, HttpResponse response) {

        // HTTP Status available as response.getStatusCode()
        int httpStatus = response.getStatusCode();

        String contentType = response.getEntity().getContentType();
        if (contentType != null && !contentType.startsWith(JSON_MIME_TYPE)) {
            // it's not JSON; don't even try to parse it
            return false;
        }
        Error error;
        try {
            // Details about the Smartsheet API error condition
            error = jsonSerializer.deserialize(Error.class, response.getEntity().getContent());
        }
        catch (IOException e) {
            return false;
        }
        switch(error.getErrorCode()) {
            // The default shouldRetry, retries 4001, 4002, 4003, 4004 codes
            case 4001:
            case 4002:
            case 4003:
            case 4004:
            case 9999: // adding my fictional error code
                break;
            default:
                return false;
        }

        // The default calcBackoff uses exponential backoff, add custom behavior by overriding calcBackoff
        long backoffMillis = calcBackoff(previousAttempts, totalElapsedTimeMillis, error);
        if(backoffMillis < 0)
            return false;

        logger.info("HttpError StatusCode=" + response.getStatusCode() + ": Retrying in " + backoffMillis + " milliseconds");
        try {
            Thread.sleep(backoffMillis);
        }
        catch (InterruptedException e) {
            logger.warn("sleep interrupted", e);
            return false;
        }
        return true;
    }
}
```
## Event Reporting
The following sample demonstrates best practices for consuming the event stream from the Smartsheet Event Reporting
feature.

The sample uses the `smartsheet.eventResources().listEvents` method to request a list of events from the stream. The
first request sets the `since` parameter with the point in time (i.e. event occurrence datetime) in the stream from 
which to start consuming events. The `since` parameter can be set with a datetime value that is either formatted as 
ISO 8601 (e.g. 2010-01-01T00:00:00Z) or as UNIX epoch (in which case the `numericDates` parameter must also be set to 
`true`. By default the `numericDates` parameter is set to `false`).

To consume the next list of events after the initial list of events is returned, set the `streamPosition` parameter 
with the `nextStreamPosition` property obtained from the previous request and don't set the `since` parameter with 
any values. This is because when using the `listEvents` method, either the `since` parameter or the `streamPosition`
parameter should be set, but never both.

Note that the `moreAvailable` property in a response indicates whether more events are immediately available for
consumption. If events are not immediately available, they may still be generating so subsequent requests should keep
using the same `streamPosition` value until the next list of events is retrieved.

Many events have additional information available as part of the event. That information can be accessed using the 
HashMap stored in the `additionalDetails` property. Information about the additional details provided can be found
[here.](https://smartsheet-platform.github.io/event-reporting-docs/)

```java
public class Sample {
    
    public static void main(String[] args) throws SmartsheetException {
        SampleProgram();
    }
    
    // this example is looking specifically for new sheet events
    private static void printNewSheetEventsInList(List<Event> events)
    {
        //  enumerate all events in the list of returned events
        for(Event event: events) {
            // find all created sheets
            if(event.getObjectType() == EventObjectType.SHEET && event.getAction() == EventAction.CREATE) {
                // additional details are available for some events, they can be accessed as a HashMap
                // in the additionalDetails property
                System.out.println(event.getAdditionalDetails().get("sheetName"));
            }
        }
    }

    public static void SampleProgram() throws SmartsheetException{

        Smartsheet smartsheet = SmartsheetFactory.createDefaultClient();

        // begin listing events in the stream starting with the `since` parameter
        Date lastWeek = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7));
        // this example looks at the previous 7 days of events by providing a since argument set to last week's date 
        EventResult eventResult = smartsheet.eventResources().listEvents(lastWeek, null, 1000, false);
        printNewSheetEventsInList(eventResult.getData());

        // continue listing events in the stream by using the `streamPosition`, if the previous response indicates 
        // that more data is available.
        while(eventResult.getMoreAvailable()) {
            eventResult = smartsheet.eventResources().listEvents(null, eventResult.getNextStreamPosition(), 10000, true);
            printNewSheetEventsInList(eventResult.getData());
        }
    }
}
``` 

## Working With Smartsheetgov.com Accounts

If you need to access Smartsheetgov you will need to specify the Smartsheetgov API URI as the base URI during creation 
of the Smartsheet client object. SmartsheetGov uses a base URI of https://api.smartsheetgov.com/2.0/. The base URI is 
defined as a constant in both the SmartsheetBuilder and SmartsheetFactory classes 
(i.e. SmartsheetFactory.GOV_BASE_URI). The SmartsheetFactory also contains API to create default Smartsheet clients 
which point to the Smartsheetgov URI: 

```java
package com.smartsheet.api.sample;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;

import java.util.List;

/**
 *
 */
public class Sample {
    static {
        // Uncomment these lines to enable logging to console
        // System.setProperty("Smartsheet.trace.parts", "RequestBody,ResponseBodySummary");
        // System.setProperty("Smartsheet.trace.pretty", "true");

    }
    public static void main(String[] args) {
        try {
            // Create Smartsheet client
            // Set your access token in environment variable "SMARTSHEET_ACCESS_TOKEN", else update and uncomment here
            Smartsheet smartsheet = SmartsheetFactory.createDefaultGovAccountClient( /* "ll352u9jujauoqz4gstvsae05" */);

            // List all sheets
            PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets(null, null, null );
            System.out.println("\nFound " + sheets.getTotalCount() + " sheets\n");

            Long sheetId =  sheets.getData().get(0).getId();            // Default to first sheet

            // TODO: Uncomment if you wish to read a specific sheet
            // sheetId = 239236234L;

            // Load entire sheet
            Sheet sheet = smartsheet.sheetResources().getSheet(sheetId, null, null, null, null, null, null, null);
            List<Row> rows = sheet.getRows();
            System.out.println("\nLoaded sheet id " + sheetId + " with " + rows.size() + " rows, title: " + sheet.getName());

            // Display the first 5 rows & columns
            for (int rowNumber = 0; rowNumber < rows.size() && rowNumber < 5; rowNumber++)
                DumpRow(rows.get(rowNumber), sheet.getColumns());
        } catch (SmartsheetException sx) {
            sx.printStackTrace();
        }
        System.out.println("done.");
    }

    static void DumpRow(Row row, List<Column> columns)
    {
        System.out.println("Row # " + row.getRowNumber() + ":");
        for (int columnNumber = 0; columnNumber < columns.size() && columnNumber < 5; columnNumber++) {
            System.out.println("    " + columns.get(columnNumber).getTitle() + ": " + row.getCells().get(columnNumber).getValue());
        }

    }
}

```
