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
1. Set up an api access token in `src/integration-test/resources/config.properties`
2. `mvn integration-test`

Mock API tests:
1. Clone the [Smartsheet sdk tests](https://github.com/smartsheet-platform/smartsheet-sdk-tests) repo and follow the 
instructions from the readme to start the mock server.
2. `mvn test -Dtest=com.smartsheet.api.sdk_test.*`

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