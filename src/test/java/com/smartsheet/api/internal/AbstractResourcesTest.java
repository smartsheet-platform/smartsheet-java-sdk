package com.smartsheet.api.internal;

import com.smartsheet.api.internal.http.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Map;

/**
 * Created by kskeem on 3/1/16.
 */
public class AbstractResourcesTest {

    @Test
    public void testHeaders() {
        String tokenValue = "somevalue";
        String changeAgent = "mychangeagent";
        AbstractResources resources = new AbstractResources(new SmartsheetImpl("doesnt/matter", tokenValue,  new DefaultHttpClient(), null, changeAgent)) {};

        Map<String, String> headers = resources.createHeaders();
        assertEquals(headers.get("Authorization"), "Bearer " + tokenValue);
        assertEquals(headers.get("Smartsheet-Change-Agent"), changeAgent);
    }
}
