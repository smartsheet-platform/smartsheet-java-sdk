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
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SheetSummaryResourcesIT extends ITResourcesImpl {

    Smartsheet smartsheet;
    Sheet sheet;
    List<SummaryField> asf;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();

        // create sheet object
        Sheet sheetHome = createSheetObject();

        //create sheet
        sheet = smartsheet.sheetResources().createSheet(sheetHome);
        if (sheet.getColumns().size() != sheetHome.getColumns().size()) {
            fail("Issue creating a sheet");
        }
    }

    @After
    public void Teardown() throws Exception {
        smartsheet.sheetResources().deleteSheet(sheet.getId());
    }

    @Test
    public void testSheetSummaryMethods() throws SmartsheetException, IOException {
        testAddSheetSummaryFields();
        testGetSheetSummary();
        testAddSheetSummaryFieldsWithPartialSuccess();
        testGetSheetSummaryFields();
        testUpdateSheetSummaryFields();
        testUpdateSheetSummaryFieldsWithPartialSuccess();
        testAddSheetSummaryFieldImage();
        testDeleteSheetSummaryFields();
    }

    public void testAddSheetSummaryFields() throws SmartsheetException {
        SummaryField sf = new SummaryField();
        sf.setTitle("Hello World");
        sf.setType(ColumnType.CHECKBOX);
        sf.setObjectValue(new BooleanObjectValue().setValue(true));

        SummaryField sf1 = new SummaryField();
        sf1.setTitle("Checkbox");
        sf1.setType(ColumnType.CHECKBOX);
        sf1.setObjectValue(new BooleanObjectValue().setValue(false));

        asf = smartsheet.sheetResources().summaryResources().addSheetSummaryFields(
                sheet.getId(), Arrays.asList(sf, sf1),true);

        assertEquals(asf.size(), 2);
    }

    public void testGetSheetSummary() throws SmartsheetException {
        SheetSummary sheetSummary = smartsheet.sheetResources().summaryResources().getSheetSummary(
                sheet.getId(), EnumSet.of(SummaryFieldInclusion.WRITERINFO, SummaryFieldInclusion.FORMAT),
                EnumSet.of(SummaryFieldExclusion.DISPLAYVALUE));
        assertEquals(sheetSummary.getFields().size(), 2);
    }

    public void testAddSheetSummaryFieldsWithPartialSuccess() throws SmartsheetException {
        SummaryField sf = new SummaryField();
        sf.setTitle("Hello World");
        sf.setType(ColumnType.CHECKBOX);
        sf.setObjectValue(new BooleanObjectValue().setValue(true));

        SummaryField sf1 = new SummaryField();
        sf1.setTitle("Eeck!");
        sf1.setType(ColumnType.CHECKBOX);
        sf1.setObjectValue(new BooleanObjectValue().setValue(false));

        BulkItemResult<SummaryField> asf = smartsheet.sheetResources().summaryResources().addSheetSummaryFieldsWithPartialSuccess(
                sheet.getId(), Arrays.asList(sf, sf1),null);

        assertEquals(asf.getFailedItems().size(), 1);
    }

    public void testGetSheetSummaryFields() throws SmartsheetException {
        PagedResult<SummaryField> fields = smartsheet.sheetResources().summaryResources().getSheetSummaryFields(
                sheet.getId(), EnumSet.of(SummaryFieldInclusion.WRITERINFO),
                EnumSet.of(SummaryFieldExclusion.DISPLAYVALUE), null);
        assertEquals(fields.getData().size(), 3);
    }

    public void testUpdateSheetSummaryFields() throws SmartsheetException {
        SummaryField sf = new SummaryField();
        sf.setId(asf.get(0).getId());
        sf.setTitle("Hellooo World!");

        SummaryField sf1 = new SummaryField();
        sf1.setId(asf.get(1).getId());
        sf1.setTitle("Eeek!");

        List<SummaryField> usf = smartsheet.sheetResources().summaryResources().updateSheetSummaryFields(
                sheet.getId(), Arrays.asList(sf, sf1), null);
    }

    public void testUpdateSheetSummaryFieldsWithPartialSuccess() throws SmartsheetException {
        SummaryField sf = new SummaryField();
        sf.setId(asf.get(0).getId());
        sf.setTitle("Hellooo World!");

        SummaryField sf1 = new SummaryField();
        sf1.setId(123L);
        sf1.setTitle("Eeek!");

        BulkItemResult<SummaryField> usf = smartsheet.sheetResources().summaryResources().updateSheetSummaryFieldsWithPartialSuccess(
                sheet.getId(), Arrays.asList(sf, sf1), null);

        assertEquals(usf.getFailedItems().size(), 1);
    }

    public void testAddSheetSummaryFieldImage() throws SmartsheetException {
        Result<SummaryField> rsf = null;
        try {
            rsf = smartsheet.sheetResources().summaryResources().addSheetSummaryFieldImage(
                    sheet.getId(), asf.get(0).getId(),
                    "src/integration-test/resources/exclam.png", null, "alt text");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(rsf.getResultCode() == 0);
    }

    public void testDeleteSheetSummaryFields() throws SmartsheetException {
        Set<Long> setOfIds = new HashSet<Long>();
        setOfIds.add(asf.get(0).getId());
        setOfIds.add(123L);
        List<Long> delIds = smartsheet.sheetResources().summaryResources().deleteSheetSummaryFields(
                sheet.getId(), setOfIds, true);
        assertEquals(delIds.size(), 1);
    }
}

