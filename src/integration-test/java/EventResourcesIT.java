/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Event;
import com.smartsheet.api.models.EventResult;
import com.smartsheet.api.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EventResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    User user;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testListEvents() throws SmartsheetException {

        Date lastHour = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
        EventResult eventResult = smartsheet.eventResources().listEvents(lastHour, null, 10, false);
        assertTrue(eventResult.getData().size() <= 10);
        for (Event event: eventResult.getData()) {
            assertNotNull(event.getObjectType());
            assertNotNull(event.getAction());
            assertNotNull(event.getObjectId());
            assertNotNull(event.getEventId());
            assertTrue(event.getEventTimestamp() instanceof Date);
            assertNotNull(event.getUserId());
            assertNotNull(event.getRequestUserId());
            assertNotNull(event.getSource());
        }

        while(eventResult.getMoreAvailable()) {
            eventResult = smartsheet.eventResources().listEvents(null, eventResult.getNextStreamPosition(), 10, true);
            assertTrue(eventResult.getData().size() != 0);
            assertTrue(eventResult.getData().size() <= 10);
            for (Event event: eventResult.getData()) {
                assertNotNull(event.getObjectType());
                assertNotNull(event.getAction());
                assertNotNull(event.getObjectId());
                assertNotNull(event.getEventId());
                assertTrue(event.getEventTimestamp() instanceof Long);
                assertNotNull(event.getUserId());
                assertNotNull(event.getRequestUserId());
                assertNotNull(event.getSource());
            }
        }
    }

    @Test
    public void testInvalidParams() {
        try {
            EventResult eventResult = smartsheet.eventResources().listEvents(0, "2.1.0An4ZapaQaOXPdojlmediSZ1WqMdi5U_3l9gViOW7ic", 10, null);
            assertTrue(true);
        } catch (SmartsheetException e) { }

        try {
            EventResult eventResult = smartsheet.eventResources().listEvents(new Date(), null, 10, true);
            assertTrue(true);
        } catch (SmartsheetException e) { }
    }
}
