package com.smartsheet.api.internal;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;


public class SightResourcesImplTest extends ResourcesImplBase {
    private SightResourcesImpl sightResourcesImpl;

    @Before
    public void before() {
        sightResourcesImpl = new SightResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
            new DefaultHttpClient(), serializer));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullSight() throws SmartsheetException {
        sightResourcesImpl.updateSight(null);
    }
}